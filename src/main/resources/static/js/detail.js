

function renderTable(title, content) {
    let tableTitle = '<tr>'
    let tableContent = ''
    let lastItem = content[content.length - 1];
    title.map(item => {
        tableTitle += '<th>' + item + '</th>'
    })
    tableTitle += '</tr>'
    content.slice(0, 17).map((item, i) => {
        tableContent += '<tr>'
        tableContent += '<td>' + (i + 1) + '</td>'
        tableContent += '<td class="tl">' + item.riskDimension + '</td>'

        tableContent += '<td>' + parseWarningTag(item.earlyWarningSituation, { isCircle: true }) + '</td>'
        tableContent += '<td>' + parseWarningTag(item.electricHouseholdOne, { isCircle: true }) + '</td>'

        tableContent += '</tr>'
    })

    tableContent += '<tr>'
    tableContent += '<td colspan="2">' + lastItem.riskDimension + '</td>'
    tableContent += '<td>' + parseWarningTag(lastItem.earlyWarningSituation, { isCircle: false }) + '</td>'
    tableContent += '<td>' + parseWarningTag(lastItem.electricHouseholdOne, { isCircle: false }) + '</td>'
    tableContent += '</tr>'

    const tableHtml = '<table class="table table-bordered"><tbody>' + tableTitle + tableContent + '</tbody></table>'
    $('#table-data').empty()
    $('#table-data').html(tableHtml)

}

function parseWarningTag(value, option) {
    const isCircle = option.isCircle;
    const className = option.className;
    let str = ''
    if (isCircle) {
        switch (value) {
            case '0':
                str = '<span class="pew-circle-green"></span>'
                break;
            case '1':
                str = '<span class="pew-circle-red"></span>'
                break;
            case '-101':
                str = '<span class="pew-circle-orange"></span>'
                break;


        }
    } else {
        switch (value) {
            case '0':
                str = '<span class="pew-warning-green ' + className + '\">正常</span>'
                break;

            case '1':
                str = '<span class="pew-warning-red ' + className + '\">预警</span>'
                break;


        }
    }
    return str;
}

function getChartData(data) {
    let dateList = [];
    let powerList = [];
    let warningList = [];
    if(data){
        const dataSource = data.warningList;
        dataSource.map(item=>{
            dateList.push(item.day);
        powerList.push(item.electricityConsumption);
        // if (item.warningSignal=='1'){
        //   warningList.push({ xAxis: item.day, yAxis: item.electricityConsumption });
        // }
    })
    }
    console.log(warningList,'yuandian')

    return {
        dateList: dateList,
        powerList: powerList,
        warningList: warningList,
    }
}
function renderChart(data) {
    const chartData = getChartData(data);

    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('echart'));

    // 指定图表的配置项和数据
    let option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                let tar;
                if (params[1].value != '-') {
                    tar = params[1];
                }
                else {
                    tar = params[0];
                }
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },
        legend: {},
        xAxis: {
            data: chartData.dateList,
            axisLabel: {
                interval: 0,
                rotate: 60,
                textStyle: {
                    fontSize: 8
                }
            }
        },
        yAxis: {
            type: 'value',
            name: '日用电量（千瓦·时）',
            min: 0,
            max: 20000,
            interval: 2000,
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: [{
            name: '日用电量',
            type: 'bar',
            data: chartData.powerList,
            itemStyle: {
                normal: {
                    color: '#47A6A4'
                }
            },
            // label: {
            //   normal: {
            //     show: true,
            //     position: 'top'
            //   }
            // },
        }, {
            name: '日用电量',
            type: 'line',
            lineStyle: {
                normal: {
                    color: '#F4884E',
                    type: 'dashed'
                }
            },
            // markPoint: {
            //   data: chartData.warningList
            // },
            data: chartData.powerList,
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    myChart.on('click', 'series', function (chart) {
        const current = data.warningList.filter(item => item.day===chart.name)
        const tableTitleData = ['序号', '风险维度', '预警总况', '用电户号']
        const tableContentData = current[0].dayWarningInfoList
        renderTable(tableTitleData,tableContentData)
        renderBasicInfo({
            companyName: data.companyName,
            electricityAcountNumber: data.electricityAcountNumber,
            warningSignal: current[0].warningSignal
        })
    });



}

function renderBasicInfo(data) {
    let basicStr = '<div class="pew-top-name"><img src="./images/gongsi.png" alt="">' + data.companyName
    basicStr += parseWarningTag(data.warningSignal, { isCircle: false, className: 'pew-warning-large' }) + '</div>';
    basicStr += '<div class="pew-top-list">'
    basicStr += '<div class="pew-top-list-item">用电户号：' + data.electricityAcountNumber  + parseWarningTag(data.warningSignal, { isCircle: false }) + '</div>'
    basicStr += '</div>'
    $('#basic-info').empty();
    $('#basic-info').html(basicStr)
}
function search(startDate, endDate) {
    console.log('--' + startDate + ',' + endDate + '--')
    queryPower();
}

//取url中的参数值
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
console.log(getChartData(), getQueryString("companyName"))
$(document).ready(function () {
    // renderTable(tableTitleData, tableContentData)
    // renderChart()
    // renderBasicInfo()
    queryPower();


});

function queryPower() {
    $.ajax(apiHost + '/power/queryPower.json', {
        dataType: 'json',
        data: {
            companyName: getQueryString('companyName'),
            // companyName: '无锡弘阳光伏有限公司',
            endDate: $('#end-date').val()
        },
        success: function (res) {
            // alert(JSON.stringify(res))
            const data = res || {};
            console.log("data,data", data)
            const warningList = data.warningList
            const tableTitleData = ['序号', '风险维度', '预警总况', '用电户号']
            const tableContentData = warningList[warningList.length - 1].dayWarningInfoList

            renderTable(tableTitleData, tableContentData)
            renderChart(data)
            renderBasicInfo({
                companyName:data.companyName,
                electricityAcountNumber:data.electricityAcountNumber,
                warningSignal: tableContentData[tableContentData.length - 1].earlyWarningSituation
            })
        }
    });
}