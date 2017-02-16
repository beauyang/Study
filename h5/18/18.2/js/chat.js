/**
 * Created by yangbo on 2017/2/16.
 */
var webSocket = new WebSocket("ws://127.0.0.1:3000");
webSocket.onopen = function () {
    //为onmessage事件绑定监听器，接收消息
    webSocket.onmessage = function (event) {
        //接受并显示消息
        document.getElementById('show').innerHTML += event.data + '<br/>';
    }
};
var sendMsg = function (val) {
    var inputElement = document.getElementById('msg');
    //发送消息
    webSocket.send(inputElement.value);
    //清空单文本框
    inputElement.innerHTML = "";
}