//类定义
manju.Package("manju.dict");

manju.dict.dictionary = function(){
    var myTable;
    var dictTable = {
        id: "support_table5",	//表格id
        seItem: null,		//选中的条目
        table: null,
        layerIndex: -1
    };
    var initColumn =
         [
            {field: 'selectItem', radio: true},
            {title: 'id', visible: false, field: 'id', sort_name: 'id',  align: 'center', valign: 'middle',width:'50px'},
            {title: '值', field: 'value', sort_name: 'value', align: 'center', valign: 'middle', sortable: true},
            {title: '名称', field: 'label', sort_name: 'label', align: 'center', valign: 'middle', sortable: true},
            {title: '类型', field: 'type', sort_name: 'type', align: 'center', valign: 'middle', sortable: true},
            {title: '备注', field: 'description', sort_name: 'description', align: 'center', valign: 'middle', sortable: true},
            //{title: '操作',  align: 'center', valign: 'middle', sortable: true, formatter : Dept.formatter}
        ];


    return {
        init:function(){
            var defaultColunms = initColumn;
            var table = new BSTable(dictTable.id, "dict/qryDictionary", defaultColunms);
            table.setPaginationType("server");
            table.setQueryParams({});
            myTable = table.init();
            console.log(myTable)

        },
        tableLoad : function (){
            
        }
    }
}();

//初始化
manju.ExecWait(manju.dict.dictionary.init);