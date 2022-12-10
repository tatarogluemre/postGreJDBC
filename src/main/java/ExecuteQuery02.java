import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc", "postgres", "tatar1987");
        //3.Adım: Statement oluştur.
        Statement statement = connection.createStatement();

    //Ör 1: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın
        String st1 = "SELECT number_of_employees,company FROM companies ORDER BY number_of_employees DESC OFFSET 1 LIMIT 1";

        ResultSet rst1 = statement.executeQuery(st1);
        while (rst1.next()){

            System.out.println(rst1.getString("company") + "---" + rst1.getInt("number_of_employees"));

        }

    //  2 .Yol :
        String st2 = "select number_of_employees,company from companies where number_of_employees = " +
                "(select max(number_of_employees) from companies where number_of_employees < (select max(number_of_employees) from companies))";

            ResultSet rst2 = statement.executeQuery(st2);
            while (rst2.next()){

                System.out.println(rst2.getString("company") + "---" + rst2.getInt("number_of_employees"));

            }

            connection.close();
            statement.close();
            rst1.close();
            rst2.close();
    }

}
