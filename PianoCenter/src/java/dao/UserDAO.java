/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import crud.ICRUD;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OS
 */
public class UserDAO implements ICRUD<UserDTO, String>{

    @Override
    public boolean create(UserDTO entity) {
        Connection con=null;
        PreparedStatement stm=null;
        String sql="insert into Users (userID, password,fullName,email,phone,role)\n" +"values (?,?,?,?,?,?)";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, entity.getUserID());
            stm.setString(2, entity.getPassword());
            stm.setString(3, entity.getFullName());
            stm.setString(4, entity.getEmail());
            stm.setString(5, entity.getPhone());
            stm.setString(6, "User");
            int row=stm.executeUpdate();
            if(row>0){return true;}
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){try {
                con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            if(stm!=null){try {
                stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        }
        return false;
    }

    @Override
    public List<UserDTO> read(String String) {
        List<UserDTO> users= new ArrayList<>();
        Connection con=null;
        PreparedStatement stm= null;
        ResultSet rs=null;
        String sql="Select * from Users where userID=?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, String);
            rs=stm.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    String username=rs.getString("userID");
                    String password=rs.getString("password");
                    String fullname=rs.getString("fullname");
                    String email=rs.getString("email");
                    String role=rs.getString("phone");
                    String phone=rs.getString("role");
                UserDTO user= new UserDTO(username, password, fullname, email, phone, role);
                users.add(user);
                }
                return users;
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){try {
                con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
            if(stm!=null){try {
                stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
            if(rs!=null){try {
                rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
        }
        return null;
    }

    @Override
    public boolean update(UserDTO entity) {
//        Connection con= dbutils.DBUtils.makeConnection();
//        PreparedStatement stn= null;
//        ResultSet rs= null;
//        String sql="Update Users where ";
//        try {
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
