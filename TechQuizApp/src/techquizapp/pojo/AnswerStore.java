/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.pojo;

import java.util.ArrayList;

/**
 *
 * @author Sameer
 */
public class AnswerStore {

    ArrayList<AnswerePojo>answerList;

    public ArrayList<AnswerePojo> getAnswerList() {
        return answerList;
    }
    public void addAnswere(AnswerePojo answer)
    {
        answerList.add(answer);
    }
    public void removeAnswer(int pos)
    {
        answerList.remove(pos);
    }
    public void setAnswerAt(int pos,AnswerePojo answer)
    {
        answerList.set(pos, answer);
    }
    public AnswerePojo getAnswer(int pos)
    {
        return answerList.get(pos);
    }
    public AnswerePojo getAnswerByQno(int qno)
    {
        for(AnswerePojo answer: answerList){
            if(answer.getQno()==qno)
                return answer;
        }
        return null;

    }

    public AnswerStore() {
        answerList = new ArrayList<>();
    }
    public int removeAnswer(AnswerePojo answer)
    {
        int pos = answerList.indexOf(answer);
        answerList.remove(pos);
        return pos;
    }
    public int getCount()
    {
        return answerList.size();
    }
    public ArrayList<AnswerePojo>getAllAnswers()
    {
        return answerList;
    }
    public void setAnswereList(ArrayList<AnswerePojo>answerList) {
        this.answerList = answerList;
    }
    public AnswerStore(ArrayList<AnswerePojo>answerList) {
        this.answerList = answerList;
    }
}
