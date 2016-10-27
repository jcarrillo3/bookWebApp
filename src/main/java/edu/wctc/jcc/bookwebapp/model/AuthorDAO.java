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
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author jcarrillo
 */
@Dependent
public class AuthorDAO implements AuthorDAOInterface, Serializable {
    @Inject
    private DbStrategy db;
    private DataSource ds;
    
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    /**
     * Empty Constructor.
     */    
    public AuthorDAO(){   
    }
    
    @Override
    public void initDAO(DataSource ds) throws SQLException {
        setDs(ds);
    }
    
    /**
     * Initializes the JDBC Connection properties
     * @param driverClass
     * @param url
     * @param username
     * @param password 
     */
    @Override
    public void initDAO(String driverClass, String url, String username, String password){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(username);
        setPassword(password);
    }
    
    /**
     * Returns a list of all authors in the Database.
     * @return authors
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public  List<Author> getAuthorList() throws ClassNotFoundException, 
            SQLException{
        if (ds == null){
            db.openConnection(driverClass, url, userName, password);
        } else {
            db.openConnection(ds);
        }
       
        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        List<Author> authors = new ArrayList<>();
        for(Map<String, Object> rec: records){
            Author author = new Author();
            int id = Integer.parseInt(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name").toString();
            author.setAuthorName(name != null ? name : "");
            Date date = (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }
        
        db.closeConnection();
        return authors;
    }
    
    @Override
    public Author getAuthorById(String id) throws ClassNotFoundException, SQLException {
        if (ds == null){
            db.openConnection(driverClass, url, userName, password);
        } else {
            db.openConnection(ds);
        }
        
        Map<String, Object> record = db.findRecordByKey("author", "author_id", id);
        
        Author a = new Author();
        a.setAuthorId(Integer.parseInt(record.get("author_id").toString()));
        a.setAuthorName(record.get("author_name").toString());
        a.setDateAdded((Date)record.get("date_added"));
        
        db.closeConnection();
        
        return a;
    }
    
    @Override
    public int deleteAuthorById(String id) throws ClassNotFoundException, 
            SQLException, NumberFormatException{
        
        Integer value = Integer.parseInt(id);
        
        if (ds == null){
            db.openConnection(driverClass, url, userName, password);
        } else {
            db.openConnection(ds);
        }
        
        int deleteCount = db.deleteRecordByKey("author", "author_id", value);
        db.closeConnection();
        
        return deleteCount;
    }
    
    @Override
    public void createAuthor(List<String> columns, List<Object> values) 
            throws ClassNotFoundException, SQLException{
        
        if (ds == null){
            db.openConnection(driverClass, url, userName, password);
        } else {
            db.openConnection(ds);
        }
        
        db.insertRecord("author", columns, values);
        db.closeConnection();
    }
    
    @Override
    public int updateAuthor(List<String> colNames, List<Object> colValues, 
            String primaryKeyCol, String id) throws ClassNotFoundException, SQLException {
         
        if (ds == null){
            db.openConnection(driverClass, url, userName, password);
        } else {
            db.openConnection(ds);
        }
        
        int updateCount = db.updateRecordByKey("author", colNames, colValues, primaryKeyCol, id);
        db.closeConnection();
        
        return updateCount;
    }

    public DbStrategy getDb() {
        return db;
    }

    public void setDb(DbStrategy db) {
        this.db = db;
    }
    
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        
//        AuthorDAOInterface dao = new AuthorDAO(new MySqlDbStrategy(), 
//                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        
////        List<Author> authors = dao.getAuthorList();
////        System.out.println(authors);
//        
////        dao.deleteAuthorById("2");
////        List<Author> authors = dao.getAuthorList();
////        System.out.println(authors);
//
//        List<String> columns = new ArrayList<>();
//        columns.add("author_name");
//        columns.add("date_added");
//        List<Object> vals = new ArrayList<>();
//        vals.add("Sam Smith");
//        vals.add("2013-02-28");
//        
//        dao.createAuthor(columns, vals);
//        
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);
//        
//    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
}
