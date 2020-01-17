import com.sun.media.sound.SoftJitterCorrector;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.awt.windows.WBufferStrategy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.security.spec.RSAOtherPrimeInfo;

public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;

    /**
     * 构造方法，用来初始化服务器
     */
    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动成功！！！！");
    }

    /**
     * 输出信息，下文要使用
     * @param msg
     * @return
     */
    public String echo(String msg) {
        return "echo：" + msg;
    }

    /**
     * 获取输出流
     * @param socket
     * @return
     * @throws IOException
     */
    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream, true);
    }

    /**
     * 获取输入流
     * @param socket
     * @return
     * @throws IOException
     */
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * 主要方法，用来创建服务
     */
    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("新的连接：" + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader bufferedReader = getReader(socket);
                PrintWriter printWriter = getWriter(socket);

                //接受客户端信息并输出信息，如果信息为bey，则不输出并结束循环
                String msg = null;
                while ((msg = bufferedReader.readLine()) != null) {
                    System.out.println(msg);
                    printWriter.println(echo(msg));
                    if (msg.equals("bey")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //无论如何都要执行的，关闭socket
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new EchoServer().service();
    }

}
