
var manju = function() {
    var _TableDictData = {};//用于保证库表字黄
    var _TableDictDataList = {};//用于保证库表字黄
	var _DATEC__ = {
		yy : "FullYear",
		mm : "Month",
		dd : "Date",
		wk : "Date",
		dw : "Day",
		hh : "Hours",
		mi : "Minutes",
		ss : "Seconds",
		ms : "Milliseconds"
	};
	Date.prototype.get = function(b) {
		return (_DATEC__[b]) ? eval("this.get" + _DATEC__[b] + "()"
				+ ((b != "mm") ? "" : "+1")) : null;
	};
	Date.prototype.set = function(b, c) {
		if ((c % 1) != 0)
			throw new Error(0, "is not an integer");
		if (_DATEC__[b])
			eval("this.set" + _DATEC__[b] + "(c" + ((b != "mm") ? "" : "-1")
					+ ")");
	};
	Date.prototype.add = function(b, c) {
		if ((c % 1) != 0)
			throw new Error(0, "is not an integer");
		if (_DATEC__[b])
			eval("this.set" + _DATEC__[b] + "(this.get" + _DATEC__[b] + "()+c"
					+ ((b != "wk") ? "" : "*7") + ")");
	};
	Date.prototype.diff = function(b, d) {
		if (!(d instanceof Date))
			return null;
		switch (b) {
		case "yy":
			return eval("this.get" + _DATEC__[b] + "()-d.get" + _DATEC__[b]
					+ "()");
		case "mm":
			return eval("(this.get" + _DATEC__["yy"] + "()-d.get"
					+ _DATEC__["yy"] + "())*12+this.get" + _DATEC__[b]
					+ "()-d.get" + _DATEC__[b] + "()");
		case "dd":
			return Math.floor((this.getTime() - d.getTime())
					/ (1000 * 60 * 60 * 24));
		case "hh":
			return Math
					.floor((this.getTime() - d.getTime()) / (1000 * 60 * 60));
		case "mi":
			return Math.floor((this.getTime() - d.getTime()) / (1000 * 60));
		case "ss":
			return Math.floor((this.getTime() - d.getTime()) / 1000);
		case "ms":
			return (this.getTime() - d.getTime());
		}
		;
	}
	
	/*
	 * 对Date的扩展，将 Date 转化为指定格式的String
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)可以用 1-2 个占位符，年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	 * 例子：
	 * (new Date()).format("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423
	 * (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
	 * (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
	 * (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
	 * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
	 * */
	Date.prototype.format=function(fmt) {         
	    var o = {         
	    "M+" : this.getMonth()+1, //月份         
	    "d+" : this.getDate(), //日         
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
	    "H+" : this.getHours(), //小时         
	    "m+" : this.getMinutes(), //分         
	    "s+" : this.getSeconds(), //秒         
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
	    "S" : this.getMilliseconds() //毫秒         
	    };         
	    var week = {         
	    "0" : "/u65e5",         
	    "1" : "/u4e00",         
	    "2" : "/u4e8c",         
	    "3" : "/u4e09",         
	    "4" : "/u56db",         
	    "5" : "/u4e94",         
	    "6" : "/u516d"        
	    };         
	    if(/(y+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
	    }         
	    if(/(E+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
	    }         
	    for(var k in o){         
	        if(new RegExp("("+ k +")").test(fmt)){         
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
	        }         
	    }         
	    return fmt;         
	} 

//	Date.prototype.toString = function() {
//		return manju.DateToString(this, true);
//	};
//	
	
	
    
	var _modalWinObj = {};// 模态窗口使用
	
	var getModalHTML = function(eleId,eleContentId,modalClass,width){
		var html = [];
		html.push('<div id="'+eleId+'" class="modal fade">');
		//高度是自动撑开，所以不用加进来
		//宽度可支持 百分比 和 px
		if(!manju.IsNull(width)){
			var style = 'style="width:'+width+';"';
			html.push('<div class="modal-dialog '+modalClass+'" '+style+'>');//modal-lg
		}else{
			html.push('<div class="modal-dialog '+modalClass+'">');//modal-lg
		}
		html.push('<div class="modal-content" id="'+eleContentId+'">');
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		return html.join("");
	}
	
	var getModalId = function(url){
		var _id = url.replace(new RegExp('/','gm'),'-').replace('.','-');
		var id = 'modal'+_id;
		return id;
	}
	
	return {
		// 执行等待、后期将增加进度条
		ExecWait : function(func) {
			$(document).ready(function(){
				//try catch 是为了解决一个界面js报错 导致其他界面加载时候js不再运行
				try{
					func();
				}catch (e){
					console.info(e);
				}
			});
		},
		
		/* 将o2合并到o1中 */
		MergeAttrs : function(o1, o2) {
			$.extend(o1, o2);
		},

		Package : function(className) {
			var classNameArr = className.split(".");
			for (var i = 1; i < classNameArr.length; i++) {
				var tmp = [];
				for (var j = 0; j <= i; j++) {
					tmp.push(classNameArr[j]);
				}
				var classNameStr = tmp.join(".");
				// alert(classNameStr);

				eval("if(" + classNameStr + "==null) " + classNameStr + "={}");
			}
		},

		/* 转为数字 */
		ToNumber : function(arg) {
			if (arg == null || isNan(arg) || typeof (reValue) == undefined) {
				return null;
			} else {
				return Number(arg);
			}
		},

		/* 日期转字符串 */
		DateToString : function(d, useTime) {
			if (d) {
				return d.getFullYear()
						+ "-"
						+ manju.StrZeroAdd(d.getMonth() + 1)
						+ "-"
						+ manju.StrZeroAdd(d.getDate())
						+ ((useTime) ? " " + manju.StrZeroAdd(d.getHours()) + ":"
								+ manju.StrZeroAdd(d.getMinutes()) + ":"
								+ manju.StrZeroAdd(d.getSeconds()) : "");
			} else {
				return "";
			}
		},

		/* 字符串转日期 */
		StringToDate : function(s) {
			var s = s.substring(0, 19);
			var aD = s.split(/[\/\-: ]/);
			if (aD.length < 3) {
				return null;
			}
			if (aD.length < 4)
				aD[3] = aD[4] = aD[5] = "00";
			var d = new Date(aD[0], parseInt(aD[1] - 1, 10), aD[2], aD[3],
					aD[4], aD[5]);
			if (isNaN(d)) {
				return null;
			}
			return d;
		},

		/* 转为json字符串 */
		ToJson : function(obj) {
			return JSON.stringify(obj);
		},
		
		/* json字符串转对象  */
		ParseJson:function(msg){
			return 	$.parseJSON(msg);
		},

		/* 去除空格 */
		Trim : function(s) {
			return ((typeof (s) != "string") ? "" : s.replace(/(^\s*)|(\s*$)/g,
					""));
		},

		/* 空格替换 */
		NullRepl : function(inStr, repStr) {
			return (inStr != null) ? inStr : repStr;
		},

		/* 空格替换 */
		BlankRepl : function(s) {
			return (typeof (s) == "string" && s != '') ? s : null;
		},

		/* 字符串替换 */
		StrRepl : function(inStr, orgStr, repStr) {
			return (inStr != orgStr) ? inStr : repStr;
		},

		/* 是否为空 */
		IsNull : function(inStr) {
			return (inStr == null || inStr == "" || typeof (inStr) == "undefined") ? true
					: false;
		},

		/* 获取参数，暂时不用 */
		GetUrlParameter : function(param) {
			var url = window.location.search;
			var pos1 = 0, pos2 = 0;
			pos1 = url.indexOf("&" + param + "=");
			if (pos1 < 0)
				pos1 = url.indexOf("?" + param + "=");
			if (pos1 > -1) {
				pos2 = url.indexOf("&", pos1 + 1);
				if (pos2 == -1)
					pos2 = url.length;
				return url.substring(pos1 + param.length + 2, pos2);
			} else
				return null;
		},

		/* 告警 */
		Alert : function(msg) {
			bootbox.alert(msg);
		},

		/* 确认窗口 */
		Confirm : function(msg, fn) {
			bootbox.confirm(msg, function(result) {
				fn(result);
			});
		},
		

		
		/*根据id获取对象，要保证id全局唯一性*/
		GetObj : function(id){
			return $('#'+id);
		},
		

		
		/* 远程方法调用--未测试  */
		InvokeMethod:function(url,paramObj){
			var ret = null;
			$.ajax({
				url : url,
				type: 'POST', 
				async : false,
				data : paramObj,
				//dataType: 'json',
				success : function(msg) {
					console.log(msg)
					ret = msg;
				}

			});
			return ret;
		},
		
		/* 远程方法调用--未测试  */
		InvokeMethodAsyn:function(url,paramObj,fn){
			$.ajax({
				url : url,
				type: 'POST', 
				async : true,
				data : paramObj,
				dataType: 'json',
				success : function(msg) {
					fn(msg);
				}
			});
		},
		
		StrZeroAdd : function(s) {
			s = "0" + s;
			return s.substr(s.length - 2);
		},
		
		/* 用于点击菜单打开窗口  */
		OpenPage:function(url,mid,title){
			window.href

			if(false){
				$('#mainContent').bTabsAdd(mid,title,url);
			}else{
				
			$("#mainContent").load(url);
			}
		},
		
		/* 用于点击菜单打开窗口，可带参数和回调方法  */
		OpenPageLoad:function(url,data,func){
			$("#mainContent").load(url,data,func);
		},

        /* 用于点击菜单打开窗口，可带参数和回调方法  */
        OpenPageLoad2:function(url,data,target,func){
        	$("#"+target).load(url,data,func);
        },
		

		/**
		 * 分解对象
		 */
		ResolveToList : function (tableObj,colCode){
			var obj = tableObj [colCode];
			var list = [];
			for(key in obj){
				list.push({
					colValue:key,
					colText:obj[key]
				})
			}
			
			return list;
		},
		
		/**
		 * 模拟表单提交
		 * 传入参数：对象，对象都是提交的参数
		 * 		  提交的url
		 */
		SubmitForm : function(obj, url, target){
			var form = $('<form></form>');
			form.attr('action', url);
			form.attr('method', 'post');
			if(!manju.IsNull(target)){
				form.attr('target', target);//_blank弹出窗口
			}
			for(key in obj){
				var input = $("<input type='hidden' name='"+key+"' value='"+obj[key]+"'/>");
				form.append(input);  
			}
			$(document.body).append(form);
			form.submit();
		},
		

		/**
		 * 打印，可局部打印，默认全部打印
		 */
		Print : function(Vflag){
			var flag = false;
			if(manju.IsNull(Vflag) || 'true' == Vflag){
				flag = true;
			}
			if(!flag){
				var bdhtml = window.document.body.innerHTML;
				var sprnstr = "<!--startprint-->";    
				var eprnstr = "<!--endprint-->";    
				var prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
				prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
				window.document.body.innerHTML=prnhtml;    
				window.print();  
				window.document.body.innerHTML=bdhtml;    
			}else{
				window.print();  
			}
		},
		

		
		//加法函数
		//arg1,arg2 表示要传入的计算的参数
		//arg3 表示要保留几位小数
		accAdd : function accAdd(arg1,arg2,arg3) {
		    var r1, r2, m;
		    var result;//返回的结果
		    try {
		        r1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r1 = 0;
		    }
		    try {
		        r2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r2 = 0;
		    }
		    m = Math.pow(10, Math.max(r1, r2));
		    
		    result = (arg1 * m + arg2 * m) / m ;
		    
		    if(!manju.IsNull(arg3)){
		    	result = result.toFixed(arg3);
		    }
		    return result;
		} ,
		
		//减法函数
		//arg1,arg2 表示要传入的计算的参数
		//arg3 表示要保留几位小数
		subtr : function subtr(arg1, arg2 ,arg3) {
		    var r1, r2, m, n;
		    var result;
		    try {
		        r1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r1 = 0;
		    }
		    try {
		        r2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		        r2 = 0;
		    }
		    m = Math.pow(10, Math.max(r1, r2));
		     //last modify by deeka
		     //动态控制精度长度
		    result = (arg1 * m - arg2 * m) / m ;
		    if(!manju.IsNull(arg3)){
		    	result = result.toFixed(arg3);
		    }
		    return result;
		},
	
		//乘法函数
		//arg1,arg2 表示要传入的计算的参数
		//arg3 表示要保留几位小数
		accMul : function accMul(arg1, arg2 ,arg3) {
		    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		    var result ;
		    try {
		        m += s1.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    try {
		        m += s2.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    
		    result = Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
		    if(!manju.IsNull(arg3)){
		    	result = result.toFixed(arg3);
		    }
		    return result;
		},
		//除法函数
		//arg1,arg2 表示要传入的计算的参数
		//arg3 表示要保留几位小数
		accDiv : function accDiv(arg1, arg2,arg3) {
		    var t1 = 0, t2 = 0, r1, r2;
		    var result;
		    try {
		        t1 = arg1.toString().split(".")[1].length;
		    }
		    catch (e) {
		    }
		    try {
		        t2 = arg2.toString().split(".")[1].length;
		    }
		    catch (e) {
		    }
		    with (Math) {
		        r1 = Number(arg1.toString().replace(".", ""));
		        r2 = Number(arg2.toString().replace(".", ""));
		        result = (r1 / r2) * pow(10, t2 - t1);
		        if(!manju.IsNull(arg3)){
		        	result = result.toFixed(arg3);
			    }
			    return result;
		    }
		} ,
		
		/**
		 * 正在加载数据的div隐藏
		 */
		WaitDivHidden : function(){
			var loadingModalObj = manju.GetObj("includes-index_backdrop-loadingModal");
			loadingModalObj.modal('hide');
		},
		
		/**
		 * 正在加载数据的div打开
		 */
		WaitDivShow : function(){
			var loadingModalObj = manju.GetObj("includes-index_backdrop-loadingModal");
			//loadingModalObj.modal('show');
			//backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
			//keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
			loadingModalObj.modal({backdrop: 'static', keyboard: false});
		},
		
		//小数转百分数 
		//point 传入小数  n表示保留几位小数  msg传入true 需要加%
		toPercent : function toPercent(point,n,msg){
		    var str=Number(point*100).toFixed(n);
		    if(msg == true){
		    	str+="%";
		    }else{
		    	
		    }
		    
		    return str;
		},

		/**
		 * 编码
		 */
		Encode : function(str){
			var result = $.base64.btoa(str, true);
			return result;
		},
		
		/**
		 * 解码
		 */
		Decode : function(str){
			var result = $.base64.atob(str, true);
			return result;
		},


		// 字典翻译
        getDictTrans : function (type,value){
			if (_TableDictData[type + '&' + value] == null){
                var retult = this.InvokeMethod("dict/selectDictTranslate",{type: type, value: value});
                _TableDictData[type + '&' + value] = retult;
			}
			return _TableDictData[type + '&' + value];
		},

		// 根据类型获取字典列表
        getDictTransList : function (type){
            if (_TableDictDataList[type] == null){
                var retult = this.InvokeMethod("dict/selectDictTranslateList",{type: type, value:0});

                if (retult != null && retult.length > 0)
                	_TableDictDataList[type] = this.ParseJson(retult);
                else
                    _TableDictDataList[type] = null;
            }
            return _TableDictDataList[type];
        },

		// 清空字典
		cleanDict : function (){
			console.log(_TableDictData);
			//console.log(_TableDictDataList);
            _TableDictData = {};
            //_TableDictDataList = {};
		}

	}

}();

$(document).on("click", ".btn-sub-disabled", function () {
	$(this).attr("disabled","disabled");
});
/*
 * function Ch13Encode(s) { return (s) ? s.replace(/ /g, "﹣").replace(/\n/g,
 * "ˉ") : ""; };
 * 
 * function Ch13Decode(s) { return (s) ? s.replace(/﹣/g, " ").replace(/ˉ/g,
 * "\n") : ""; };
 * 
 * function Ch13HTMLDecode(s) { return (s) ? s.replace(/﹣/g,
 * "&nbsp;").replace(/ˉ/g, "<br>") : ""; }; , // // /* 补0
 */

// */
