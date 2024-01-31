package ru.klimov.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.klimov.springmvc.models.Book;
import ru.klimov.springmvc.models.Person;

import java.util.List;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }


    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (personid, name, author, year) VALUES (?,?,?,?)",
                book.getPersonId(),
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET personId=? WHERE id=?",
                person.getId(),
                id);
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE Book SET personId=NULL WHERE id=?",
                id);
    }
}
