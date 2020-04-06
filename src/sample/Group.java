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

    public void testowwa(){}

    public String getGroupName() {
        int k;
        int w;
        return groupName;
    }

    public void addClient(String nameClient, int socket){
        nameClientMap.put(nameClient, socket);
    }


}
