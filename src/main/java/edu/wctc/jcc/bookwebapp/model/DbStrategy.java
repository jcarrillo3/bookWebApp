/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author jcarrillo
 */
public interface DbStrategy {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    
    Map<String, Object> findRecordByKey(String tableName, String colName, Object keyVal) throws SQLException;
    
    int deleteRecordByKey(String tableName, String primaryKeyCol, Object keyVal) throws SQLException;
    
    int updateRecordByKey(String tableName, List<String> columns, List<Object> values, String primaryKeyCol, Object keyVal) throws SQLException;

    void insertRecord(String tableName, List<String> columns, List<Object> values) throws SQLException;

    
    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    public abstract void openConnection(DataSource ds) throws SQLException;
}
