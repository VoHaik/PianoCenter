/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbutils.DBUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OS
 */
public class RegistrationDAO implements Serializable{
    public boolean checkingExist(String txtVar,String column) throws SQLException, ClassNotFoundException{
        Connection con=DBUtils.makeConnection();
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
//            String sql="select * from Users where userID =?";
            String sql="select * from Users where "+column+"=?";
            stm=con.prepareStatement(sql);
            stm.setString(1, txtVar);
            rs= stm.executeQuery();
            if(rs.next()){return true;}
        }finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
            if(rs!=null){rs.close();}
        }
        return false;
    }
    public boolean registerAccount(String username,String password,String fullName,String email,String phone) throws ClassNotFoundException, SQLException{
        Connection con=DBUtils.makeConnection();
        PreparedStatement stm=null;
//        if(checkingExist(username,con)){return false;}
        try {
            String sql="Insert into Users (userID,password,fullName,email,phone,role)\n" +"values	(?,?,?,?,?,?)";
            stm=con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, fullName);
            stm.setString(4, email);
            stm.setString(5, phone);
            stm.setString(6, "User");
            int row=stm.executeUpdate();
            if(row>0){return  true;}
        } 
        finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
        }
        return false;
    }
    
    
}
