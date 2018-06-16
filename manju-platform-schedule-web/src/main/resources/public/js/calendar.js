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
    layui.define(['layer'], function(exports){
        var controller="";
        var layer = layui.layer;

        $("#addCalendar").click(function () {
            controller="new";
            //页面层
            layer.open({
                type: 2,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '550px'], //宽高
                content: ['/add']
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
            var tip="主题："+calEvent.title+"\n\r";
            tip+="开始日期："+new Date(calEvent.start)+"\n\r";
            tip+="结束日期："+new Date(calEvent.end)+"\n\r";
            tip+="内容："+calEvent.content+"\n\r";

            //小tips
            layer.tips(tip, ".fc-content", {
                tips: [1, '#3595CC'],
            });
        },
        eventClick: function (event) {
            layer.open({
                type: 2,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '550px'], //宽高
                content: ['/getById?id='+event.id]
            });
        }
        // ,
        // events: function(start, end, timezone, callback) {
        //     $.ajax({
        //         url: 'myxmlfeed.php',
        //         dataType: 'xml',
        //         data: {
        //             // our hypothetical feed requires UNIX timestamps
        //             start: start.unix(),
        //             end: end.unix()
        //         },
        //         success: function(doc) {
        //             var events = [];
        //             $(doc).find('event').each(function() {
        //                 events.push({
        //                     title: $(this).attr('title'),
        //                     start: $(this).attr('start') // will be parsed
        //                 });
        //             });
        //             callback(events);
        //         }
        //     });
        // }
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
                    var list=re["extend"]["events"];
                    for(var i in list){
                        events.push({
                            id:list[i].id,
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
    //
    // layui.use('layer',function () {
    //     var layer = layui.layer;
    //     //iframe层
    //     $("#addCalendar").click(function () {
    //         layer.open({
    //             type: 2,
    //             title: '新增事件页',
    //             shadeClose: true,
    //             shade: 0.8,
    //             area: ['380px', '90%'],
    //             content: ['/add','no']//iframe的url
    //         });
    //     });
    //
    // });

});

// function NewCalendar() {
//     layui.use('layer',function () {
//         var layer = layui.layer;
//         //iframe层
//         layer.open({
//             type: 2,
//             title: '新增事件页',
//             shadeClose: true,
//             shade: 0.8,
//             area: ['380px', '90%'],
//             content: ['/add','no']//iframe的url
//         });
//     });
//
// };

function deleteById(calendarId) {

    $.ajax({
        url: '/delete',
        type:"post",
        cache: false,
        data: {
            id:calendarId
        },
        success: function (re) {
            if(re.code==200){
                layer.msg({
                    content:"删除成功",
                    time:2000
                });
                window.location.href="/index";
            }
        }
    });
}
function delCalendar() {
    var id=$("#calendarId").val();
    window.location.href="/delete?id="+id;
}