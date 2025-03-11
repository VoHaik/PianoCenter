/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author OS
 */
public class DBUtils {
    public static Connection makeConnection() throws ClassNotFoundException{
        Connection con=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://VoDongDucKhai\\SQLEXPRESS:1433;databaseName=VoDongDucKhai_Spring2025";
            String username="sa";
            String password="12345";
            con=DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    } 
}
