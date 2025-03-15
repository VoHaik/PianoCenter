/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;


import java.util.*;

/**
 *
 * @author OS
 */
public interface ICRUD<E,K> {
    boolean create(E entity);
    List<E> read(K String);
    
    boolean update(E entity);
    boolean delete(E entity);
}
