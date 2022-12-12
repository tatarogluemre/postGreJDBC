import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        *
        * PreparedStatement Interface birden çok kez çalıştırılabilen önceden derlenmiş bir SQL Kodunu temsil eder.
        * Parametrelenderilmiş SQL Query(sorguları) ile çalışır. Bu sorguyu 0 veya daha fazla parametre ile kullanılır.
        *
        * */


        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc", "postgres", "tatar1987");
        //3.Adım: Statement oluştur.
        Statement statement = connection.createStatement();

        //Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 999 olarak güncelleyin.
        //     1. Adım: PreparedStatement qury si ? ile değişkenlere pametre oluştur
        String sql1="UPDATE companies SET number_of_employees=? WHERE company=? ;";
        //     2.Adım: Prepared Statement objesi oluştur.
       PreparedStatement prd1= connection.prepareStatement(sql1);
       //       3.Adım: setInt() , setString(), ... methodlarını kullanarak ? yerine değer atıyoruz.
        prd1.setInt(1,999); //1 --> 1. parametre
        prd1.setString(2,"IBM");
        //      4.Adım:exequteUpdate() çalıştır.
        int guncellenenSatirlar= prd1.executeUpdate();
        System.out.println("guncellenenSatirlar = " + guncellenenSatirlar);

        String st1 = "SELECT * FROM companies WHERE company ='IBM'";
        ResultSet rst1 = statement.executeQuery(st1);
        while (rst1.next()){

            System.out.println(rst1.getInt(1) + "--" + rst1.getString(2) + "---" + rst1.getInt(3));
        }

//2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        prd1.setInt(1,5555);
        prd1.setString(2,"GOOGLE");
        int guncellenenSatirSayiyisi = prd1.executeUpdate();
        System.out.println("guncellenenSatirSayiyisi = " + guncellenenSatirSayiyisi);

        String st2 = "SELECT * FROM companies WHERE company ='GOOGLE'";

        ResultSet rst2 = statement.executeQuery(st2);
        while (rst2.next()){

            System.out.println(rst2.getInt(1) + "---" + rst2.getString(2) + "---" + rst2.getInt(3));

        }
        connection.close();
        statement.close();
        rst1.close();
        rst2.close();


    }

}
