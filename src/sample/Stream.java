package sample;

import java.io.Serializable;

public class Stream implements Serializable {
    private int type;
    private Object stremObject;

    public Stream(int type, Object obj){
        this.type = type;
        stremObject = obj;
    }

    public int getType() {
        return type;
    }

    public Object getStremObject() {
        return stremObject;
    }

    public void setStremObject(Object stremObject) {
        this.stremObject = stremObject;
    }

    public void setType(int type) {
        this.type = type;
    }
}
