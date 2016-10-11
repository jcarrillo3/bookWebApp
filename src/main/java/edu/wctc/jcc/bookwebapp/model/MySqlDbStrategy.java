/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.enterprise.context.Dependent;

/**
 *
 * @author jcarrillo
 */
@Dependent
public class MySqlDbStrategy implements DbStrategy, Serializable {
    private Connection conn;
    
    @Override
    public  void openConnection(String driverClass, String url, String userName, 
            String password) throws ClassNotFoundException, SQLException, IllegalArgumentException {
        
        if (driverClass.isEmpty() || url.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (driverClass == null || url == null || userName == null || password == null){
            throw new NullPointerException();
        }
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public  void closeConnection() throws SQLException{
        conn.close();
    }
    
    @Override
    public  List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) 
            throws SQLException, IllegalArgumentException{
        
        if (tableName == null || tableName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        while(rs.next()){
            Map<String, Object> record = new LinkedHashMap<>();
            for(int i=0; i < colCount; i++){
                String colName = rsmd.getColumnName(i+1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }
    
    @Override
    public  Map<String, Object> findRecordByKey(String tableName, 
            String colName, Object keyVal) throws SQLException, IllegalArgumentException{
        
        if(tableName == null || tableName.isEmpty() || colName == null 
                || colName.isEmpty() || keyVal == null) {
            throw new IllegalArgumentException();
        }
        if (tableName == null || colName == null || keyVal == null) {
            throw new NullPointerException();
        }
        String sql = "SELECT * FROM " + tableName + " WHERE " + colName + " = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, keyVal);
        ResultSet rs = ps.executeQuery();
        
        Map<String, Object> record = new LinkedHashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        while(rs.next()){
            for(int i=0; i < colCount; i++){
                String column = rsmd.getColumnName(i+1);
                Object colData = rs.getObject(column);
                record.put(column, colData);
            }
        }
        return record;
    }
    
    @Override
    public int deleteRecordByKey(String tableName, String primaryKeyCol, 
            Object keyVal) throws SQLException, IllegalArgumentException, NullPointerException{
        
        if (tableName.isEmpty()|| primaryKeyCol.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(tableName == null || primaryKeyCol == null || keyVal == null) {
            throw new NullPointerException();
        }
        
        PreparedStatement pstmt = this.buildDeleteStatement(tableName, 
                primaryKeyCol, keyVal);
        int deleteCount = pstmt.executeUpdate();
        
        return deleteCount;
    }
    
    private PreparedStatement buildDeleteStatement(String tableName, String primaryKeyCol, Object keyVal) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyCol + " = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, keyVal);
        return pstmt;
    }
    
    @Override
    public  int updateRecordByKey(String tableName, List<String> colNames, 
            List<Object> colValues, String primaryKeyCol, Object keyVal) 
            throws SQLException, IllegalArgumentException, NullPointerException {
        
        if (tableName.isEmpty() || colNames.size() < 1 || colValues.size() < 1 || primaryKeyCol.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (tableName == null || colNames == null || colValues == null || keyVal == null) {
            throw new NullPointerException();
        }

        PreparedStatement ps = this.buildUpdateStatement(tableName, colNames, colValues, primaryKeyCol, keyVal);
        int updateCount = ps.executeUpdate();
        ps.close();
        
        return updateCount;
    }
    
    private PreparedStatement buildUpdateStatement(String tableName, List<String> colNames, 
            List<Object> colValues, String primaryKeyCol, Object keyVal) throws SQLException {
        
        String sql = "UPDATE " + tableName + "\nSET ";
        StringJoiner sj = new StringJoiner(", ");
        for(int i =0; i < colNames.size(); i++){
            sj.add(colNames.get(i) + "=?");
        }
        sql += sj.toString() + "\nWHERE " + primaryKeyCol + "=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0; i < colValues.size(); i++){
            ps.setObject(i +1, colValues.get(i));
        }
        ps.setObject(colValues.size() + 1, keyVal);
        int updateCount = ps.executeUpdate();
        
        return ps;
    }
    
    
    @Override
    public  void insertRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException, IllegalArgumentException, NullPointerException {
        
        if (tableName.isEmpty() || colNames.size() < 1 || colValues.size() < 1) {
            throw new IllegalArgumentException();
        }
        if (tableName == null || colNames == null || colValues == null){
            throw new NullPointerException();
        }

        PreparedStatement ps = this.buildInsertMethod(tableName, colNames, colValues);
        
        ps.executeUpdate();
        ps.close();
    }
    
    private PreparedStatement buildInsertMethod(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException{
        
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner cols = new StringJoiner(",", "(", ")");
        for(int i =0; i < colNames.size(); i++){
            cols.add(colNames.get(i));
        }
        sql += cols.toString() + "\n VALUES ";
        StringJoiner vals = new StringJoiner(",", "(", ")");
        for(int i=0; i < colValues.size(); i++){
            vals.add("?");
        }
        sql += vals.toString();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0; i < colValues.size(); i++){
            ps.setObject(i +1, colValues.get(i));
        }
        return ps;
    }
    
    public static void main(String[] args) throws Exception {
        MySqlDbStrategy db = new MySqlDbStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        List<Map<String, Object>> records = db.findAllRecords("author", 500);
//        System.out.println(records);

//        Map<String, Object> record = db.findRecordByKey("author", "author_id", 2);
//        System.out.println(record);

//        db.deleteRecordByKey("author", "author_id", 3);
//        System.out.println(db.deleteRecordByKey("author", "author_id", 4) + " record(s) deleted");
        
        List<String> columns = new ArrayList<>();
        columns.add("author_name");
        columns.add("date_added");
        List<Object> vals = new ArrayList<>();
        vals.add("Jake Ford");
        vals.add("2014-11-04");
        
        System.out.println(db.updateRecordByKey("author", columns, vals, "author_id", "10"));
        
        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);
        db.closeConnection();
    }
}
