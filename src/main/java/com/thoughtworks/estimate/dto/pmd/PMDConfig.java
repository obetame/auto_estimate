package com.thoughtworks.estimate.dto.pmd;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PMDConfig {

  private String jsonReportFileName;
  private double codeSmellTotalScore;
  private Map<String, Double> codeSmellPriorityScoreMap;
  private String xmlPmdRulesetsFileName;


}
