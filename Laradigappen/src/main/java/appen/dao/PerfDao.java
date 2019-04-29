package appen.dao;

import appen.domain.*;
import java.sql.*;

/**
 * An interface to handle performances.
 *
 * @param <T> The class of an object.
 * @param <K> The class of a key of the object.
 *
 * @author Pete
 * @version 1.0
 * @since 1.1
 */
public interface PerfDao<T, K> {

    /**
     * Method is used to create an entry to the database.
     *
     * @param object An object containing all the info.
     * @throws SQLException Is handled elsewhere
     */
    void create(T object) throws SQLException;

    /**
     * Method is used to calculate who performed worse.
     *
     * @param object An object containing all the info.
     * @return Returns the percentage in double format
     * @throws SQLException Is handled elsewhere
     */
    double getBetterThan(T object) throws SQLException;

}
