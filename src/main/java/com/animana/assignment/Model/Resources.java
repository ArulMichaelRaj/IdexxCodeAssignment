package com.animana.assignment.Model;


import java.util.Objects;

public class Resources {


    public  String title;
    public  String author;
    public  String type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resources)) return false;
        Resources resources = (Resources) o;
        return Objects.equals(title, resources.title) &&
                Objects.equals(author, resources.author) &&
                Objects.equals(type, resources.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, type);
    }
}
