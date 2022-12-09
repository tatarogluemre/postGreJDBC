import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tecproed","postgres","tatar1987");
            Statement st = con.createStatement();
            System.out.println("Connection Success");
        } catch (ClassNotFoundException e) {
            System.out.println("dosya bulunamadı");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
