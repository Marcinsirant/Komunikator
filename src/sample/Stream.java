package sample;

import java.io.Serializable;

public class Stream implements Serializable {
    private int type;
    private Object streamObject;

    public Stream(int type, Object obj){
        this.type = type;
        streamObject = obj;
    }

    public int getType() {
        return type;
    }

    public Object getStreamObject() {
        return streamObject;
    }

    public void setStreamObject(Object streamObject) {
        this.streamObject = streamObject;
    }

    public void setType(int type) {
        this.type = type;
    }
}
