package sample;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client
{
    // initialize socket and input output streams 
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    private ObjectOutputStream objectOutputStream = null;
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

            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream());

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
        String line = "";

        // keep reading until "Over" is input 
        while (!line.equals("Over"))
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                line = input.readLine();

                Stream sendStream = new Stream(num,new String(line));

                objectOutputStream.writeObject(sendStream);
                System.out.println("wysylano: " + sendStream.getType() +":"+ sendStream.getStremObject().getClass());
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection 
        try
        {
            objectOutputStream.close();
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}