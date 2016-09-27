/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDAOInterface dao = new AuthorDAO(new MySqlDbStrategy(), 
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        AuthorService service = new AuthorService(dao);
        System.out.println(service.getAuthors());
    }
    
    
}
