package appen.dao;

import appen.domain.*;
import java.sql.*;

/**
 * An interface to handle performances.
 *
 * @param <T>
 * @param <K>
 *
 * @author Pete
 * @version 1.0
 * @since 1.1
 */
public interface perfDao<T, K> {

    void create(T Object) throws SQLException;
    
    double getBetterThan(T object) throws SQLException;
    
}
