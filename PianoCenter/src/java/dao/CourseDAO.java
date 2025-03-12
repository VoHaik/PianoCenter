/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import crud.ICRUD;
import dto.CourseDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
public class CourseDAO implements ICRUD<CourseDTO, String>{
    
    public int countRow(String searchValue,String category) throws ClassNotFoundException, SQLException{
        int numberOfRecords=0;
        Connection con=dbutils.DBUtils.makeConnection();
        ResultSet rs= null;
        PreparedStatement stm=null;
        String sql="SELECT COUNT(courseID) AS totalCourses FROM Courses where name like ? and category like ?  and status = 'Active' AND quantity > 0";
        try {
            stm=con.prepareStatement(sql);
            stm.setString(1, "%"+searchValue+"%");
            if(category.equalsIgnoreCase("all")){stm.setString(2, "%");}else{stm.setString(2, category);}
            rs=stm.executeQuery();
//            System.out.println(rs);
            if(rs!=null){rs.next();numberOfRecords=rs.getInt("totalCourses");}
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
            if(rs!=null){rs.close();}
        }
        return numberOfRecords;
    }

    @Override
    public boolean create(CourseDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<CourseDTO> read(String searchValue,String categories,int OFFSET,int PageSize){
        Connection con=null;
        ResultSet rs= null;
        PreparedStatement stm=null;
        ArrayList<CourseDTO> courses= new ArrayList<>();
        String sql="SELECT * \n" +
"FROM Courses \n" +
"WHERE name like ? and status = 'Active' AND quantity > 0 and category like ?\n" +
"ORDER BY createDate asc \n" +
"OFFSET ? ROWS \n" +//OFFSET ở đây là số records sẽ được bỏ qua
"FETCH NEXT ? ROWS ONLY;";// Trong khi đó fetch nó sẽ nạp số records tiếp theo sau khi được bỏ qua bởi offset
        
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, "%"+searchValue+"%");
            if(categories.equalsIgnoreCase("all")){
                stm.setString(2, "%");
            }else{stm.setString(2, categories);}
            stm.setInt(3, OFFSET);
            stm.setInt(4, PageSize);
            rs=stm.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    int courseID=rs.getInt("courseID");
                    String name=rs.getString("name");
                    String description=rs.getString("description");
                    BigDecimal tutionFee=rs.getBigDecimal("tuitionFee");
                    Date startDate=rs.getDate("startDate");
                    Date endDate=rs.getDate("endDate");
                    String category=rs.getString("category");
                    Date createDate=rs.getDate("createDate");
                    String lastUpdateUser=rs.getString("lastUpdateUser");
                    String status=rs.getString("status");
                    int quantity=rs.getInt("quantity");
                    CourseDTO course= new CourseDTO(courseID, name, description, tutionFee, startDate, endDate, category, createDate, lastUpdateUser, status, quantity);
                    courses.add(course);
                }
                return courses;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null){try {
                con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            if(stm!=null){try {
                stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            if(rs!=null){try {
                rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        }
        return null;
    }

    @Override
    public boolean update(CourseDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(CourseDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<String> getCategory() throws ClassNotFoundException, SQLException{
        ArrayList<String> categories= new ArrayList<>();
        Connection con=dbutils.DBUtils.makeConnection();
        ResultSet rs= null;
        PreparedStatement stm= null;
        String sql="select category from Courses group by category";
        try {
            stm=con.prepareStatement(sql);
            rs=stm.executeQuery();
            while(rs.next()){
                categories.add(rs.getString("category"));
            }
        } catch (Exception e) {e.printStackTrace();
        }finally{
            if(con!=null){con.close();}
            if(stm!=null){stm.close();}
            if(rs!=null){rs.close();}
        }
        return categories;
    }

    @Override
    public List<CourseDTO> read(String String) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
