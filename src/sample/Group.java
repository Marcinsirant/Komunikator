package sample;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Group implements Serializable {
    String groupName;
    Map<String, Socket> nameClientMap = new HashMap<String, Socket>();

    public Group(String name){
        groupName=name;

    }

    public void testowwa(){}

    public String getGroupName() {
        return groupName;
    }


    public void addClient(String nameClient, Socket socket){
        nameClientMap.put(nameClient, socket);
    }


}
