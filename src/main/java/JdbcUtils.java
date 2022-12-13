import java.sql.*;

public class JdbcUtils {

   private static Connection connection;
   private static Statement statement;
   private static boolean execute;

    public static Connection connectToDb(String hostName,String dbName, String userName, String password){

        //1. Adım: Driver'a kaydol
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //2. Adım: Database'e bağlan
        try {
           connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Success");
        }else System.out.println("Connection Fail");

    return connection;


    }
        //3.Adım:
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //4.Adım: Query Çalıştır:

    public static boolean execute(String sql){

        try {
          execute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     if(!execute){
         System.out.println("İşlem Başarılı");
}else System.out.println("Başarısız işlem");
return execute;
    }

    //5.Adım:
    public static  void closeConnectionAndStatement(){

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and Statement Closed");
            }else System.out.println("Connection and Statement Not Closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//Table oluşturan method
    public static void createTable(String tablename , String... sutunAdi_dataType){

        StringBuilder sutunAdi_dataValue = new StringBuilder("");

       for(String w : sutunAdi_dataType){

           sutunAdi_dataValue.append(w).append(",");
       }
        sutunAdi_dataValue.deleteCharAt(sutunAdi_dataValue.length()-1);
        try {
            if(!statement.execute("CREATE TABLE " +tablename+ "("+sutunAdi_dataValue+")")){
                System.out.println("Tablo " +tablename+ " başarılı bir şekilde oluşturuldu");
            }else System.out.println("Tablo oluşturulamadı");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
     public static void insertValue(String tableName , Object... value){

        StringBuilder dataValue = new StringBuilder("");

        for(Object w : value){

            dataValue.append(w).append(",");
        }
        dataValue.deleteCharAt(dataValue.length()-1);
         try {
             statement.execute("INSERT INTO "+tableName+" VALUES("+dataValue+")");

             System.out.println("datalar başarılı eklendi");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }


     }

}
