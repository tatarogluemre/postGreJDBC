import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Medunna {
    public static void main(String[] args) throws SQLException {
        JdbcUtils.connectToDb("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();

        if(!statement.execute("CREATE TABLE example (id int, name varchar(20))")){

            System.out.println("tablo eklendi");

        }else System.out.println("eklenmedi");

        JdbcUtils.insertValue("example",1,"'Asd'");
        JdbcUtils.insertValue("example",2,"'dsd'");


    ResultSet rst1 = statement.executeQuery("SELECT * FROM example");

    while (rst1.next()){

        System.out.println(rst1.getInt(1) + "--" + rst1.getString(2));
    }
     if(!statement.execute("Drop Table example")){
         System.out.println("Tablo Silindi");
     }else System.out.println("Tablo Silinmedi");
    JdbcUtils.closeConnectionAndStatement();
    }


}
