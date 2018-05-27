package com.example.day08_06;

/**
 * Created by apple on 2017/2/23.
 */

public class Question {
    private String title;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private int score;
    private int level;

    public Question() {
    }

    public Question(String title, String optiona, String optionb,
                    String optionc, String optiond, String answer, int score, int level) {
        this.title = title;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.optiond = optiond;
        this.answer = answer;
        this.score = score;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return title + "\n"
                + optiona + "\n"
                + optionb + "\n"
                + optionc + "\n"
                + optiond + "\n";

    }
}
