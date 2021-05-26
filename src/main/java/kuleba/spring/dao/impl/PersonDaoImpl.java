package kuleba.spring.dao.impl;

import kuleba.spring.dao.PersonDao;
import kuleba.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SHOW_ALL_QUERY = "SELECT * FROM Person";
    private final String SHOW_BY_ID_QUERY = "SELECT * FROM Person WHERE id=?";
    private final String SAVE_QUERY = "INSERT INTO Person VALUES(default,?,?,?)";
    private final String UPDATE_BY_ID_QUERY = "UPDATE Person SET name=?, age=?, email=? WHERE id=?";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM Person WHERE id=?";


    public List<Person> index() {

        return jdbcTemplate.query(SHOW_ALL_QUERY, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {

        return jdbcTemplate.query(SHOW_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update(SAVE_QUERY, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {

        jdbcTemplate.update(UPDATE_BY_ID_QUERY,
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

    }

    public void delete(int id) {

        jdbcTemplate.update(DELETE_BY_ID_QUERY, id);
    }
}
