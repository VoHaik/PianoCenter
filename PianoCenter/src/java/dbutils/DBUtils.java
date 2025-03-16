/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OS
 */
public class DBUtils {
    public static Connection makeConnection() throws ClassNotFoundException{
        Connection con=null;
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
             ds.setPassword("123456");
            ds.setServerName("Jarvis\\SQLEXPRESS");
            ds.setPortNumber(1433);
            ds.setDatabaseName("ttk_piano_db");
            ds.setEncrypt(false);  // Tắt SSL encryption
        ds.setTrustServerCertificate(true);
            try 
            { con = ds.getConnection();}
            catch (SQLException e) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, e);
        }
            return con;
    } 
}
