package kuleba.spring.dao;

import kuleba.spring.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> index();

    Person show(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    void delete(int id);
}
