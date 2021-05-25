package kuleba.spring.dao;

import kuleba.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNTER;

    private static final String URL = "jdbc:postgresql://localhost:5432/kulebaDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {

        List<Person> peoples = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {

                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                peoples.add(person);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return peoples;
    }

    public Person show(int id) {

        Person person = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void update(int id, Person updatedPerson) {

        try {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

            statement.setString(1, updatedPerson.getName());
            statement.setInt(2, updatedPerson.getAge());
            statement.setString(3, updatedPerson.getEmail());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }


    }
}
