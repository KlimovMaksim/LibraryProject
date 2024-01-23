package ru.klimov.springmvc.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(max = 200, message = "ФИО должно содержать меньше 200 символов")
    private String fullName;

    @Min(value = 1900, message = "Дата рождения должна быть позже 1900 года")
    private int dateOfBirth;

    public Person() {
    }

    public Person(int id, String fullName, int dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
