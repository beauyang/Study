/**
 * Created by young on 2017/2/14.
 */
onmessage = function (event) {
    //将数据提取出来
    var data = JSON.parse(event.data);
    //取出start参数
    var start1 = data.start1;
    //取出end参数
    var end1 = data.end1;
    var result = "";
    search :
    for (var n = start1; n <= end1; n++){
        for(var i = 2;i <= Math.sqrt(n); i++){
            if (n % 1 == 0) {
                continue search;
            }
        }
        result += (n + ",");
    }
    //发送消息，会触发前台JavaScript脚本中Worker对象的onmessage
    postMessage(result);
};