package com.example.pcy.sharingeconomy;

public class Data_Post {
    private String UserID;
    private String Title;
    private int index;
    private int Point;
    private String Time;
    private String Content;
    public Data_Post(){}

    public Data_Post(String Time,String UserID, String Title, int index, int point,String content){
        this.UserID=UserID;
        this.Title=Title;
        this.index=index;
        this.Point=point;
        this.Time =Time;
        this.Content=content;
    }
    public String getTitle(){ return Title; }
    public int getIndex(){return index;}
    public String getUserID() {
        return UserID;
    }
    public int getPoint() {
        return Point;
    }
    public String getTime(){return Time;}
    public String getContent(){return Content;}
    public void setTime(String Time){this.Time = Time;}
    public void setTitle(String Title){this.Title=Title;}
    public void setIndex(){this.index=index;}
    public void setUserID(String userID) {
        UserID = userID;
    }
    public void setPoint(int point) { Point = point; }
    public void setContent(String content){Content=content;}
}
