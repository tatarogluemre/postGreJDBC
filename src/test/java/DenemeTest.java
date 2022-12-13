import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DenemeTest {

    @Test
    public void test() throws SQLException {

        JdbcUtils.connectToDb("localhost","jdbc","postgres","tatar1987");
        Statement statement = JdbcUtils.createStatement();

        String st1 = "SELECT region_id FROM countries";
        ResultSet rst1 = statement.executeQuery(st1);
        List<Integer> ids = new ArrayList<>();

        while(rst1.next()){
           if(rst1.getInt(1)>1){
            ids.add(rst1.getInt(1));
           }
        }
        System.out.println(ids);

        Assert.assertEquals(17,ids.size());

    }
@Test
    public void medunna() throws SQLException {

        JdbcUtils.connectToDb("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        String sql = "SELECT created_by FROM room";

        ResultSet rst1 = statement.executeQuery(sql);
        List<String> createdBy = new ArrayList<>();

        while(rst1.next()){

        createdBy.add(rst1.getString(1));
        }
    System.out.println(createdBy);
        Assert.assertTrue(createdBy.contains("john_doe"));
}

}
