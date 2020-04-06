package sample;

public class Message {
    int messageType;
    String messageContent;


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

}
