import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class JdbcMain
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        // TODO: Load the SQLite JDBC driver (JDBC class implements java.sql.Driver)
        Class.forName("org.sqlite.JDBC");
        // TODO: Create a DB connection
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db"))
        {

            // TODO: Create a SQL statement
            Statement statement = connection.createStatement();
            // TODO: Create a DB table
            statement.executeUpdate("DROP TABLE IF EXISTS contacts");
            statement.executeUpdate("CREATE TABLE contacts ( id INTEGER PRIMARY KEY, firstname STRING, lastname STRING, email STRING, phone INT(10) )");
            // TODO: Insert a couple contacts
            Contact contact = new Contact("Mitch", "Maynard", "mmaynar1@gmail.com", 8675309L);
            save(contact, statement);
            contact = new Contact("Laura", "Yates", "blah@test.com", 8775309L);
            save(contact, statement);
            // TODO: Fetch all the records from the contacts table
            ResultSet resultSet = statement.executeQuery("select * from contacts");
            // TODO: Iterate over the ResultSet & display contact info
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                System.out.printf("%s %s (%d)", firstName, lastName, id);
            }

        } catch (SQLException ex)
        {
            // Display connection or query errors
            System.err.printf("There was a database error: %s%n", ex.getMessage());
        }
    }

    public static void save(Contact contact, Statement statement) throws SQLException
    {
        String sql = "INSERT INTO contacts ( firstname, lastname, email, phone ) VALUES ( '%s', '%s', '%s', %d )";
        sql = String.format(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());

        statement.executeUpdate(sql);
    }
}