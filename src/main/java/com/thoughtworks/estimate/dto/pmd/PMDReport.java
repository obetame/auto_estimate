package com.thoughtworks.estimate.dto.pmd;

import com.thoughtworks.estimate.config.PMDConfig;
import com.thoughtworks.estimate.dto.IReport;
import java.util.Arrays;
import lombok.Data;

@Data
public class PMDReport implements IReport {

  private String pmdVersion;

  private PMDFileReport[] files;

  private String formatVersion;

  private String timestamp;

  @Override
  public double getScore() {
    final double deductScore = Arrays.stream(files)
        .flatMap(pmdFileReport -> Arrays.stream(pmdFileReport.getViolations()))
        .map(Violations::getPriority)
        .map(priority -> PMDConfig.getCodeSmellPriorityScoreMap().getOrDefault(priority, 0d))
        .mapToDouble(Double::doubleValue)
        .sum();
    return Math.max(0d, PMDConfig.getCodeSmellTotalScore() - deductScore);
  }
}

