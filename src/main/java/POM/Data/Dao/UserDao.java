package POM.Data.Dao;

import POM.Data.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> getUsersInfo() throws SQLException {

        String query = "select * from students";
        List<User> userList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try(Connection conn  = SQLJDBCUtil.getConnection()){
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber =rs.getString("phone");
                userList.add(new User(firstName, lastName, phoneNumber));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            assert stmt != null;
            stmt.close();

            assert rs != null;
            rs.close();
        }
        return userList;

    }
}
