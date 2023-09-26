package POM.Data.Dao.DaoUtils;

import POM.Data.Model.User;

import java.sql.*;

public class Utils {

    public static void insertWithCustomCommit(User user, boolean isAutoCommit) throws SQLException {
        PreparedStatement pstmt = null;

        String preparedQuery = "INSERT into students values(?,?,?,?)";

        try(Connection conn = SQLJDBCUtil.getConnection()){
            conn.setAutoCommit(isAutoCommit);

            pstmt = conn.prepareStatement(preparedQuery);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert pstmt != null;
            pstmt.close();
        }
    }

    public static int getUsersCount() {
        String query = "select * from students";
        int usersCount = 0;
        try(Connection conn = SQLJDBCUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                usersCount++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersCount;
    }

    public static User getStudentById(int id) throws SQLException {
        String preparedQuery = "SELECT * FROM students WHERE id=?";
        PreparedStatement pstmt = null;
        User exactUser = null;

        try(Connection conn = SQLJDBCUtil.getConnection()){
            pstmt = conn.prepareStatement(preparedQuery);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int studentId = rs.getInt(1);
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phone");
                exactUser = new User(studentId, firstName, lastName, phoneNumber);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert pstmt != null;
            pstmt.close();
        }
        return exactUser;
    }
}
