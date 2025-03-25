/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import crud.ICRUD;
import dbutils.DBUtils;
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
public class CourseDAO implements ICRUD<CourseDTO, Integer>{
    
    public int countRow(String searchValue,String category, String role) throws ClassNotFoundException, SQLException{
        int numberOfRecords=0;
        Connection con=dbutils.DBUtils.makeConnection();
        ResultSet rs= null;
        PreparedStatement stm=null;
        String sql;
        if(role==null) sql="SELECT COUNT(courseID) AS totalCourses FROM Courses where name like ? and category like ?  and status = 'Active' AND quantity > 0";
        else if (role.equalsIgnoreCase("Admin")) { sql="SELECT COUNT(courseID) AS totalCourses FROM Courses where name like ? and category like ?  and status = 'Active'";}
                else sql="SELECT COUNT(courseID) AS totalCourses FROM Courses where name like ? and category like ?  and status = 'Active' AND quantity > 0";
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

    public boolean create(String name, String description, BigDecimal tuitionFee, 
                         Date startDate, Date endDate, String category, String status, int quantity, 
                         String lastUpdateUser) throws ClassNotFoundException, SQLException{
        
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
    
    try {
        String sql = "INSERT INTO Courses (name, image, description, tuitionFee, startDate, endDate, category, status, quantity, lastUpdateUser) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stm = con.prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, null);
        stm.setString(3, description);
        stm.setBigDecimal(4, tuitionFee);
        stm.setDate(5, startDate); // Dùng java.sql.Date
        stm.setDate(6, endDate);   // Dùng java.sql.Date
        stm.setString(7, category);
        stm.setString(8, status);
        stm.setInt(9, quantity);
        stm.setString(10, lastUpdateUser);

        int row = stm.executeUpdate();
        return row > 0;
    } finally {
        if (stm != null) stm.close();
        if (con != null) con.close();
    }
    }

    
    public List<CourseDTO> read(String searchValue,String categories,int OFFSET,int PageSize){
        Connection con=null;
        ResultSet rs= null;
        PreparedStatement stm=null;
        ArrayList<CourseDTO> courses= new ArrayList<>();
        String sql="SELECT * \n" +
"FROM Courses \n" +
"WHERE name like ?  AND quantity > 0 and category like ?\n" +
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
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement stm= null;
        String sql="update Courses set name=?, description=?,tuitionFee=?,startDate=?,endDate=?,category=?,createDate=?,status=?,quantity=?,lastUpdateUser=? \n" +
"where courseID =?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, entity.getName());
            stm.setString(2, entity.getDescription());
            stm.setBigDecimal(3, entity.getTutionFee());
            stm.setDate(4, entity.getStartDate());
            stm.setDate(5, entity.getEndDate());
            stm.setString(6, entity.getCategory());
            stm.setDate(7, entity.getCreateDate());
            stm.setString(8, entity.getStatus());
            stm.setInt(9, entity.getQuantity());
            stm.setString(10, entity.getLastUpdateUser());
            stm.setInt(11, entity.getCourseID());
            int row= stm.executeUpdate();
            if(row>0){return true;}
        } catch (Exception e) {
        }finally{
            if(con!=null){try {
                con.close();
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
            if(stm!=null){try {
                stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        }
        return false;
    }
    public boolean updateQuantity(int quantity,int courseID) {
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement stm= null;
        String sql="update Courses set quantity=? where courseID =?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, String.valueOf(quantity));
            stm.setString(2, String.valueOf(courseID));
            int row= stm.executeUpdate();
            if(row>0){return true;}
        } catch (Exception e) {
        }finally{
            if(con!=null){try {
                con.close();
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
            if(stm!=null){try {
                stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        }
        return false;
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
    public boolean create(CourseDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public CourseDTO read(String value, String column) {
//       CourseDTO courseDTO=null;
//       Connection con=null;
//       PreparedStatement stm=null;
//       ResultSet rs=null;
//       String sql="select * from Courses where "+column+" =?";
//        try {
//            con=dbutils.DBUtils.makeConnection();
//            stm=con.prepareStatement(sql);
//            stm.setString(1, value);
//            rs=stm.executeQuery();
//            
//                if(rs.next()){
//                    int courseID=rs.getInt("courseID");
//                    String name=rs.getString("name");
//                    String description=rs.getString("description");
//                    BigDecimal tutionFee=rs.getBigDecimal("tuitionFee");
//                    Date startDate=rs.getDate("startDate");
//                    Date endDate=rs.getDate("endDate");
//                    String category=rs.getString("category");
//                    Date createDate=rs.getDate("createDate");
//                    String lastUpdateUser=rs.getString("lastUpdateUser");
//                    String status=rs.getString("status");
//                    int quantity=rs.getInt("quantity");
//                    courseDTO= new CourseDTO(courseID, name, description, tutionFee, startDate, endDate, category, createDate, lastUpdateUser, status, quantity);
//                }
//        } catch (Exception e) {}
//        finally{
//            if(con!=null){try {
//                con.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//}
//            if(rs!=null){try {
//                rs.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//}
//            if(stm!=null){try {
//                stm.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//}
//        }
//        return courseDTO;
//    }

    @Override
    public List<CourseDTO> read(Integer courseID) {
        ArrayList<CourseDTO> courseDTOs= new ArrayList<>();
        Connection con = null;
        ResultSet rs= null;
        PreparedStatement stm= null;
        String sql="select * from Courses where courseID = ?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, String.valueOf(courseID));
            rs=stm.executeQuery(sql);
            while(rs.next()){
                    int CourseID=rs.getInt("courseID");
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
                    CourseDTO course= new CourseDTO(CourseID, name, description, tutionFee, startDate, endDate, category, createDate, lastUpdateUser, status, quantity);
                    courseDTOs.add(course);
                }
                return courseDTOs;
        } catch (Exception e) {}
        finally{
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
    public CourseDTO getCourse(int courseID){
        Connection con =null;
        ResultSet rs=null;
        PreparedStatement stm=null;
        String sql="select * from Courses where courseID = ?";
        try {
            con=dbutils.DBUtils.makeConnection();
            stm=con.prepareStatement(sql);
            stm.setString(1, String.valueOf(courseID));
            rs=stm.executeQuery();
            if(rs.next()){
                int CourseID=rs.getInt("courseID");
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
                    CourseDTO course= new CourseDTO(CourseID, name, description, tutionFee, startDate, endDate, category, createDate, lastUpdateUser, status, quantity);;
                    return course;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
}
