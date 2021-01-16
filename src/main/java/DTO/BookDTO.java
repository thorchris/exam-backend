package DTO;

import entities.Book;

public class BookDTO { 
    private int isbn;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;

    public BookDTO(Book b) {
        this.isbn = b.getIsbn();
        this.title = b.getTitle();
        this.author = b.getAuthor();
        this.publisher = b.getPublisher();
        this.publishYear = b.getPublishYear();
    }

    public BookDTO() {
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }   
}
