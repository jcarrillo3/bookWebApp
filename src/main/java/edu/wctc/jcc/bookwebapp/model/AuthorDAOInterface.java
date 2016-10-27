/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author jcarrillo
 */
public interface AuthorDAOInterface {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    
    Author getAuthorById(String id) throws ClassNotFoundException, SQLException;
    
    int deleteAuthorById(String id) throws ClassNotFoundException, 
            SQLException;
    
    void createAuthor(List<String> columns, List<Object> values) throws ClassNotFoundException, SQLException;
    
    int updateAuthor(List<String> colNames, List<Object> colValues, String primaryKeyCOl, String id) throws ClassNotFoundException, SQLException;
    
    void initDAO(String driverClass, String url, String username, String password);
    void initDAO(DataSource ds) throws SQLException;
}
