/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

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

    /**
     * Empty Constructor.
     */
    public Author() {
    }
    
    /**
     * Initializes an Author with ID only.
     * @param authorId
     * @throws IllegalArgumentException if authorId is less than 1. 
     */
    public Author(int authorId) throws IllegalArgumentException{
        setAuthorId(authorId);
    }
    
    /**
     * Initializes an Author with ID, Name, and Date Added.
     * @param authorId
     * @param authorName
     * @param dateAdded
     * @throws IllegalArgumentException if parameters are invalid.
     * @throws NullPointerException if any parameter is null.
     */
    public Author(int authorId, String authorName, Date dateAdded) throws IllegalArgumentException, NullPointerException {
        setAuthorId(authorId);
        setAuthorName(authorName);
        setDateAdded(dateAdded);
    }

    /**
     * Returns the ID of the Author.
     * @return authorId
     */
    public final int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the Author ID.
     * @param authorId
     * @throws IllegalArgumentException if the authorId is less than 1.
     */
    public final void setAuthorId(int authorId) throws IllegalArgumentException {
        if (authorId < 1) {
            throw new IllegalArgumentException(INVALID_ID);
        }
        this.authorId = authorId;
    }

    /**
     * Returns the name of the Author.
     * @return authorName
     */
    public final String getAuthorName() {
        return authorName;
    }

    /**
     *Sets the value of the Author name.
     * @param authorName
     * @throws IllegalArgumentException if the author name is empty or null.
     */
    public final void setAuthorName(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.authorName = authorName;
    }

    /**
     * Returns the Author's date added.
     * @return dateAdded
     */
    public final Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the value of the Author date added.
     * @param dateAdded
     * @throws NullPointerException if dateAdded is null
     */
    public final void setDateAdded(Date dateAdded) throws NullPointerException {
        if (dateAdded == null) {
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
