package com.exercise.helpers;

public enum BoxType {
  MediumBoxHeavyDuty("Medium Box Heavy Duty"),
  JumboBoxHeavyDuty("Jumbo Box Heavy Duty");

  private String boxType;

  BoxType(String boxType) {
    this.boxType = boxType;
  }
  public String getBoxTitle() {
    return boxType;
  }
}
