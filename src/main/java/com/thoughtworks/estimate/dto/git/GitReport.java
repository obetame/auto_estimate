package com.thoughtworks.estimate.dto.git;

import com.thoughtworks.estimate.config.GitConfig;
import com.thoughtworks.estimate.dto.IReport;
import java.util.List;

public class GitReport implements IReport {

  private final List<GitReportItem> items;

  public GitReport(List<GitReportItem> items) {
    this.items = items;
  }

  @Override
  public double getScore() {
    return getCommitQuantityScore(items.size()) + getCommitMessageScore();
  }

  private double getCommitMessageScore() {
    final double commitMessageDeductScore = getCommitMessageDeductScore();
    return Math.max(GitConfig.getCommitMessageTotalScore() - commitMessageDeductScore, 0);
  }

  private double getCommitMessageDeductScore() {
    return items.stream()
        .map(GitReportItem::getCommitMessage)
        .map(String::length)
        .mapToDouble(length -> length < GitConfig.getCommitMessageMinimumLength() ? GitConfig
            .getCommitMessageLengthDeductScore()
            : 0)
        .sum();
  }

  private double getCommitQuantityScore(int commitQuantity) {
    return GitConfig.getCommitQuantityTotalScore() * Math
        .min((commitQuantity / GitConfig.getCommitMinimumQuantity()), 1);
  }
}
