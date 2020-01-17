import javax.print.DocFlavor;
import java.io.*;
import java.net.Socket;

public class EchoClient {
    private String host = "localhost";
    private int port = 8000;
    private Socket socket;

    /**
     * 构造方法
     *
     * @throws IOException
     */
    public EchoClient() throws IOException {
        socket = new Socket(host, port);
    }

    /**
     * 获取输出流
     *
     * @param socket
     * @return
     * @throws IOException
     */
    private PrintWriter getWrite(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream, true);
    }

    /**
     * 获取输入流
     *
     * @param socket
     * @return
     * @throws IOException
     */
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * 主要方法，发送信息到客户端
     *
     * @throws IOException
     */
    public void talk() throws IOException {
        try {
            BufferedReader bufferedReader = getReader(socket);
            PrintWriter printWriter = getWrite(socket);

            //获取本地输入，缓存到buffer中
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));


            String msg = null;
            while ((msg = localReader.readLine()) != null) {
                printWriter.println(msg);
                System.out.println(bufferedReader.readLine());
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

    public static void main(String[] args) throws IOException {
        new EchoClient().talk();
    }
}
