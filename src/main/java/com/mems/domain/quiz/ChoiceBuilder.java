package com.mems.domain.quiz;
public class ChoiceBuilder {
  
  Choice choice;
  public ChoiceBuilder() {
	  choice = new Choice();
  }
  
  public ChoiceBuilder title(String title) {
	  choice.setText(title);
    return this;
  }
  public Choice booleanChoice(boolean onOff)
  {
	return choice;
	 
  }
  public ChoiceBuilder choice(Choice choice) {
	this.choice = choice;
    return this;
  }
  
  public Choice asChoice() {
    return choice;
  }
}