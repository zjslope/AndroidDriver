package com.example.slope.androiddriver.basicclass;

/**
 * Created by Slope on 2016/9/13.
 */
public class Msg {
    public static final int TYPE_RECEIVED=0;//收到消息
    public static final int TYPE_SENT=1;//发送消息
    private String content;//消息的内容
    private int type;//消息的类型

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
