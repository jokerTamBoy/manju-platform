'use strict';
// var layer=require("./initLayer");

$(document).ready(function () {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    // layui.config({
    //     base: '/js/' //你存放新模块的目录，注意，不是layui的模块目录
    // }).use('initLayer'); //加载入口

    /**
     * layui
     **/
    layui.define(['layer'], function (exports) {
        var controller = "";
        var layer = layui.layer;

        $("#addCalendar").click(function () {
            controller = "new";
            //页面层
            parent.layer.open({
                type: 2,
                title: '新增页面',
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '550px'], //宽高
                content: ['/add'],
                shadeClose: true,
                // end:function () {
                //     location.reload();
                // }
            });
        });
        exports('layer', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
    });


    /**
     * fullCalendar
     */
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        editable: false,
        dayClick: function (date, allDay, jsEvent, view) {
            // AddCalendar($.fullCalendar.formatDate(date, "yyyyMMdd"));
        },
        eventMouseover: function (calEvent, jsEvent, view) {
            var tip = "主题：" + calEvent.title + "\n\r";
            tip += "开始日期：" + new Date(calEvent.start) + "\n\r";
            tip += "结束日期：" + new Date(calEvent.end) + "\n\r";
            tip += "内容：" + calEvent.content + "\n\r";

            //小tips
            // layer.tips(tip, ".fc-content", {
            //     tips: [1, '#3595CC'],
            // });
        },
        eventClick: function (event) {
            layer.open({
                type: 2,
                title: '修改页面',
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '550px'], //宽高
                content: ['/getById?id=' + event.id],
                shadeClose: true,
                // end:function () {
                //     location.reload();
                // }
            });
        }

        ,
        events: function (start, end, timezone, callback) {
            var datestart = $.fullCalendar.formatDate(start, "yyyyMMdd");
            var dateend = $.fullCalendar.formatDate(end, "yyyyMMdd");
            var events = [];
            $.ajax({
                url: '/list',
                dataType: 'json',
                cache: false,
                data: {
                    userId: "test",
                    start: datestart,
                    end: dateend
                },
                success: function (re) {
                    var list = re["extend"]["events"];
                    for (var i in list) {
                        events.push({
                            id: list[i].id,
                            title: list[i].title,
                            start: new Date(parseInt(list[i].startTime)),
                            end: new Date(parseInt(list[i].endTime))
                        })
                    }

                    callback(events);
                }
            });

        }
    });

});


function deleteById() {
    swal({
        title: "确定删除该事件吗?",
        text: "你将会删除该事件！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定删除!",
        cancelButtonText: "取消操作!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                url: '/del',
                dataType: 'json',
                type: "post",
                data: {
                    id: id
                },
                cache: false,
                success: function (data) {
                    if (data.extend.result) {
                        swal("删除成功!!", "你成功删除了该事件", "success");
                        parent.location.reload();
                    }
                },
                error: function (data) {
                    swal("已取消", "删除失败，请检查原因", "error");
                }
            });

        } else {
            swal("已取消", "你已经取消了该操作", "error");
        }
    });

    var id = $("#calendarId").val();

}

