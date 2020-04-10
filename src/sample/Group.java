package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Group implements Serializable {
    String groupName;
    Map<String, Socket> nameClientMap = new HashMap<String, Socket>();

    public Group(String name){
        groupName=name;

    }

    public Group(Group name){
        this.groupName=name.getGroupName();
        this.nameClientMap=name.getNameClientMap();
    }

    public void testowwa(){}

    public String getGroupName() {
        return groupName;
    }


    public void addClient(String nameClient, Socket socket){
        nameClientMap.put(nameClient, socket);
    }

    public Map<String, Socket> getNameClientMap() {
        return nameClientMap;
    }

    ObservableList<String> getObservableListWithNamesUsers(){
        ObservableList<String> list = FXCollections.observableArrayList();
        nameClientMap.forEach((string,socket)->{
            list.add(string);
        });
        System.out.println("list size = "+list.size());
        return list;
    }

     ArrayList<String> getArrayListWithNamesUsers(){
         ArrayList<String> list = new ArrayList<String>();
         nameClientMap.forEach((string,socket)->{
             list.add(string);
         });
         System.out.println("list size = "+list.size());
         return list;
     }
}
