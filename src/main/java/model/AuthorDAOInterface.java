/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jcarrillo
 */
public interface AuthorDAOInterface {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    
    int deleteAuthorById(String id) throws ClassNotFoundException, 
            SQLException;
}
