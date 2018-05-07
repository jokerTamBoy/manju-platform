//类定义
manju.Package("manju.index");

manju.index.indexContent = function(){

    return {
        init:function(){
            console.log("1234123123");
        }
    }
}();

//初始化
manju.ExecWait(manju.index.indexContent.init);