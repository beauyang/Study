/**
 * Created by young on 2017/2/9.
 */
var drag = function (target, event) {
//    定义开始拖动的位置（相对于window坐标）
//
    var startX = event.clientX;
    var startY = event.clientY;
//    定义要被拖动的位置（相对于document坐标）
//
    var oriaX = target.offsetLeft;
    var oriaY = target.offsetTop;
//    计算windows坐标系和document坐标系之间的偏移
    var deltaX = startX - oriaX;
    var deltaY = startY - oriaY;
//    设置该元素直接捕获该事件
    target.setCapture();
    //鼠标移动处理器
    var moveHandler = function () {
        var evt = window.event;
    //    将被拖动的位置移动到当前鼠标位置
        target.style.left = (evt.clientX - deltaX) + 'px';
        target.style.top = (evt.clientY - deltaY) + 'px';
    //    阻止事件冒泡
        evt.cancelBubble = true;
    };
    //鼠标停止处理器
    var upHandler = function () {
        var evt = window.event;
        target.detachEvent('onlosecapture', upHandler);
        target.detachEvent('onmouseup', upHandler);
        target.detachEvent('onmousemove', moveHandler);
        // 释放该对象的“事件捕获”
        target.releaseCapture();
        //阻止事件冒泡
        evt.cancelBubble = true;
    }
    //为该元素鼠标移动时绑定事件处理器
    target.attachEvent('onmousemove', moveHandler);
    target.attachEvent('onmouseup', upHandler);
    target.attachEvent('onlosecapture', upHandler);
    //阻止事件冒泡
    event.cancelBubble = true;
    event.returnValue = false;
}