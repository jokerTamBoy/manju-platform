$(document).ready(function() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    var calendar = $('#calendar').fullCalendar({

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },

        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            var title = prompt('Event Title:');
            if (title) {
                var params = {
                    title: title,
                    start: start,
                    end: end,
                    allDay: allDay
                };
                $.ajax({
                    url:"/insertCalendar",
                    type:"post",
                    data:params,
                    dataType: 'json',
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
            var params = {};
            $.ajax({
                url:"/findCalendar",
                type:"post",
                data:params,
                dataType: 'json',
                success: function(res){
                    var events =[];
                    if(res.status==0){
                        for(i in res.events){
                            if(res.events[i].allDay == false){
                                events.push({
                                    id:res.events[i]._id,
                                    title:res.events[i].title,
                                    start:new Date(res.events[i].y_start,res.events[i].m_start,res.events[i].d_start,res.events[i].hh_start,res.events[i].mm_start),
                                    end:new Date(res.events[i].y_end,res.events[i].m_end,res.events[i].d_end,res.events[i].hh_end,res.events[i].mm_end),
                                    allDay:false
                                });
                            }else{
                                events.push({
                                    id:res.events[i]._id,
                                    title:res.events[i].title,
                                    start:new Date(res.events[i].y_start,res.events[i].m_start,res.events[i].d_start),
                                    end:new Date(res.events[i].y_end,res.events[i].m_end,res.events[i].d_end),
                                    allDay:true
                                });
                            }
                        }
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
                        callback(events);
                    }
                }
            });
        } ,


    })
});