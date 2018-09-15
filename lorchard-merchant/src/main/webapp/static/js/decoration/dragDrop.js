var dest = document.getElementById("dest");
//开始拖动事件的事件监听器
var dsHandler = function(evt){
    //将被拖动元素的innerHTML属性值设置成被拖动的数据
    evt.dataTransfer.setData("text/plain","<item>" + evt.target.innerHTML);
}
dest.ondrop = function(evt){
    var text = evt.dataTransfer.getData("text/plain");
    //如果text以<item>开头
    if(text.indexOf("<item>") == 0){
        //创建一个新的div元素
        var newEle = document.createElement("div");
        //以当前时间为该元素生成一个唯一的ID
        newEle.id = new Date().getUTCMilliseconds();
        //该元素内容为“拖”过来的数据
        newEle.innerHTML = text.substring(6);
        //设置该元素允许拖动
        newEle.draggable = "true";
        //为该元素的开始拖动事件指定监听器
        newEle.ondragstart = function(evt){
            //将被拖动元素的id属性值设置成被拖动的数据
            evt.dataTransfer.setData("text/plain","<remove>"+newEle.id);
        }
        dest.appendChild(newEle);
    }
}
 // 当把被拖动元素“放”到垃圾桶上时激发该方法。
document.getElementById("gb").ondrop = function(evt){
    var id = evt.dataTransfer.getData("text/plain");
    // 如果id以<remove>开头
    if (id.indexOf("<remove>") == 0)
    {
        // 根据“拖”过来的数据，获取被拖动的元素
        var target = document.getElementById(id.substring(8));
        // 删除被拖动的元素
        dest.removeChild(target);
    }
}
document.ondragover = function(evt){
    // 取消事件的默认行为
    return false;
}
document.ondragleave = function(evt){
//取消被拖动的元素离开本元素时触发该事件
    return false;
}
document.ondrop = function(evt){
    // 取消事件的默认行为
    return false;
}

