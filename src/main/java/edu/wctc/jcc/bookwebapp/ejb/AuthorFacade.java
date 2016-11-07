/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.ejb;

import edu.wctc.jcc.bookwebapp.model.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jcarrillo
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
//    @Override
//    public List<Author> findAll(){
//        List<Author> authors = null;
//        String jpql = "Select ";
//        Query query = this.getEntityManager().createQuery(qlString);
//    }
    
    public List<Author> findByName(String name) {
        List<Author> a = null;
        
        TypedQuery<Author> query = getEntityManager().createNamedQuery("Author.findByAuthorName", Author.class);
        query.setParameter("authorName", name);
        
        a = query.getResultList();
        
        return a;
    }
    
    public void removeById(Object id){
        String jpql = "Delete from Author a where a.authorId = ?1";
        
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter(1, id);
        
        query.executeUpdate();
    }
}
