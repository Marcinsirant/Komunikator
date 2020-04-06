package sample;

import java.util.Map;

public class Group {
    String groupName;
    Map<String, Integer> nameClientMap;

    public Group(String name){
        groupName=name;
        nameClientMap=null;
    }

    public  Group(String name,  Map<String, Integer> map){
        groupName=name;
        nameClientMap = map;
    }

    public String getGroupName() {
        return groupName;
    }

    public void addClient(String nameClient, int socket){
        nameClientMap.put(nameClient, socket);
    }


}
