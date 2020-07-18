package com.thoughtworks.estimate.dto.test;

import com.thoughtworks.estimate.configuration.TestCoverageConfiguration;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCoverageReport implements IReport {

  private List<TestCoverageReportItem> items;

  @Override
  public double getScore() {
    return Math.max(TestCoverageConfiguration.getTestCoverageTotalScore() - getDeductScore(), 0);
  }

  @Override
  public String getModuleName() {
    return "Test Coverage ";
  }

  @Override
  public List<ViolationItem> getViolationItems() {
    return items.stream()
        .filter(
            item -> item.getTestCoverage() < TestCoverageConfiguration.getTestCoverageBaseLine())
        .map(this::getViolationItem)
        .collect(Collectors.toList());
  }

  private double getDeductScore() {
    return this.getViolationItems().stream().mapToDouble(ViolationItem::getDeductScore).sum();
  }

  private ViolationItem getViolationItem(TestCoverageReportItem item) {
    final double deductScore =
        (1 - item.getTestCoverage() / TestCoverageConfiguration.getTestCoverageBaseLine()) *
            TestCoverageConfiguration.getTestCoverageTotalScore() / items.size();
    return ViolationItem.builder()
        .position(item.getClassName())
        .rule("Insufficient test coverage")
        .deductScore(deductScore)
        .build();
  }
}
