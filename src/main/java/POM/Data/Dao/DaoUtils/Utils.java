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
            closePreparedStatement(pstmt);
        }
    }

    public static int getUsersCount() throws SQLException {
        String query = "select * from students";
        int usersCount = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try(Connection conn = SQLJDBCUtil.getConnection()) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                usersCount++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeStatement(stmt);
            closeResultSet(rs);
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
            closePreparedStatement(pstmt);
        }
        return exactUser;
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        assert rs != null;
        rs.close();
    }

    public static void closePreparedStatement(PreparedStatement pstmt) throws SQLException {
        assert pstmt != null;
        pstmt.close();
    }

    public static void closeStatement(Statement stmt) throws SQLException {
        assert stmt != null;
        stmt.close();
    }
}