function edit() {
    var ok = true,
        message = "";
    var reg=/^([12][0-9]|3[01]|0?[1-9])\/([1][0-2]|0?[1-9])\/[1-2][0-9][0-9][0-9] ([01][0-9]|[2][0-3]):[0-5][0-9]:[0-6][0-9]$/;
    var title=$("#title").val(),st=$("#startTime").val(),ed=$("#endTime").val();
    var r = st.match(reg),
        rr = ed.match(reg);
    if (title.length <= 0) {
        message = "主题不能为空！";
        ok = false;
    } else if ($.trim(st).length <= 0 ||
        $.trim(ed).length <= 0
    ) {
        message = "开始时间或者结束时间不能为空！";
        ok = false;
    }
    else if (r == null || rr==null) {
        message = "开始时间或者结束时间格式不正确！！";
        ok = false;
    }
    if (!ok) {
        swal("错误", message, "error");
    }else{

    swal({
        title: "确定修改吗?",
        text: "你将会把该事件修改！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定修改",
        cancelButtonText: "取消",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            $.ajax({
                url: '/updateCalendar',
                dataType: 'json',
                type: "post",
                contentType: "application/json",
                data: JSON.stringify({
                    title: $("#title").val(),
                    startTime: $("#startTime").val(),
                    endTime: $("#endTime").val(),
                    content: $("#content").val(),
                    id: $("#calendarId").val()
                }),
                cache: false,
                success: function (data) {
                    if (data.extend.result) {
                        swal("修改成功!", "你已经把该事件修改成功", "success");
                        parent.location.reload();
                    }
                },
                error: function (data) {
                    alert("error");
                }
            });
        } else {
            swal("取消", "你已经取消了该操作", "error");
        }
    });
    }

}

function add() {
    var ok = true,
        message = "";
    var reg=/^([12][0-9]|3[01]|0?[1-9])\/([1][0-2]|0?[1-9])\/[1-2][0-9][0-9][0-9] ([01][0-9]|[2][0-3]):[0-5][0-9]$/;
    var title=$("#title").val(),st=$("#startTime").val(),ed=$("#endTime").val();
    var r = st.match(reg),
        rr = ed.match(reg);
    if (title.length <= 0) {
        message = "主题不能为空！";
        ok = false;
    } else if ($.trim(st).length <= 0 ||
        $.trim(ed).length <= 0
    ) {
        message = "开始时间或者结束时间不能为空！";
        ok = false;
    }
    else if (r == null || rr==null) {
        message = "开始时间或者结束时间格式不正确！！";
        ok = false;
    }
    if (!ok) {
        swal("错误", message, "error");
    } else {
        $.ajax({
            url: '/doAdd',
            dataType: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                title: $("#title").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                content: $("#content").val(),
                userId: $("#userId").val()
            }),
            cache: false,
            success: function (data) {
                if (data.extend.result) {
                    swal({
                        title: "新增成功",
                        text: "你已经成功添加了一个事件",
                        type: "success"
                    });
                    parent.location.reload();
                }
            },
            error: function (data) {
                alert("error");
            }
        });
    }
}

function toSelect() {
    layer.open({
        type: 2,
        title: '事件搜索',
        skin: 'layui-layer-rim', //加上边框
        area: ['85%', '80%'], //宽高
        content: ['/toSelect'],
        shadeClose: true,
        // end:function () {
        //     location.reload();
        // }
    });
}

// var conditions=[
//     {name:"id",symbols:[{symbol:"等于"},{symbol:"不等于"}]},
//     {name:"userId",symbols:[{symbol:"等于"},{symbol:"包含"},{symbol:"不包含"}]},
//     {name:"title",symbols:[{symbol:"等于"},{symbol:"包含"},{symbol:"不包含"}]},
//     {name:"startTime",symbols:[{symbol:"等于"},{symbol:"不等于"},{symbol:"大于等于"},{symbol:"小于等于"},{symbol:"大于"},{symbol:"小于"}]},
//     {name:"endTime",symbols:[{symbol:"等于"},{symbol:"不等于"},{symbol:"大于等于"},{symbol:"小于等于"},{symbol:"大于"},{symbol:"小于"}]},
//     {name:"createTime",symbols:[{symbol:"等于"},{symbol:"不等于"},{symbol:"大于等于"},{symbol:"小于等于"},{symbol:"大于"},{symbol:"小于"}]},
//     {name:"editTime",symbols:[{symbol:"等于"},{symbol:"不等于"},{symbol:"大于等于"},{symbol:"小于等于"},{symbol:"大于"},{symbol:"小于"}]},
//     {name:"content",symbols:[{symbol:"等于"},{symbol:"包含"},{symbol:"不包含"}]}
// ];