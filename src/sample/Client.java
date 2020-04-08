package sample;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client
{
    // initialize socket and input output streams 
    private Socket socket            = null;
    private DataInputStream  input   = null;

    private ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    // constructor to put ip address and port 
    public Client(String address, int port)
    {
        // establish a connection 
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);



            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input 
      //  String line = "";

        // keep reading until "Over" is input 
//        while (!line.equals("Over"))
//        {
//            try
//            {
//                Scanner scanner = new Scanner(System.in);
//                int num = scanner.nextInt();
//                line = input.readLine();
//
//                Stream sendStream = new Stream(num,new String(line));
//
//                objectOutputStream.writeObject(sendStream);
//                System.out.println("wysylano: " + sendStream.getType() +":"+ sendStream.getStremObject().getClass());
//
//                if(num == 2){
//                    //objectInputStream = new ObjectInputStream(socket.getInputStream());
//                   //Stream ourStream = (Stream) objectInputStream.readObject();
//                    // System.out.println("num: " + ourStream.getType() +" objType:"+ ourStream.getStremObject().getClass());
//                }
//
//
//            }
//            catch(IOException i)
//            {
//                System.out.println(i);
//            }
//        }

        // close the connection 
//        try
//        {
//            objectOutputStream.close();
//            input.close();
//            socket.close();
//        }
//        catch(IOException i)
//        {
//            System.out.println(i);
//        }
    }

    public void userLogin(String name) throws IOException {
        Stream sendStream = new Stream(1, new String(name));
        objectOutputStream.writeObject(sendStream);
    }

    public void userCreateOrAddGroup(String groupName) throws IOException {
        Stream sendStream = new Stream(2, new String(groupName));
        objectOutputStream.writeObject(sendStream);
    }

}