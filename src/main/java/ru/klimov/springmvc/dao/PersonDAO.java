package ru.klimov.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.klimov.springmvc.models.Book;
import ru.klimov.springmvc.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(int id){
       return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                       new Object[]{id},
                       new BeanPropertyRowMapper<>(Person.class)).stream().findAny()/*.orElse(null)*/;
    }

    public Optional<Person> show(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullname=?",
                new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(fullName, dateOfBirth) VALUES (? ,?)",
                person.getFullName(),
                person.getDateOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, dateOfBirth=? WHERE id=?",
                updatedPerson.getFullName(),
                updatedPerson.getDateOfBirth(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> getBooksTakenByPerson(int id) {
        List<Book> result =  jdbcTemplate.query("SELECT name, author, year FROM Person LEFT JOIN Book ON Person.id = Book.personId WHERE Person.id=?",
                new Object[]{id},
                new RowMapper<Book>() {
                    @Override
                    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                        Book book = new Book();
                        book.setName(resultSet.getString("name"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setYear(resultSet.getInt("year"));
                        return book;
                    }
                });
        if (isEmptyList(result)) result.clear();
        return result;
    }

    private boolean isEmptyList(List<Book> result) {
        return result.get(0).getAuthor() == null;
    }
}
