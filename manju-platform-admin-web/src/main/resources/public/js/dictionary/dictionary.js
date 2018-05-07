//类定义
manju.Package("manju.dict");

manju.dict.dictionary = function(){

    return {
        init:function(){
         // var list =  manju.InvokeMethod('dict/qryDictionary',{dict:null,  page : {current : 1, size : 100}});
         // console.log(list)

            $("#support_table5").DataTable({
                ajax : 'dict/qryDictionary',
                "columns": [
                    { "data": "value" },
                    { "data": "label" },
                    { "data": "type" },
                    { "data": "description" },
                ],
                serverSide : true,//开启服务器模式:启用服务器分页
                lengthChange : true,//是否允许用户改变表格每页显示的记录数
                ordering : false,//是否允许用户排序
                paging : true,//是否分页
                pagingType : "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
                processing : true,//是否显示处理状态
                /* scrollX: true,//允许水平滚动
                scrollY: "200px",
                scrollCollapse: true, */
                searching : false,//是否开始本地搜索
                stateSave : false,//刷新时是否保存状态
                autoWidth : true,//自动计算宽度
                lengthMenu : [[10, 25, 50, -1], [10, 25, 50, "All"]],
                "language": {

                }
            });

        }
    }
}();

//初始化
manju.ExecWait(manju.dict.dictionary.init);