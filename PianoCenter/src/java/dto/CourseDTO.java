 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author OS
 */
public class CourseDTO {
    private int courseID;
    private String name;
    private String description;
    private BigDecimal tutionFee;
    private Date startDate;
    private Date endDate;
    private String category;
    private Date createDate;
    private String lastUpdateUser;
    private String status;
    private int quantity;

    public CourseDTO(int courseID, String name, String description, BigDecimal tutionFee, Date startDate, Date endDate, String category, Date createDate, String lastUpdateUser, String status, int quantity) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.tutionFee = tutionFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.status = status;
        this.quantity = quantity;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseIDString) {
        this.courseID = courseIDString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(BigDecimal tutionFee) {
        this.tutionFee = tutionFee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
            
}
