package POM.Data.Dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLJDBCUtil {

    public static Connection getConnection() throws SQLException{
        Connection conn = null;

        try(FileInputStream f = new FileInputStream("src/main/resources/db.properties")){
            Properties props = new Properties();
            props.load(f);

            String url = props.getProperty("url"),
                   user = props.getProperty("user"),
                   password = props.getProperty("password");

            conn = DriverManager.getConnection(url, user, password);
            System.out.printf("\nConnected to database %s Successfully \n", conn.getCatalog());

        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
