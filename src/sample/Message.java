package sample;

import java.io.Serializable;

public class Message implements Serializable {
    int messageType;
    String messageContent;
    String direction;

    public Message(int type, String content){
        messageType = type;
        messageContent = content;
    }

    public int getMessageType() {
        return messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
