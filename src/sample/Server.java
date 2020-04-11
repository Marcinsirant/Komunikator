package sample;// A Java program for a Server


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Server implements Serializable
{
    private ServerSocket    server   = null;
    List<Group> groupList = new ArrayList<Group>() ;

    public class ClientService implements Runnable
    {

        private Socket socket;

        private ObjectOutputStream objectOutputStream = null;

        public ObjectInputStream objectInputStream = null ;

        private String clientName;


        public ClientService(Socket clientSocket) throws IOException  {
            socket = clientSocket;

            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream= new ObjectOutputStream(socket.getOutputStream());

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
                            addOrJoinGroup((String) ourStream.getStremObject().toString(), clientName, objectOutputStream);
                            break;
                        case 3:
                            int el = 0;
                            for(Group e: groupList ){
                                if(e.groupName.equals( (String) ourStream.getStremObject().toString() )){
                                   el = groupList.indexOf(e);
                                   System.out.println("wysylam: "+e.getGroupName());
                                };
                            }
                            objectOutputStream.writeObject(new Stream(3, groupList.get(el).getArrayListWithNamesUsers()));

                            break;
                        case 4:
                            receiveMessage((Message) ourStream.getStremObject());
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

    public void addOrJoinGroup(String group, String nameUser, ObjectOutputStream socket) throws IOException {
        Group index = null;
        for (Group el : groupList) {
            if(el.getGroupName().equals(group)){
                index = el;
                el.addClient(nameUser,socket);
            };
        }

        if(index ==null){
            Group gr = new Group(group);
            gr.addClient(nameUser, socket);
            groupList.add(gr);
        }

        groupList.forEach((o)->{ System.out.println("grupy: " + o.getGroupName() +" uzytkownicy w grupie: " + o.getNameClientMap() );});


    };

    public void receiveMessage(Message message) throws IOException{
        System.out.println(message.getDirection());
        System.out.println(message.getMessageContent());
        System.out.println(message.getMessageType());
        for(Group e: groupList ){
            if(e.groupName.equals(message.getDirection())){
                System.out.println("Wysyłanie wiadomości");
                sendMessageToUsers(e, message);
            }
        }
    }

    public void sendMessageToUsers(Group group, Message message) throws IOException{
        Map<String, ObjectOutputStream> listSockets = group.getNameClientMap();

        for(Map.Entry<String, ObjectOutputStream> entry : listSockets.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
            System.out.println("Wiadomość: " + message.getMessageContent() + " od: " + message.getSource() + " do: " + message.getDirection());
            Stream sendMessage = new Stream(5, message);
            entry.getValue().writeObject(sendMessage);
        }

    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
} 