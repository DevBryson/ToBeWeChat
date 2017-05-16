package cn.bisondev.tobewechat.bean;

/**
 * 第一个微信导航的item bean类
 * Created by Bison on 2017/5/12.
 */

public class ChatWindowBean {

    private String avactor;
    private String chatName;
    private String chatContent;
    private String chatTime;
    private int chatNotify;

    public String getAvactor(){
        return avactor;
    }

    public void setAvactor(String avactor){
        this.avactor = avactor;
    }

    public int getChatNotify(){
        return chatNotify;
    }

    public void setChatNotify(int chatNotify){
        this.chatNotify = chatNotify;
    }

    public String getChatName(){
        return chatName;
    }

    public void setChatName(String chatName){
        this.chatName = chatName;
    }

    public String getChatContent(){
        return chatContent;
    }

    public void setChatContent(String chatContent){
        this.chatContent = chatContent;
    }

    public String getChatTime(){
        return chatTime;
    }

    public void setChatTime(String chatTime){
        this.chatTime = chatTime;
    }

}
