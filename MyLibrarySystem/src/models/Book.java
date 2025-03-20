package models;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    String author, title, genre, description;
    int year, id;
    double rating;
    List<String> keywords;

    public Book(int id, String author, String title, String genre, String description, int year, List<String> keywords, double rating) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.year = year;
        this.keywords = keywords;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Year: " + year + ", Rating: " + rating;
    }
}
