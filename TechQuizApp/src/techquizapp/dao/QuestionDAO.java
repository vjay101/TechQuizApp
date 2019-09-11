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
import java.util.ArrayList;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.QuestionPojo;

/**
 *
 * @author Sameer
 */
public class QuestionDAO {
    public static ArrayList <QuestionPojo> getQuestionByEaxmId(String examId) throws SQLException
    {
        String qry = "select * from questions where examid = ? order by qno";
        ArrayList<QuestionPojo> questionList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, examId);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            int qno = rs.getInt("qno");
            String question = rs.getString("question");
            String option1 = rs.getString("answer1");
            String option2 = rs.getString("answer2");
            String option3 = rs.getString("answer3");
            String option4 = rs.getString("answer4");
            String correctAnswer = rs.getString("correct_answer");
            String language = rs.getString("language");
            QuestionPojo obj = new QuestionPojo(examId,qno,question,option1,option2,option3,option4,correctAnswer,language);
            questionList.add(obj);
        }
        return questionList;
    }
}
