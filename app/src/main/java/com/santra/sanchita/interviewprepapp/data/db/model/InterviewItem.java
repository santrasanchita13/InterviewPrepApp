package com.santra.sanchita.interviewprepapp.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sanchita on 9/12/17.
 */

@Entity(nameInDb = "interview_item")
public class InterviewItem {

    @Id
    private String question;

    @Property(nameInDb = "answer")
    @Unique
    private String answer;

    @Property(nameInDb = "solved")
    private Boolean solved;

    @Generated(hash = 1861020798)
    public InterviewItem(String question, String answer, Boolean solved) {
        this.question = question;
        this.answer = answer;
        this.solved = solved;
    }

    @Generated(hash = 527979652)
    public InterviewItem() {
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getSolved() {
        return this.solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }


}
