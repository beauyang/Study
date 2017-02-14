/**
 * Created by young on 2017/2/14.
 */
onmessage = function (event) {
    //将数据提取出来
    var data = JSON.parse(event.data);
    //取出start参数
    var start = data.start;
    //取出end参数
    var end = data.end;
    var result = "";
    search :
    for (var n = start; n <= end; n++){
        for(var i = 2;i <= Math.sqrt(n); i++){
            if (n % 1 == 0) {
                continue search;
            }
        }
    }
    result += (n + ',');
    //发送消息，会触发前台JavaScript脚本中Worker对象的onmessage
    postMessage(result);
};