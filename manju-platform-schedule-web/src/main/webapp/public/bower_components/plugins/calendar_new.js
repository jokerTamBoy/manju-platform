$(document).ready(function() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
     $('#calendar').fullCalendar({

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },

        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            var title = prompt('Event Title:');
            var day=0;
            if(allDay){
                day=1;
            }
            if (title) {
                var params = {
                    title: title,
                    startTime: new Date(start).getTime(),
                    endTime: new Date(end).getTime(),
                    allDay: day
                };
                $.ajax({
                    url:"/calendar/add",
                    type:"post",
                    data:JSON.stringify(params),
                    dataType:"json",
                    contentType:"application/json",
                    success: function(res){

                        alert("添加成功");
                        // if(res.status == 0){
                        //     $("#success1").html("<strong>添加成功!</strong>");
                        //     $("#success1").show();
                        //     setTimeout(function(){$("#success1").hide();},3000);
                        //
                        // }else{
                        //     $("#err1").html("<strong>添加失败!</strong>");
                        //     $("#err1").show();
                        //     setTimeout(function(){$("#err1").hide();},3000);
                        // }
                        // $('#calendar').fullCalendar('refetchEvents');
                    }
                    // ,
                    // err:function(res){
                    //     $("#err1").html("<strong>操作有误!</strong>");
                    //     $("#err1").show();
                    //     setTimeout(function(){$("#err1").hide();},3000);
                    // }
                });//insert
            }
        },
        editable: true,
        events: function(start,end,callback){
            var params = {userId:"test"};
            $.ajax({
                url:"/calendar/list",
                type:"get",
                data:params,
                dataType: 'json',
                success: function(res){
                    var events =[];
                    var list=res["extend"]["events"];
                    if(res.code==200){
                        for(i in list){
                            if(list[i].allDay == 0){
                                events.push({
                                    id:list[i]._id,
                                    title:list[i].title,
                                    start:new Date(list[i].y_start,list[i].m_start,list[i].d_start,list[i].hh_start,list[i].mm_start),
                                    end:new Date(list[i].y_end,list[i].m_end,list[i].d_end,list[i].hh_end,list[i].mm_end),
                                    allDay:false,
                                    backgroundColor: "#9b59b6"
                                });
                            }else{
                                events.push({
                                    id:list[i]._id,
                                    title:list[i].title,
                                    start:new Date(list[i].y_start,list[i].m_start,list[i].d_start),
                                    end:new Date(list[i].y_end,list[i].m_end,list[i].d_end),
                                    allDay:true,
                                    backgroundColor: "#9b59b6"
                                });
                            }
                        }
                        callback(events);

                        // try{
                        //     //$("#success1").html(events[0].start+"*_*"+events[0].end);
                        //     //$("#success1").show();
                        //     callback(events);
                        // }catch(e){
                        //     hc("div.fc-event");
                        //     fc("span.fc-button");
                        // } finally {
                        //     hc("div.fc-event");
                        //     fc("span.fc-button");
                        // }

                    }

                }
            });
        } ,


    })
});

