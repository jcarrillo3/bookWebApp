/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author jcarrillo
 */
public class Author {
    private int authorId;
    private String authorName;
    private Date dateAdded;
    private final String INVALID_DATE = "Invalid Date value.";
    private final String INVALID_NAME = "Invalid Author Name.";
    private final String INVALID_ID = "Invalid Author ID.";
    private final String INVALID_NAME_OR_ID = "Invalid Author Name and/or ID.";

    public Author() {
    }
    
    /**
     * 
     * @param authorId
     * @param authorName
     * @param dateAdded 
     */
    public Author(int authorId, String authorName, Date dateAdded)throws IllegalArgumentException, NullPointerException {
        if (authorId < 1 || authorName == null || authorName.isEmpty()){
            throw new IllegalArgumentException(INVALID_NAME_OR_ID);
        }
        if (dateAdded == null){
            throw new NullPointerException(INVALID_DATE);
        }
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    public final int getAuthorId() {
        return authorId;
    }

    public final void setAuthorId(int authorId) throws IllegalArgumentException {
        if (authorId < 1){
            throw new IllegalArgumentException(INVALID_ID);
        } 
        this.authorId = authorId;
    }

    public final String getAuthorName() {
        return authorName;
    }
    /**
     * 
     * @param authorName
     * @throws IllegalArgumentException 
     */
    public final void setAuthorName(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.isEmpty()){
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.authorName = authorName;
    }

    public final Date getDateAdded() {
        return dateAdded;
    }
    
    /**
     * 
     * @param dateAdded
     * @throws NullPointerException 
     */
    public final void setDateAdded(Date dateAdded) throws NullPointerException {
        if (dateAdded == null){
            throw new NullPointerException(INVALID_DATE);
        }
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.authorId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    
}
