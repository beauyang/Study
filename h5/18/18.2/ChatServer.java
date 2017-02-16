



import sun.misc.BASE64Encoder;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangbo on 2017/2/16.
 * 基于WebSocket的多人实时聊天 服务端
 *
 */
public class ChatServer {
    //记录所有的客户端Socket
    public static List<Socket> clientSockets = new ArrayList<>();

    public ChatServer() throws IOException {
        //创建ServerSocket，准备接受客户端连接
        ServerSocket ss = new ServerSocket(3000);
        while (true){
            //接收客户端连接
            Socket socket = ss.accept();
            //将客户端Socket添加到clientSockets集合中
            clientSockets.add(socket);
            // 启动线程
            new ServerThread(socket).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer();
    }
}
class ServerThread extends Thread{
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            // 得到Socket对应的输入流
            InputStream is = socket.getInputStream();
            // 的到Socket对应的输出流
            OutputStream os = socket.getOutputStream();
            byte[] buff = new byte[1024];
            String req = "";
            // 读取数据，此时建立与WebSocket的“握手”
            int count = is.read(buff);
            // 如果读取的长度大于0;
            if(count > 0){
                //将读取的数据转化成字符串
                req = new String(buff , 0 , count);
                System.out.println("握手请求:" + req);
                //获取WebSocket的key
                String secKey = getSecWebSocketKey(req);
                System.out.println("secKey = " + secKey);
                String response = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: " + "websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: " + getSecWebSocketAccept(secKey) + "\r\n\r\n";
                System.out.println("secAccept = " + getSecWebSocketAccept(secKey));
                System.out.println("WebSocketAccept : " + getSecWebSocketAccept(secKey));
                os.write(response.getBytes());
            }
            int hasRead = 0;
            // 不断读取WebSocket发送过来的数据
            while ((hasRead = is.read(buff)) > 0){
                System.out.println("接收的字节数:" + hasRead);
                 /*
                    因为WebSocket发送过来的数据遵循了一定的协议格式
                    其中第3~6个字节是数据掩码
                    从第7个字节开始才是真正的有效数据
                    因此程序使用第3~6个字节对后面的数据进行了处理
                */
                for (int i = 0 ; i < hasRead - 6 ; i++){
                    buff[i + 6] = (byte) (buff[i % 4 + 2] ^ buff[i + 6]);
                }
                // 获得从浏览器发送过来的数据
                String pushMeg = new String(buff , 6 , hasRead - 6 , "utf-8");
                // 遍历Socket集合，以此向每个Socket发送数据
                for (Iterator<Socket> it = ChatServer.clientSockets.iterator() ; it.hasNext();){
                    try {
                        Socket socket = it.next();
                        // 发送数据时，第一个字节必须与读到的第一个字节相同
                        byte[] pushHead = new byte[2];
                        pushHead[0] = buff[0];
                        // 发送数据时，第二个字节记录发送数据的长度
                        pushHead[1] = (byte) pushMeg.getBytes("utf-8").length;
                        // 发送前两个字节
                        socket.getOutputStream().write(pushHead);
                        // 发送有效数据
                        socket.getOutputStream().write(pushMeg.getBytes("utf-8"));
                    }catch (SocketException e){
                        // 如果捕获到异常，则表明Socket已经关闭
                        // 将该Socket从Socket集合中剔除
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭Socket
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String getSecWebSocketAccept(String key) throws Exception {
        String guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        key +=guid;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(key.getBytes("ISO-8859-1"), 0 , key.length());
        byte [] sha1Hash = md.digest();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(sha1Hash);
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
}