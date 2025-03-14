/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import crud.ICRUD;
import dbutils.DBUtils;
import dto.CartDTO;
import dto.CourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dinh
 */
public class CartDAO implements ICRUD<CartDTO, String> {
    public boolean isCourseInCart(String userID, int courseID) throws SQLException, ClassNotFoundException {
    boolean exists = false;
    String sql = "SELECT COUNT(*) FROM Cart WHERE userID = ? AND courseID = ?";
    
    try (Connection con = DBUtils.makeConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, userID);
        ps.setInt(2, courseID);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true; // Nếu số lượng > 0 thì đã tồn tại
            }
        }
    }
    return exists;
}
    
    public boolean createCart(CartDTO entity)throws ClassNotFoundException, SQLException{
     Connection con=dbutils.DBUtils.makeConnection();
        ResultSet rs= null;
        PreparedStatement stm=null;
        String sql;
        if (isCourseInCart(entity.getUserID(),entity.getCourseID())) { 
             sql="Update Cart set quantity = quantity +1 where userID=? and courseID=?";
             try {
            stm=con.prepareStatement(sql);
            stm.setString(1, entity.getUserID());
            stm.setInt(2, entity.getCourseID());
//            System.out.println(rs);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
            if(rs!=null){rs.close();}
        }
             
        } else { sql="Insert into Cart (quantity,userID,courseID) values (?,?,?)";
        try {
            stm=con.prepareStatement(sql);
            stm.setString(2, entity.getUserID());
            stm.setInt(3, entity.getCourseID());
            stm.setInt(1, entity.getQuantity());
//            System.out.println(rs);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
            if(rs!=null){rs.close();}
        }
        }
         
        return false;
    }
    
//      public boolean deleteCart(CartDTO entity) throws ClassNotFoundException, SQLException{
//        
//    }
    @Override
    public boolean create(CartDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<CartDTO> read(String String) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CartDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(CartDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
