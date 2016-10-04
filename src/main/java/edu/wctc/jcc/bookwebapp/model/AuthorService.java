/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jcarrillo
 */
public class AuthorService {
    AuthorDAOInterface dao;
    
    public AuthorService(AuthorDAOInterface dao){
        this.dao = dao;
    }

    public final List getAuthors() throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();
    }
    
    public final Author getAuthorById(String id) throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorById(id);
    }
    
    public final int deleteAuthor(String id) throws ClassNotFoundException, SQLException {
        
        return dao.deleteAuthorById(id);
    }
    
    public final void createNewAuthor(List<String> colNames, List<Object> colValues) 
            throws ClassNotFoundException, SQLException {
        
        dao.createAuthor(colNames, colValues);
    }
    
    public final int updateAuthorByKey(List<String> colNames, List<Object> colValues, 
            String primaryKeyCol, String id) throws ClassNotFoundException, SQLException{
        
        return dao.updateAuthor(colNames, colValues, primaryKeyCol, id);
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDAOInterface dao = new AuthorDAO(new MySqlDbStrategy(), 
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        
        AuthorService service = new AuthorService(dao);
        //System.out.println(service.getAuthors());
        
        //System.out.println(service.getAuthorById("7"));
        
        //System.out.println(service.deleteAuthor("1"));
        
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
        List<Object> colValues = new ArrayList<>();
        colValues.add("MEEEN Vazquez");
        colValues.add("2014-08-10");
        //service.createNewAuthor(colNames, colValues);
        
        List<String> colNames2 = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
        List<Object> colValues2 = new ArrayList<>();
        colValues.add("Jason Lane");
        colValues.add("2015-10-25");
        System.out.println(service.updateAuthorByKey(colNames, colValues, "author_id", "5") + " record updated");
        
    }
    
    
}
