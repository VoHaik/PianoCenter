/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author dinh
 */
public class CartDTO implements Serializable{
    String userID;
    int courseID;
    int quantity;
    int cartID;

    public CartDTO(String userID, int courseID, int quantity) {
        this.userID = userID;
        this.courseID = courseID;
        this.quantity = quantity;
    }
    public CartDTO(int cartID, String userID, int courseID, int quantity) {
        this.cartID=cartID;
        this.userID = userID;
        this.courseID = courseID;
        this.quantity = quantity;
    }

    public CartDTO() {
       
    }

    public String getUserID() {
        return userID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
    
}
