package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client
{
    // initialize socket and input output streams 
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private String actualGroup; //aktualna grupa w czacie
    private ClientMessageReceive t;
    private Controller controller;
    private Map<String, ChatPageController> controllechatMaps =  new HashMap<String, ChatPageController>();
    private ChatPageController controllerChat = null;
    private String userName;
    private ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    // constructor to put ip address and port 
    public Client(String address, int port, Controller controller)
    {
        // establish a connection 
        try
        {

            this.controller = controller;
            socket = new Socket(address, port);

            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);



            objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
            objectInputStream= new ObjectInputStream(socket.getInputStream());

            t = new ClientMessageReceive(socket, objectInputStream);
            t.start();

        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

    }

    public void setControllerChat(ChatPageController controllerChat, String nameGroup) {
        this.controllerChat = controllerChat;
        this.controllechatMaps.put(nameGroup,controllerChat);
    }

    public String getUserName() {
        return userName;
    }

    public String getActualGroup() {
        return actualGroup;
    }

    public void setActualGroup(String actualGroup) {
        this.actualGroup = actualGroup;
    }

    public void userLogin(String name) throws IOException {
        userName = name;
        Stream sendStream = new Stream(1, AES.encrypt(name, socket.getInetAddress().toString()));
        objectOutputStream.writeObject(sendStream);
    }

    public void userCreateOrAddGroup(String groupName) throws IOException {
        Stream sendStream = new Stream(2, AES.encrypt(groupName, socket.getInetAddress().toString()));
        objectOutputStream.writeObject(sendStream);
    }

    public void getUsersInGroupFromServer(String groupName) throws IOException {
        Stream sendStream = new Stream(3, AES.encrypt(groupName, socket.getInetAddress().toString()));
        objectOutputStream.writeObject(sendStream);
    }

    public void sendExitUser() throws IOException {
        Stream sendStream = new Stream(6, AES.encrypt(userName, socket.getInetAddress().toString()));
        objectOutputStream.writeObject(sendStream);
    }

   public void setUsersInGroupFromServer(Stream readStream) {
        ArrayList<String> list = (ArrayList<String>)readStream.getStremObject();
        ObservableList<String> listO = FXCollections.observableArrayList(list);
        System.out.println("jestem tutaj: " + readStream.getType() +" ilosc osob: "+listO.size());

        controller.getChatTableView().setItems(listO);
        if(controllerChat!=null){
            controllerChat.getChatTableView().setItems(listO); // actualize usernames in chatpage
        }

    }

    public void userSendMessage(Message newMessage) throws IOException {
        Stream sendStream = new Stream(4, newMessage);
        objectOutputStream.writeObject(sendStream);

    }

    public class ClientMessageReceive implements Runnable{
        public Thread t;
        private Socket socket;
        private ObjectInputStream objectInputStream;

        public ClientMessageReceive(Socket clientSocket, ObjectInputStream getInputStream) {
            this.socket = clientSocket;
            this.objectInputStream = getInputStream;
        }

        public void start(){
            System.out.println("Wątek odbierania wiadomości");
            if(t == null) {
                t = new Thread(this);
                t.start();
            }
        }

        @Override
        public void run() {
            System.out.println("(run)");
            while(true){
                try {
                    System.out.println("(odczyt...)");
                    Stream ourStream = (Stream) objectInputStream.readObject();
                    System.out.println("num: " + ourStream.getType() +" objType:"+ ourStream.getStremObject().getClass());
                    switch(ourStream.getType()){
                        case 3:
                            System.out.println("Lista grup");
                            setUsersInGroupFromServer(ourStream);
                            break;
                        case 5:
                            //receive message
                            Message receiveMessage = (Message) ourStream.getStremObject();
                            System.out.println("Wiadomość");
                            System.out.println(receiveMessage.getSource());
                            System.out.println(receiveMessage.getDirection());
                            System.out.println(receiveMessage.getMessageContent());
                            //controller.addMessageToTextArea(receiveMessage);
                            // trzeba zrobic mape kontrolerow z ich nawami
                            for(Map.Entry<String, ChatPageController> entry : controllechatMaps.entrySet()){
                                if(entry.getKey().equals(receiveMessage.getDirection())){
                                    entry.getValue().addMessageToTextArea(receiveMessage);
                                }

                            }
                            //controllerChat.addMessageToTextArea(receiveMessage);

                            break;
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}