package com.thoughtworks.estimate.dto.pmd;

import com.thoughtworks.estimate.configuration.PMDConfiguration;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
import com.thoughtworks.estimate.utils.FileUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PMDFileReport {

  private String filename;

  private PMDViolation[] violations;

  public List<ViolationItem> getViolationItems() {
    return Arrays.stream(violations).map(
        pmdViolation -> ViolationItem.builder()
            .position(getFormattedPosition(pmdViolation))
            .rule(getFormattedRule(pmdViolation))
            .deductScore(getDeductScore(pmdViolation))
            .build()
    ).collect(Collectors.toList());
  }

  private Double getDeductScore(PMDViolation pmdViolation) {
    return PMDConfiguration
        .getCodeSmellPriorityScoreMap().getOrDefault(pmdViolation.getPriority(), 0d);
  }

  private String getFormattedRule(PMDViolation pmdViolation) {
    return String.format("%s : %s", pmdViolation.getRule(), pmdViolation.getDescription());
  }

  private String getFormattedPosition(PMDViolation pmdViolation) {
    return String.format("filename: %s beginline: %s endline: %s",
        FileUtils.getFileNameByPath(this.filename),
        pmdViolation.getBeginline(), pmdViolation.getEndline());
  }

}
