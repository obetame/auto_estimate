package com.thoughtworks.estimate.configuration;

public class GitConfiguration {

  private static final double COMMIT_MESSAGE_MINIMUM_LENGTH = 8;
  private static final double COMMIT_MESSAGE_LENGTH_DEDUCT_SCORE = 0.5;
  private static final double COMMIT_MESSAGE_TOTAL_SCORE = 3;
  private static final double COMMIT_MINIMUM_QUANTITY = 10;
  private static final double COMMIT_QUANTITY_TOTAL_SCORE = 7;

  private GitConfiguration() {
  }

  public static double getCommitMessageMinimumLength() {
    return COMMIT_MESSAGE_MINIMUM_LENGTH;
  }

  public static double getCommitMessageLengthDeductScore() {
    return COMMIT_MESSAGE_LENGTH_DEDUCT_SCORE;
  }

  public static double getCommitMessageTotalScore() {
    return COMMIT_MESSAGE_TOTAL_SCORE;
  }

  public static double getCommitMinimumQuantity() {
    return COMMIT_MINIMUM_QUANTITY;
  }

  public static double getCommitQuantityTotalScore() {
    return COMMIT_QUANTITY_TOTAL_SCORE;
  }
}
