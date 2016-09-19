/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jcarrillo
 */
public class AuthorService {
    private List<Author> authors;
    
    public AuthorService(){
        authors = new ArrayList<>();
        authors.add(new Author(21, "Stan Jackson", new Date()));
        authors.add(new Author(8, "Melvin Price", new Date()));
        authors.add(new Author(57, "Bob Stewart", new Date()));
    }

    public List getAuthors() {
        return authors;
    }
    
    
}
