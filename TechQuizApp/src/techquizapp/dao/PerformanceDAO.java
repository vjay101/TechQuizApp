/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.Performance;
import techquizapp.pojo.StudentScore;

/**
 *
 * @author Sameer
 */
public class PerformanceDAO {
    public static ArrayList<String> getAllStudentId()throws SQLException
    {
        String qry = "select distinct userid from performance";
        ArrayList<String> studentList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
        while(rs.next())
        {
            String id = rs.getString(1);
            studentList.add(id);
        }
        return studentList;
    }
    public static ArrayList<String> getAllExamId(String studentId)throws SQLException
    {
        String qry = "select examid from performance where userid = ?";
        ArrayList<String> examList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, studentId);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            String id = rs.getString(1);
            examList.add(id);
        }
        return examList;
    }
    public static StudentScore getScore(String studentId,String examId)throws SQLException
    {
        String qry = "select language,per from performance where userid = ? and examid = ?";
        StudentScore ss= new StudentScore();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, studentId);
        ps.setString(2, examId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        ss.setLanguage(rs.getString(1));
        ss.setPer(rs.getDouble(2));
        
        return ss;
    }
    public static void addPerformance(Performance performance)throws SQLException
    {
        String qry = "Insert into performance values(?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, performance.getUserId());
        ps.setString(2, performance.getExamId());
        ps.setInt(3, performance.getRight());
        ps.setInt(4, performance.getWrong());
        ps.setInt(5, performance.getUnattempted());
        ps.setDouble(6, performance.getPer());
        ps.setString(7, performance.getLanguage());
        ps.executeUpdate();
    }
    public static ArrayList<Performance> getPerformance()throws SQLException
    {
        String qry = "Select * from performance";
        Connection conn = DBConnection.getConnection();
        ArrayList<Performance> performanceList = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
        while(rs.next())
        {
            String userid= rs.getString("userid");
            String examid= rs.getString("examid");
            int right = rs.getInt("right");
            int wrong = rs.getInt("wrong");
            int unatt = rs.getInt("unattempted");
            double per = rs.getDouble("per");
            String lang= rs.getString("language");
            Performance obj = new Performance(examid,lang,userid,right,wrong,unatt,per);
            performanceList.add(obj);
        }
        return performanceList;
    }
}
