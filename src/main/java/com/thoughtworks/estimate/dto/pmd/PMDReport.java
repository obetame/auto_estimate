package com.thoughtworks.estimate.dto.pmd;

import com.thoughtworks.estimate.configuration.PMDConfiguration;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PMDReport implements IReport {

  private String pmdVersion;

  private PMDFileReport[] files;

  private String formatVersion;

  private String timestamp;

  @Override
  public double getScore() {
    final double deductScore = this.getViolationItems().stream()
        .mapToDouble(ViolationItem::getDeductScore)
        .sum();

    return Math.max(0d, PMDConfiguration.getCodeSmellTotalScore() - deductScore);
  }

  @Override
  public String getModuleName() {
    return "Code Smell";
  }

  @Override
  public List<ViolationItem> getViolationItems() {
    return Arrays.stream(files)
        .flatMap(pmdFileReport -> pmdFileReport.getViolationItems().stream())
        .collect(Collectors.toList());
  }
}

