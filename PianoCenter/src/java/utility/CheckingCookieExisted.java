/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author OS
 */
public class CheckingCookieExisted {
    public boolean wasExisted(String username,HttpServletRequest request){
        for(Cookie cookie :request.getCookies()){
                    if(cookie.getName().equals(username)){return true;}
                    }
        return false;
    }
}
