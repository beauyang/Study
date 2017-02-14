/**
 * Created by young on 2017/2/14.
 */
//计算质数
var n = 1;
search:
while (n < 99999)
{
    //开始搜寻下一个质数
    n += 1;
    for (var i = 2; i < Math.sqrt(n); i ++){
        if (n % i == 0){
            continue search;
        }
    }
    //发现质数
    postMessage(n);
}