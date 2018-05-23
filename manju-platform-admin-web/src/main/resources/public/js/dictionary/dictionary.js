//类定义
manju.Package("manju.dict");

manju.dict.dictionary = function(){
    var myTable;
    return {
        init:function(){
         // var list =  manju.InvokeMethod('dict/qryDictionary',{dict:null,  page : {current : 1, size : 100}});
         // console.log(list)

            // $("#support_table5").DataTable({
            //     ajax : 'dict/qryDictionary',
            //     "columns": [
            //         { "data": "value" },
            //         { "data": "label" },
            //         { "data": "type" },
            //         { "data": "description" },
            //     ],
            //
            //
            //     serverSide : true,//开启服务器模式:启用服务器分页
            //     //lengthChange : true,//是否允许用户改变表格每页显示的记录数
            //     ordering : false,//是否允许用户排序
            //     paging : true,//是否分页
            //     pagingType : "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
            //     processing : false,//是否显示处理状态
            //     /* scrollX: true,//允许水平滚动
            //     scrollY: "200px",
            //     scrollCollapse: true, */
            //     searching : false,//是否开始本地搜索
            //     stateSave : false,//刷新时是否保存状态
            //     autoWidth : true,//自动计算宽度
            //     //lengthMenu : [[10, 25, 50, -1], [10, 25, 50, "All"]],
            // });



                myTable = $('#support_table5').DataTable( {
                    "deferRender": true,
                    "processing": true,//刷新的那个对话框
                    "serverSide": true,//服务器端获取数据
                    "paging": true,//开启分页
                    lengthMenu: [ //自定义分页长度
                        [ 20, 50, 100 ],
                        [ '20 页', '50 页', '100 页' ]
                    ],
                    ordering:false,
                    "ajax": {
                        "url": "dict/qryDictionary",
                        "type": "POST",
                        "data": null,
                        "dataType" : "json",
                        "dataFilter": function (json) {//json是服务器端返回的数据

                            return json;//这几个参数都是datatable需要的，必须要
                        }
                    },
                    "searching" : false,
                    "columns": [
                                { "data": "value" },
                                { "data": "label" },
                                { "data": "type" },
                                { "data": "description" },
                            ],
                    "oLanguage" : { // 国际化配置
                        "sProcessing" : "正在获取数据，请稍后...",
                        "sLengthMenu" : "显示 _MENU_ 条",
                        "sZeroRecords" : "没有找到数据",
                        "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                        "sInfoEmpty" : "记录数为0",
                        "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                        "sInfoPostFix" : "",
                        "sSearch" : "查询",
                        "sUrl" : "",
                        "oPaginate" : {
                            "sFirst" : "第一页",
                            "sPrevious" : "上一页",
                            "sNext" : "下一页",
                            "sLast" : "最后一页"
                        }
                    }
                });
            console.log(myTable)

        },
        tableLoad : function (){
            
            myTable.ajax.reload();
        }
    }
}();

//初始化
manju.ExecWait(manju.dict.dictionary.init);