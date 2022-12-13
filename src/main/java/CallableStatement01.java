import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       /* //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc", "postgres", "tatar1987");
        //3.Adım: Statement oluştur.
        Statement statement = connection.createStatement();
*/
        Connection connection = JdbcUtils.connectToDb("localhost","jdbc","postgres","tatar1987");
        Statement statement = JdbcUtils.createStatement();


        /*

        Javada methodlar return type olsa da olmasada method olarak adlandırılırlar.
        SQL de ise data retun ediyorsa function denir return yapmıyorsa procedure olarak adlandırılır.
         */

        //CallableStatement ile function çağırmayı parametrelendireceğiz.(SQL de method oluşturma)
        //1.Adım : Function kodunu yaz.//method oluştur.
        String sql1 = "CREATE OR REPLACE FUNCTION  toplama(x NUMERIC ,y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +  // dolar işaretleri fonksiyonun curly bracelet süsslü parantez body yi temsil eder
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adım : Function Çalıştır.
        statement.execute(sql1);

        //3.Adım : Function Çağır.
        CallableStatement cst1 = connection.prepareCall("{? = call toplama(?,?)}"); //ilk soru işareti return type ikinci ve üçüncü soru işareti parametre değişkenleri

        //4. Adım : Return için registerOurParameter() methodu, parametreler için set() ... methodlarını uygula
        cst1.registerOutParameter(1,Types.NUMERIC);//1. soru işareti için
        cst1.setInt(2,6);//2. soru işareti
        cst1.setInt(3,10);//3.soru işareti

        //5.Adım : exequte() methodu ile CallableStatement çalıştır.
        cst1.execute();

        //6.Adım : Sonucu çağırmak için return data type tipine göre
        System.out.println(cst1.getBigDecimal(1));

        //2.Ör: Koninin hacmini heaplayan bir function kodu yazınız.

        String sql2 = "CREATE OR REPLACE FUNCTION koni(r NUMERIC , h NUMERIC)" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +  // dolar işaretleri fonksiyonun curly bracelet süsslü parantez body yi temsil eder
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;" +
                "END" +
                "$$";
        statement.execute(sql2);
        CallableStatement cst2 = connection.prepareCall("{?=call koni(?,?)}");
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,5);
        cst2.setInt(3,8);
        cst2.execute();
        System.out.printf("%.2f",cst2.getBigDecimal(1));


    }
}
