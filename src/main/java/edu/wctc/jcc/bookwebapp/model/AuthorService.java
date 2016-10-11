/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author jcarrillo
 */
@SessionScoped
public class AuthorService implements Serializable {
    @Inject
    AuthorDAOInterface dao;
    
    /**
     * Empty Constructor
     */
    public AuthorService(){
    }

    /**
     * Returns all Author in the Database
     * @return Authors
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public List getAuthors() throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();
    }
    
    /**
     * Returns an Author with the given ID
     * @param id
     * @return Author
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalArgumentException if one or more parameters are invalid
     */
    public Author getAuthorById(String id) throws ClassNotFoundException, 
            SQLException, IllegalArgumentException {
        
        if (id == null || id.isEmpty()){
            throw new IllegalArgumentException();
        }
        return dao.getAuthorById(id);
    }
    
    /**
     * Deletes an Author with the given Id
     * @param id
     * @return deleteCount
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalArgumentException if one or more parameters are invalid
     */
    public int deleteAuthor(String id) throws ClassNotFoundException, 
            SQLException, IllegalArgumentException {
        
        if (id == null || id.isEmpty()){
            throw new IllegalArgumentException();
        }
        return dao.deleteAuthorById(id);
    }
    
    /**
     * Creates a new Author with the given values
     * @param colNames
     * @param colValues
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalArgumentException if one or more parameters are invalid
     * @throws NullPointerException  if one or more parameters are null
     */
    public void createNewAuthor(List<String> colNames, List<Object> colValues) 
            throws ClassNotFoundException, SQLException, 
            IllegalArgumentException, NullPointerException {
        
        if (colNames.size() < 1 || colValues.size() < 1) {
            throw new IllegalArgumentException();
        }
        if (colNames == null || colValues == null) {
            throw new NullPointerException();
        }
        dao.createAuthor(colNames, colValues);
    }
    
    /**
     * Updates an Author with the given values
     * @param colNames
     * @param colValues
     * @param primaryKeyCol
     * @param id
     * @return auhthorsUpdates
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalArgumentException if one or more parameters are invalid
     * @throws NullPointerException if one or more parameters are null
     */
    public int updateAuthorByKey(List<String> colNames, List<Object> colValues, 
            String primaryKeyCol, String id) throws ClassNotFoundException, SQLException,
            IllegalArgumentException, NullPointerException {
        
        if (colNames.size() < 1 || colValues.size() < 1 || primaryKeyCol.isEmpty() || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (colNames == null || colValues == null || primaryKeyCol == null || id == null) {
            throw new NullPointerException();
        }
        
        return dao.updateAuthor(colNames, colValues, primaryKeyCol, id);
    }
    
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AuthorDAOInterface dao = new AuthorDAO(new MySqlDbStrategy(), 
//                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        
//        AuthorService service = new AuthorService(dao);
//        //System.out.println(service.getAuthors());
//        
//        //System.out.println(service.getAuthorById("7"));
//        
//        //System.out.println(service.deleteAuthor("1"));
//        
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("MEEEN Vazquez");
//        colValues.add("2014-08-10");
//        //service.createNewAuthor(colNames, colValues);
//        
//        List<String> colNames2 = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List<Object> colValues2 = new ArrayList<>();
//        colValues.add("Jason Lane");
//        colValues.add("2015-10-25");
//        System.out.println(service.updateAuthorByKey(colNames, colValues, "author_id", "5") + " record updated");
//        
//    }

    /**
     * Returns the AuthorDAOInterface object
     * @return 
     */
    public AuthorDAOInterface getDao() {
        return dao;
    }

    /**
     * Sets the AuthorDAOInterface object
     * @param dao
     * @throws NullPointerException if dao is null
     */
    public void setDao(AuthorDAOInterface dao) throws NullPointerException {
        if (dao == null){
            throw new NullPointerException();
        }
        this.dao = dao;
    }
    
    
}
