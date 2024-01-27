package ru.klimov.springmvc.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    private String personId;

    @NotEmpty(message = "Не указано название книги")
    @Size(max = 200, message = "Название книги должно быть меньше 200 символов")
    private String name;

    @NotEmpty(message = "Не указан автор")
    @Size(max = 200, message = "Имя автора должно быть меньше 200 символов")
    private String author;

    @Min(value = 0, message = "Год не может быть меньше 0")
    @Max(value = 2025, message = "Год не может быть больше 2025")
    private int year;

    public Book() {
    }

    public Book(int id, String personId, String author, int year) {
        this.id = id;
        this.personId = personId;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
