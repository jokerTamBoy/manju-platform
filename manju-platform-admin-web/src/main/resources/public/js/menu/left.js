//类定义
manju.Package("manju.indexLeft");

manju.index.indexLeft = function(){

    return {
        init:function(){
            console.log("adsfadsf")
        }
    }


}();

//初始化
manju.ExecWait(function(){
    manju.index.indexLeft.init();
});