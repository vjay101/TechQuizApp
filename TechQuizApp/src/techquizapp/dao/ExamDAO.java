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
import techquizapp.pojo.ExamPojo;
import techquizapp.pojo.QuestionPojo;
import techquizapp.pojo.QuestionStore;

/**
 *
 * @author Sameer
 */
public class ExamDAO {
    public static String getExamId()throws SQLException
    {
        String qry = "Select count(*) as totalrows from Exam";
        int rowCount = 0;
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
        if(rs.next())
            rowCount = rs.getInt(1);
        String newId = "EX-"+(rowCount + 1);
        return newId;
    }
    public static boolean addExam(ExamPojo newExam) throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into exam values(?,?,?)");
        ps.setString(1, newExam.getExamId());
        ps.setString(2, newExam.getLanguage());
        ps.setInt(3,newExam.getTotalQuestions());
        int ans = ps.executeUpdate();
        return ans==1;
    }
    public static void addQuestion(QuestionStore qstore)throws SQLException
    {
        String qry = "Insert into questions values(?,?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ArrayList<QuestionPojo> questionList = qstore.getAllQuestions();
        for(QuestionPojo qp:questionList)
        {
            ps.setString(1, qp.getExamId());
            ps.setInt(2, qp.getQno());
            ps.setString(3, qp.getQuestion());
            ps.setString(4, qp.getAnswer1());
            ps.setString(5, qp.getAnswer2());
            ps.setString(6, qp.getAnswer3());
            ps.setString(7, qp.getAnswer4());
            ps.setString(8, qp.getCorrectAnswer());
            ps.setString(9, qp.getLanguage());
            ps.executeUpdate();
        }
    }
    public static boolean isPaperSet(String subject)throws SQLException{
        String qry = "select examid from exam where language = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
         ps.setString(1, subject);
         ResultSet rs = ps.executeQuery();
         return rs.next();
    }
    public static ArrayList<String> getExamIdBySubject(String userid,String subject)throws SQLException{
        String qry = "select examid from exam where language = ? minus select examid from performance where userid = ?";
        ArrayList<String> examList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, subject);
        ps.setString(2, userid);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            examList.add(rs.getString(1));
        }
        return examList;
    }
    public static int getQuestionCountByExam(String examId) throws SQLException
    {
        String qry = "select TOTAL_QUESTION from exam where examid = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, examId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static ArrayList<String> getExamIdBySubject2(String subject)throws SQLException 
    {
        String qry = "select examid from exam where language = ?";
        ArrayList<String> examList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, subject);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            examList.add(rs.getString(1));
        }
        return examList;
    }
    public static void updateQuestion(QuestionStore qstore)throws SQLException
    {
        String qry = "update questions set question=?,answer1 =?,answer2 = ?,answer3=?,answer4=?,correct_answer=?,language=? where qno = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ArrayList<QuestionPojo> questionList = qstore.getAllQuestions();
        for(QuestionPojo qp:questionList)
        {
            ps.setString(1, qp.getQuestion());
            ps.setString(2, qp.getAnswer1());
            ps.setString(3, qp.getAnswer2());
            ps.setString(4, qp.getAnswer3());
            ps.setString(5, qp.getAnswer4());
            ps.setString(6, qp.getCorrectAnswer());
            ps.setString(7, qp.getLanguage());
            ps.setInt(8, qp.getQno());
            ps.executeUpdate();
        }
    }
}
