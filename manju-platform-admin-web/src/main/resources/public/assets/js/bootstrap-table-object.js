/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author fengshuonan
 */
(function () {
    var BSTable = function (bstableId, url, columns) {
    	BSTable.isByClassSort(true); 			// 默认以 class属性为排序属性
        this.btInstance = null;					//jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = Feng.ctxPath + url;
        this.method = "post";
        this.paginationType = "server";			//默认分页方式是服务器分页,可选项"client"
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.height = 665;						//默认表格高度665
        this.data = {};
        this.queryParams = {}; // 向后台传递的自定义参数
    };

    BSTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            var me = this;
            this.btInstance =
                $('#' + tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    url: this.url,				//请求地址
                    method: this.method,		//ajax方式,post还是get
                    ajaxOptions: {				//ajax请求的附带参数
                        data: this.data
                    },
                    toolbar: "#" + this.toolbarId,//顶部工具条
                    striped: true,     			//是否显示行间隔色
                    cache: false,      			//是否使用缓存,默认为true
                    pagination: true,     		//是否显示分页（*）
                    sortable: true,      		//是否启用排序
                    sortOrder: "desc",     		//排序方式
                    pageNumber: 1,      			//初始化加载第一页，默认第一页
                    pageSize: 14,      			//每页的记录行数（*）
                    pageList: [14, 50, 100],  	//可供选择的每页的行数（*）
                    queryParamsType: 'limit', 	//默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParams: function (param) {
                        return $.extend(me.queryParams, param);
                    }, // 向后台传递的自定义参数
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: false,      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    strictSearch: true,			//设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: true,     		//是否显示所有的列
                    showRefresh: true,     		//是否显示刷新按钮
                    minimumCountColumns: 2,    	//最少允许的列数
                    clickToSelect: true,    	//是否启用点击选中行
                    searchOnEnterKey: true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,		//列数组
                    pagination: true,			//是否显示分页条
                    height: this.height,
                    icons: {
                        refresh: 'fa fa-repeat',
                        toggle: 'fa fa-list-ol',
                        columns: 'fa fa-list-ol'
                    },
                    iconSize: 'outline'
                });
            return this;
        },
        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {
            this.queryParams = param;
        },
        /**
         * 设置分页方式：server 或者 client
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query params specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        }
    };

    window.BSTable = BSTable;
    
    // 是否根据Class名称来排序
    BSTable.isByClassSort = function(fn){
    	if (fn === true){
    		setSortAttr('sort_name');
    	}else{
    		setSortAttr('field');
    	}
    }
    
    function  setSortAttr(fn) {
    	var BootstrapTable = $.fn.bootstrapTable.Constructor;
        BootstrapTable.prototype.onSort = function (event) {
            var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
                $this_ = this.$header.find('th').eq($this.index()),
                sortName = this.header.sortNames[$this.index()];

            this.$header.add(this.$header_).find('span.order').remove();

            if (this.options.sortName === $this.data(fn)) {
                this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
            } else {
                this.options.sortName = sortName || $this.data(fn);
                this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
            }
            this.trigger('sort', this.options.sortName, this.options.sortOrder);

            $this.add($this_).data('order', this.options.sortOrder);

            // Assign the correct sortable arrow
            this.getCaret();

            if (this.options.sidePagination === 'server') {
                this.initServer(this.options.silentSort);
                return;
            }

            this.initSort();
            this.initBody();
        };
        BootstrapTable.prototype.getCaret = function () {
            var that = this;

            $.each(this.$header.find('th'), function (i, th) {
                var sortName = that.header.sortNames[i];
                $(th).find('.sortable').removeClass('desc asc').addClass((sortName || $(th).data(fn)) === that.options.sortName ? that.options.sortOrder : 'both');
            });
        };
    }
}());