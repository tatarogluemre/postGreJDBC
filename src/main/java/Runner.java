import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
       Connection connection = JdbcUtils.connectToDb("localhost","jdbc","postgres","tatar1987");

       Statement statement = JdbcUtils.createStatement();

        JdbcUtils.execute("CREATE TABLE students (name VARCHAR(20),id INT, address VARCHAR(80))");
        JdbcUtils.execute("DROP TABLE students");

        JdbcUtils.createTable("denemelerim","name varchar(10)","id int");

        JdbcUtils.insertValue("denemelerim","'emre'",10);

        JdbcUtils.closeConnectionAndStatement();

    }
}
