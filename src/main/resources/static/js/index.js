// const companyList = [
//   '重庆中链融科技有限公司',
//   '重庆数宜信信用管理有限公司',
//   '重庆中链融科技有限公司',
//   '重庆数宜信信用管理有限公司',
//   '重庆中链融科技有限公司',
//   '重庆中链融科技有限公司',
//   '重庆数宜信信用管理有限公司',
//   '重庆中链融科技有限公司',
//   '重庆数宜信信用管理有限公司',
//   '重庆中链融科技有限公司',
//   '重庆数宜信信用管理有限公司'];
let companyList =[];

function compareCompany(value) {
    value = value.trim();
    console.log(value, 11)
    let result = []
    companyList.map(item => {
        if (item.indexOf(value) != -1) {
        result.push(item)
    }
})
    return result
}
function initCompanyList(companyList) {
    let resultList = [];
    companyList.map((item,i) => {
        if(i<10){
        resultList.push('<li><a href="#">' + item + '</a></li>')
    }
})
    $('#power-company-list').empty()
    $('#power-company-list').append(resultList)

}


function showDropList() {
    $('#power-company-list').css({
        display: 'block'
    })
}
function hideDropList() {
    $('#power-company-list').css({
        display: 'none'
    })
}

$("#power-search-input").on('keyup', function (e) {
    const value = e.target.value;
    let result = [];
    if (value) {
        result = compareCompany(value)
    }
    console.log("result", result)
    initCompanyList(result)
    if (result.length > 0) {
        showDropList()
    } else {
        hideDropList()
    }


});

$("#power-company-list").on('click', function (e) {
    console.log(e.target)
    const value = $(e.target).text();
    $("#power-search-input").val(value)
    hideDropList()
})


$('#power-search-btn').on('click', function () {
    let value = $("#power-search-input").val().trim()
    const filterArr = companyList.filter(item=>item===value);
    if (filterArr.length>0) {
        window.open('./detail.html?companyName=' + value)
    }else{
        $('#modal').modal()
    }

})


$(document).ready(function () {
    $('#search-box').css({
        height: ($(document).height() - 60) + 'px'
    })
    $.ajax(apiHost + '/power/queryCompanyName.json', {
        dataType: 'json',
        success: function (res) {
            console.log("res,res",res)
            companyList = res.warningList;
            initCompanyList(companyList)
        }
    });
});

