package sample;// A Java program for a Server


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Serializable
{
    private ServerSocket    server   = null;


    public class ClientService implements Runnable
    {

        private Socket socket;
        public DataInputStream  input;
        public DataOutputStream out;
        public ObjectInputStream objectInputStream ;

        private String clientName;

        public ClientService(Socket clientSocket) throws IOException  {
            socket = clientSocket;
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("(nowy watek utworzony)");
        }
        @Override
        public void run() {
            System.out.println("(run)");
            String messg = " ";
            while (true){
                try {
                    System.out.println("(odczyt...)");

                    Stream ourStream = (Stream) objectInputStream.readObject();
                    System.out.println("num: " + ourStream.getType() +" objType:"+ ourStream.getStremObject().getClass());

                    switch(ourStream.getType()) {
                        case 1:
                            clientName = (String) ourStream.getStremObject().toString();
                            break;
                        case 2:
                            // code block
                            break;
                        //default:
                            // code block
                    }

                    System.out.println("ClientName: " + clientName);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("watek sie zakonczyl)");
                    break;
                }
            }
        }

    }

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection 
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
            while(true){
                Socket clientSocket = server.accept();

                Thread t = new Thread(new ClientService(clientSocket));
                t.start();
            }
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
} 