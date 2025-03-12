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

    @Override
    public boolean create(CourseDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CourseDTO> read(String searchValue){
        Connection con=null;
        ResultSet rs= null;
        PreparedStatement stm=null;
        ArrayList<CourseDTO> courses= new ArrayList<>();
        String sql="select * from Courses where name like ?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, "%"+searchValue+"%");
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
//        courses=read("");
//        ArrayList<String> categories= new ArrayList<>();
//        for(CourseDTO dto:courses){
//            if(!categories.contains(dto.getCategory())){categories.add(dto.getCategory());}
//        }
//        return categories;
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
}
