import java.sql.*;

public class ExecuteQueryDeneme {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","tatar1987");
        Statement statement = connection.createStatement();

        //1. Örnek : Oluşturulmuş countries tablosunda region_id'si 1 olan country_name değerlerini çağırın

        String st1 = "SELECT country_name FROM countries WHERE region_id=1";
        boolean sql1 = statement.execute(st1);
        if(sql1){
            ResultSet rst1 = statement.executeQuery(st1);
            while (rst1.next()){

                System.out.println(rst1.getString("country_name"));
            }
        }else System.out.println("veriler alınamadı");
        //2. Örnek: region_id 2 den büyük olduğu country_name ve country_id değerlerini çağırın
        System.out.println("-----------------------------------");
        String st2 = "SELECT country_name, country_id FROM countries WHERE region_id>2";
        boolean sql2 = statement.execute(st2);
        if(sql2){
            ResultSet rst2 = statement.executeQuery(st2);

            while (rst2.next()){

                System.out.println(rst2.getString("country_name") + " -- " + rst2.getString("country_id"));
            }
        }else{
            System.out.println("veriler alınamadı");
        }
        //3.Örnek: "oluşturulmuş companies tablosundan number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("-------------------------------------");
        String st3 = "SELECT * FROM companies WHERE number_of_employees= (SELECT min(number_of_employees) FROM companies)";
        boolean sql3 = statement.execute(st3);
        if(sql3){
            ResultSet rst3 = statement.executeQuery(st3);
            while (rst3.next()){

                System.out.println(rst3.getString(1) + "---" + rst3.getString(2) + " ---- " + rst3.getInt(3));

            }
        }else System.out.println("veriler alınamadı");
    }
}
