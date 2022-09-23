package com.example.ariagrocery;

public class Review {
    private int groceryId;
    private String username;
    private String text;
    private String date;

    public Review(int groceryId, String username, String text, String date) {
        this.groceryId = groceryId;
        this.username = username;
        this.text = text;
        this.date = date;
    }

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "groceryId=" + groceryId +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
