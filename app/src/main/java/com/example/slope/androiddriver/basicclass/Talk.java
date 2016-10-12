package com.example.slope.androiddriver.basicclass;

/**
 * Created by Slope on 2016/9/16.
 */
public class Talk {

    /**
     * id : 1
     * talk : fdsfds
     * tbuser : null
     * time : null
     * userid : 1
     * username : slope
     */

    private String id;
    private String talk;
    private String tbuser;
    private String time;
    private String userid;
    private String username;

    public Talk(String id, String talk, String tbuser, String time, String userid, String username) {
        this.id = id;
        this.talk = talk;
        this.tbuser = tbuser;
        this.time = time;
        this.userid = userid;
        this.username = username;
    }

    public Talk(String time, String talk, String username) {
        this.time = time;
        this.talk = talk;
        this.username = username;
    }

    public Talk(String talk, String time, String userid, String username) {
        this.talk = talk;
        this.time = time;
        this.userid = userid;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Talk{" +
                "id=" + id +
                ", talk='" + talk + '\'' +
                ", tbuser=" + tbuser +
                ", time=" + time +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getTbuser() {
        return tbuser;
    }

    public void setTbuser(String tbuser) {
        this.tbuser = tbuser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
