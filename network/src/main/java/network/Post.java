package network;

import java.util.Date;

public class Post {
	
    private int id;
    private String text;
    private int likes;
    private Date date;
    private int userId;

    public Post(int id, String text, int likes, Date date, int userId) {
        this.id = id;
        this.text = text;
        this.likes = likes;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }
}
