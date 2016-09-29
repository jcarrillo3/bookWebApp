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
import java.util.Map;

/**
 *
 * @author jcarrillo
 */
public class AuthorDAO implements AuthorDAOInterface {
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDAO(DbStrategy db, String driverClass, String url, String userName, 
            String password) {
        
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    
    public AuthorDAO(DbStrategy db){
        this.db = db;
    }
    
    @Override
    public  List<Author> getAuthorList() throws ClassNotFoundException, 
            SQLException{
        
        db.openConnection(driverClass, url, userName, password);
        
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
        
        db.openConnection(driverClass, url, userName, password);
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
        
        db.openConnection(driverClass, url, userName, password);
        int deleteCount = db.deleteRecordByKey("author", "author_id", value);
        db.closeConnection();
        
        return deleteCount;
    }
    
    @Override
    public void createAuthor(List<String> columns, List<Object> values) 
            throws ClassNotFoundException, SQLException{
        
        db.openConnection(driverClass, url, userName, password);
        db.insertRecord("author", columns, values);
        db.closeConnection();
    }
    
    @Override
    public int updateAuthor(List<String> colNames, List<Object> colValues, 
            String primaryKeyCol, String id) throws ClassNotFoundException, SQLException {
         
        db.openConnection(driverClass, url, userName, password);
        
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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        AuthorDAOInterface dao = new AuthorDAO(new MySqlDbStrategy(), 
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);
        
//        dao.deleteAuthorById("2");
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);

        List<String> columns = new ArrayList<>();
        columns.add("author_name");
        columns.add("date_added");
        List<Object> vals = new ArrayList<>();
        vals.add("Sam Smith");
        vals.add("2013-02-28");
        
        dao.createAuthor(columns, vals);
        
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
        
    }
}
