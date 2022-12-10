import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc", "postgres", "tatar1987");
        //3.Adım: Statement oluştur.
        Statement statement = connection.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan
        //          number_of_employees değerlerini 18500 olarak UPDATE edin.

        String st1 = "UPDATE companies SET number_of_employees =18500 WHERE number_of_employees <(SELECT AVG(number_of_employees) FROM companies);";

        int updateEdilenSatirSayisi = statement.executeUpdate(st1);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

        ResultSet rst1 = statement.executeQuery("SELECT * FROM companies");

        while (rst1.next()){

            System.out.println(rst1.getInt(1) + "---" + rst1.getString(2) + "---" + rst1.getInt(3));

        }

        connection.close();
        statement.close();
        rst1.close();

    }
}
