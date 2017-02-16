
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangbo on 2017/2/16.
 * 与浏览器进行数据交换
 * 将此java文件直接运行即可
 */
public class SimpleServer {
    public SimpleServer() throws Exception{
        //1.new ServerSocket
        ServerSocket ss = new ServerSocket(3000);
        //2.accept client connect
        Socket socket = ss.accept();
        //3.get socket InputStream
        InputStream is = socket.getInputStream();
        //4.get socket outputStream
        OutputStream os = socket.getOutputStream();
        byte[] buff = new byte[1024];
        int count = -1;
        String req = "";
        // read data
        count = is.read(buff);
        //make read data to string
        req = new String(buff,0, count);
        System.out.println("shake hands :" + req);
        // get webSocket key
        String secKey = getSecWebSocketKey(req);
        System.out.println("secKey" + secKey);
        String response = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: " + "websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: " + getSecWebSocketAccept(secKey) + "\r\n\r\n";
        System.out.println("secAccept = " + getSecWebSocketAccept(secKey));
        os.write(response.getBytes());
        //再次读取WebSocket发送来的数据
        count = is.read(buff);
        System.out.println("接受的字节数：" + count);
        /*
            因为WebSocket发送过来的数据遵循了一定的协议格式
            其中第3~6个字节是数据掩码
            从第7个字节开始才是真正的有效数据
            因此程序使用第3~6个字节对后面的数据进行了处理
         */
        for (int i = 0 ; i < count - 6 ; i++){
            buff[i + 6] = (byte) (buff[i % 4 + 2] ^ buff[i + 6]);
        }
        //显示读到的数据
        System.out.println("接受到的内容:" + new String(buff, 6 ,count - 6, "utf-8"));
        //发送数据时，第一个字节必须与读到的第一个字节相同
        byte[] pushHead = new byte[2];
        pushHead[0] = buff[0];
        String pushMeg = "收到！收到！欢迎young进入WebSocket世界！";
        //发送数据时，第二个字节记录发送数据的长度
        pushHead[1] = (byte)pushMeg.getBytes("utf-8").length;
        //发送前两个字节
        os.write(pushHead);
        //发送有效数据
        os.write(pushMeg.getBytes("utf-8"));
        //关闭流
        is.close();
        os.close();
        //关闭socket
        socket.close();
        //关闭ServerSocket
        ss.close();
    }
    /**
     * 获取WebSocket请求的secKey
     * @param req
     * @return
     */
    private String getSecWebSocketKey(String req) {
        //构建正则表达式，获取Sec-WebSocket-Key : 后面内容
        Pattern p = Pattern.compile("^(Sec-WebSocket-Key:).+" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(req);
        if (m.find()){
            //提取Sec-WebSocket-Key
            String foundString = m.group();
            return foundString.split(":")[1].trim();
        }else {
            return null;
        }
    }

    /**
     * 根据WebSocket请求的SecKey计算SecAccept
     * @param key
     * @return
     */
    private String getSecWebSocketAccept(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        key +=guid;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(key.getBytes("ISO-8859-1"), 0 , key.length());
        byte [] sha1Hash = md.digest();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(sha1Hash);
    }

    public static void main(String[] args) throws Exception {
        new SimpleServer();
    }
}
