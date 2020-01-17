import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try  {
            //监听端口8888
            ServerSocket serverSocket = new ServerSocket(8888);
            //等待客户端的请求
            Socket socket = serverSocket.accept();
            //输出流
            OutputStream outputStream =socket.getOutputStream();
            //传输数据流
            PrintWriter printWriter = new PrintWriter(outputStream);
            //输入流
            InputStream inputStream = socket.getInputStream();
            //缓存输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
