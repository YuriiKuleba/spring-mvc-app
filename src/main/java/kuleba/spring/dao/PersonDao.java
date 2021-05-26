package kuleba.spring.dao;

import kuleba.spring.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> index();

    Person show(Integer id);

    void save(Person person);

    void update(Integer id, Person updatedPerson);

    void delete(Integer id);
}
