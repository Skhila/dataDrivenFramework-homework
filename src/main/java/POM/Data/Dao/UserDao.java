package POM.Data.Dao;

import POM.Data.Dao.DaoUtils.SQLJDBCUtil;
import POM.Data.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static POM.Data.Dao.DaoUtils.Utils.getUsersCount;
import static POM.Data.Dao.DaoUtils.Utils.insertWithCustomCommit;

public class UserDao {



    public static List<User> getUsersInfo() throws SQLException {

        String query = "select * from students";
        List<User> userList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLJDBCUtil.getConnection()) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phone");
                userList.add(new User(id, firstName, lastName, phoneNumber));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert stmt != null;
            stmt.close();

            assert rs != null;
            rs.close();
        }
        return userList;
    }

    public static void insertIntoStudentsTableUsingCustomAutoCommit(User user) throws SQLException {
        int usersCountBefore = getUsersCount();
        System.out.println("Users count before insert without commit: " + usersCountBefore);

        insertWithCustomCommit(user, false);

        int usersCountAfter = getUsersCount();
        System.out.println("Users count after insert without commit: " + usersCountAfter);

        if (usersCountBefore == usersCountAfter){
            System.out.println("Success! Row wasn't created! Committing...");
            insertWithCustomCommit(user, true);
        }else {
            System.out.println("Something went wrong! row was created!");
        }

        int usersCountAfterCommit = getUsersCount();
        System.out.println("Users count after commit: " + usersCountAfterCommit);
    }

    public static void deleteStudentById(int id) throws SQLException {
        String query = "delete from students where id=?";
        PreparedStatement pstmt = null;

        try (Connection conn = SQLJDBCUtil.getConnection()) {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert pstmt != null;
            pstmt.close();
        }
    }

    public static void updateStudentFirstName(int id, String updatedName) throws SQLException {
//        String query = String.format("UPDATE students SET firstName=%s WHERE id=%d", updatedName, student.getId());
        String preparedQuery = "UPDATE students SET firstName=? WHERE id=?";
        PreparedStatement pstmt = null;

        try (Connection conn = SQLJDBCUtil.getConnection()) {
            pstmt = conn.prepareStatement(preparedQuery);
            pstmt.setString(1, updatedName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert pstmt != null;
            pstmt.close();
        }
    }

}
