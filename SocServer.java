import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocServer {

    private ServerSocket serverSocket;
    public SocServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new client connected! ");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch(IOException e){

        }
    }
    public void closedServerSocket(){
       try{
        if(serverSocket != null){
            serverSocket.close();
        }
       } catch(IOException e){
        e.printStackTrace();
       }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3535);
        SocServer server_1 = new SocServer(serverSocket);
        server_1.startServer();
    }
}