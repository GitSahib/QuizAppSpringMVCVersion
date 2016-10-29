package com.mems.domain.quiz;
public class QuestionBuilder {
  
  Question question;
  public QuestionBuilder() {
    question = new Question();
  }
  
  public QuestionBuilder title(String title) {
    question.setText(title);
    return this;
  }
  
  public QuestionBuilder choice(Choice choice) {
    question.getChoices().add(choice);
    return this;
  }
  
  public Question asQuestion() {
    return question;
  }
}