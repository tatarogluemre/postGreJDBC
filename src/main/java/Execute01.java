import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

            //1. Adım: Driver'a kaydol
            Class.forName("org.postgresql.Driver");
            //2. Adım: Database'e bağlan
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","tatar1987");
           //3.Adım: Statement oluştur.
            Statement statement = connection.createStatement();


            //4.Adım: Query Çalıştır.
       /*
        execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
        1) Eğer execute() methodu DDL için kullanılırsa 'false' return yapar.
        2) Eğer execute() methodu DQL için kullanılırsa ResultSet alındığında 'true' aksi hale 'false' verir.
        */

     //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

       boolean sql1 = statement.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20),worker_salary INT)");
       //false return eder çünkü data çağırmıyoruz.
       if(!sql1){
           System.out.println("Tablo başarıyla eklendi");
       }else {System.out.println("Tablo eklenemedi");}

    //2.Örnek : workers tablosuna workers_address sütunu ekleyiniz.
        boolean sql2 = statement.execute("ALTER TABLE workers ADD worker_address varchar(70)");
        if(!sql2){
            System.out.println("Sütun başarıyla eklendi");
        }else {System.out.println("Sütun eklenemedi");}

     //3.Örnek: workers table siliniz
        boolean sql3= statement.execute("DROP TABLE IF EXISTS workers");
        if(!sql3){
            System.out.println("Tablo başarıyla silindi");
        }else {System.out.println("Tablo Silinemedi");}


        //5.Adım: Bğlantı ve Statement kapat.




    }
}
