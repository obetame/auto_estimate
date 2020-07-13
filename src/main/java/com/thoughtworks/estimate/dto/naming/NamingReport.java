package com.thoughtworks.estimate.dto.naming;

import com.google.common.collect.Lists;
import com.thoughtworks.estimate.configuration.NamingConfiguration;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NamingReport implements IReport {

  private Double typeScore;
  private Double methodScore;
  private Double variableScore;

  @Override
  public double getScore() {
    return typeScore + methodScore + variableScore;
  }

  @Override
  public String getModuleName() {
    return "naming";
  }

  @Override
  public List<ViolationItem> getViolationItems() {
    final double deductScore = NamingConfiguration.getNamingTotalScore() - getScore();
    return deductScore == 0
        ? Lists.newArrayList()
        : Lists.newArrayList(ViolationItem.builder()
            .position("Naming")
            .rule("Names with business meaning")
            .deductScore(deductScore)
            .build());
  }
}
