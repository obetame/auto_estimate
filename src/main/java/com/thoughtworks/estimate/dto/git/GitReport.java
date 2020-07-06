package com.thoughtworks.estimate.dto.git;

import com.thoughtworks.estimate.config.GitConfig;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;

@Builder
public class GitReport implements IReport {

  private final List<GitReportItem> items;
  private final Integer commitQuantity;

  @Override
  public double getScore() {
    return Math.max(GitConfig.getCommitMessageTotalScore() - getCommitMessageDeductScore(), 0) +
        Math.max(GitConfig.getCommitQuantityTotalScore() - getCommitQuantityDeductScore(), 0);
  }

  @Override
  public String getModuleName() {
    return "Git Commit";
  }

  @Override
  public List<ViolationItem> getViolationItems() {
    final List<ViolationItem> violationItemsOfCommitMessage = items.stream()
        .map(GitReportItem::getViolationItems)
        .collect(Collectors.toList());

    return isViolationOfCommitQuantity() ?
        Stream.concat(Stream.of(this.getViolationItemOfCommitQuantity()),
            violationItemsOfCommitMessage.stream())
            .collect(Collectors.toList())
        : violationItemsOfCommitMessage;
  }

  private boolean isViolationOfCommitQuantity() {
    return commitQuantity < GitConfig.getCommitMinimumQuantity();
  }

  private double getCommitMessageDeductScore() {
    return items.stream()
        .map(GitReportItem::getViolationItems)
        .mapToDouble(ViolationItem::getDeductScore)
        .sum();
  }

  private ViolationItem getViolationItemOfCommitQuantity() {
    return ViolationItem.builder()
        .position("Git Commit")
        .rule("Git commit quantity less than min quantity")
        .deductScore(this.getCommitQuantityDeductScore())
        .build();
  }

  private double getCommitQuantityDeductScore() {
    return GitConfig.getCommitQuantityTotalScore() * Math
        .max((1 - this.commitQuantity / GitConfig.getCommitMinimumQuantity()), 0);
  }
}
