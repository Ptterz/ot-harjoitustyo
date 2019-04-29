package appen.dao;

import java.sql.*;
import java.util.List;

/**
 * An interface for Dao classes.
 * 
 * @param <T> The class of an object.
 * @param <K> The class of a key of the object.
 * 
 * @author Pete
 * @version 1.0
 * @since 1.0
 */
public interface Dao<T, K> {
    
    /**
     * Creates a new entry to a SQL table.
     * @param object Contains the information that is added.
     * @throws SQLException Throws the expection to one that called it.
     * @since 1.0
     */
    void create(T object) throws SQLException;
    
    /**
     * Reads a specific row from the SQL table.
     * @param key A primary key to fetch a specific information.
     * @return Returns the information as an object of class T.
     * @throws SQLException Throws the expection to one that called it.
     * @since 1.0
     */
    T read(K key) throws SQLException;
    
    /**
     * Updates a specific row in the SQL table.
     * @param object An object of class T that contains the primary key 
     * and the new information.
     * @return Returns the information as an object of class T.
     * @throws SQLException Throws the expection to one that called it.
     * @since 1.0
     */
    T update(T object) throws SQLException;
    
    /**
     * Deletes a specific row from the SQL table.
     * @param key A primary key of the row that is to be deleted.
     * @throws SQLException Throws the expection to one that called it.
     * @since 1.0
     */
    void delete(K key) throws SQLException;
    
    /**
     * Returns a list of objects of class T.
     * @return A list of objects of class T.
     * @throws SQLException Throws the expection to one that called it.
     * @since 1.0
     */
    List<T> list() throws SQLException;
}
