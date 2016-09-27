/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

/**
 *
 * @author jcarrillo
 */
public class MySqlDbStrategy implements DbStrategy {
    private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, String userName, 
            String password) throws ClassNotFoundException, SQLException{
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException{
        conn.close();
    }
    
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException{
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
    public Map<String, Object> findRecordByKey(String tableName, String colName, int keyVal) throws SQLException{
        String sql = "SELECT * FROM " + tableName + " WHERE " + colName + " = " + keyVal;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
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
    public int deleteRecordByKey(String tableName, String primaryKeyCol, Object keyVal) throws SQLException{
        
        PreparedStatement pstmt = this.buildDeleteStatement(tableName, primaryKeyCol, keyVal);
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
    public int updateRecordByKey(String tableName, List<String> columns, 
            List<Object> values, String primaryKeyCol, int keyVal) throws SQLException{
        
        String sql = "UPDATE " + tableName + "\nSET ";
        for(int i=0; i < columns.size(); i++){
            if (i < columns.size()-1) {
                sql += columns.get(i) + "=?, ";
            }
            else {
                sql += columns.get(i) + "=? WHERE " + primaryKeyCol + "=" + keyVal;
            }
        } 
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0; i < values.size(); i++){
            ps.setObject(i+1, values.get(i));
        }
        int updateCount = ps.executeUpdate(sql);
        ps.close();
        return updateCount;
    }
    
    public final void insertRecord(String tableName, List<String> colNames, 
            List<Object> colValues){
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner cols = new StringJoiner(",", "(", ")");
        for(int i =0; i < colNames.size(); i++){
            cols.add(colNames.get(i))
        }
        
    }
    
    private void buildInsertMethod(String tableName, List<String> colNames, 
            List<Object> colValues){
        
    }
    
    public static void main(String[] args) throws Exception {
        MySqlDbStrategy db = new MySqlDbStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        List<Map<String, Object>> records = db.findAllRecords("author", 500);
//        System.out.println(records);

//        Map<String, Object> record = db.findRecordByKey("author", "author_id", 3);
//        System.out.println(record);

//        db.deleteRecordByKey("author", "author_id", 3);
        System.out.println(db.deleteRecordByKey("author", "author_id", 4) + " record(s) deleted");
        
//        List<String> columns = new ArrayList<>();
//        columns.add("author_name");
//        columns.add("date_added");
//        List<Object> vals = new ArrayList<>();
//        vals.add("Ben Benson");
//        vals.add("2015-02-19");
        
        //List<Map<String, Object>> records = db.findAllRecords("author", 500);
        //System.out.println(db.updateRecordByKey("author", columns, vals, "author_id", 4));
        db.closeConnection();
    }
}
