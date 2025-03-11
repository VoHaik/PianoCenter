/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author OS
 */
public class CheckRegisterForm {
    public boolean checkUsername(String username){
//  kí tự từ A-z gồm chữ in hoa và số từ 0-9. Độ dài của chuỗi 6-12        
        String regex = "^[a-zA-Z0-9_]{6,12}$";
        if(username.matches(regex)){return true;}
        else return false;
    }
    public boolean checkPassword(String password){
//  ít nhất 1 chữ cái viết hoa/thường, ít nhất 1 kí tự số, ít nhất 1 kí tự đặc biệt. Độ dài 20 là max        
        String regex =  "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        if(password.matches(regex)){return true;}
        else return false;
    }
    public boolean checkEmail(String password){
//  phải có @ và . trước phần miền. Với phần miền phải có ít nhất 2 kí tự .vn/.com        
        String regex =  "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if(password.matches(regex)){return true;}
        else return false;
    }
    public boolean checkPhone(String password){
//  phải có @ và . trước phần miền. Với phần miền phải có ít nhất 2 kí tự .vn/.com        
        String regex =  "^\\d{10}$";
        if(password.matches(regex)){return true;}
        else return false;
    }

}
