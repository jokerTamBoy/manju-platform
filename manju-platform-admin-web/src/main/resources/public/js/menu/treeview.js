/**
 *  Document   : treeview.js
 *  Author     : redstar
 *  Description: script for treeview data
 *
 **/

$(function () {
    initMenuTree();
    initMenuList();
    selectMenuTree();
});

function initMenuTree() {
    /*加载树状菜单*/
    $.post(
        '/menu/tree',
        {},
        function (data, textStatus, jqXHR) {
            $('#treeview7').treeview({
                color: '#428bca',
                levels: 2,
                showBorder: false,
                enableLinks: true,
                data: data
            });
        },
        'json'
    )
}

function selectMenuTree() {
    /*加载树状菜单*/
    $.post(
        '/menu/selectTree',
        {},
        function (data, textStatus, jqXHR) {
            $('#treeview8').treeview({
                color: '#428bca',
                levels: 2,
                showBorder: false,
                enableLinks: true,
                data: data
            });
        },
        'json'
    )
}

function selectParentMenu(parentId, parentIds, parentName){
    $("#parentId").val(parentId);
    $("#parentIds").val(parentIds);
    $("#parentName").val(parentName);
    $("#myModalClose").click();
}

function initMenuList() {
    //加载菜单列表
    manju.OpenPageLoad2('menu/list', {}, 'menuBox');
}



function submitMenu() {
    //ajax方式提交表单
    $.ajax(
        {
            url: '/menu/submit',
            type: 'post',
            data: JSON.stringify($('#menuForm').serializeJSON()),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if (data.result == 'true' || data.result == true) {
                    //加载菜单详情
                    manju.OpenPageLoad2('menu/edit', {id: data.id}, 'menuBox');
                    initMenuTree();
                } else {
                    alert(data.msg);
                }
            }
        });
}