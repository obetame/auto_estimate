package com.thoughtworks.estimate.dto.test;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCoverageReportItem {

  @CsvBindByName(column = "group")
  private String groupName;
  @CsvBindByName(column = "package")
  private String packageName;
  @CsvBindByName(column = "class")
  private String className;
  @CsvBindByName(column = "INSTRUCTION_MISSED")
  private Integer instructionMissed;
  @CsvBindByName(column = "INSTRUCTION_COVERED")
  private Integer instructionCovered;
  @CsvBindByName(column = "BRANCH_MISSED")
  private Integer branchMissed;
  @CsvBindByName(column = "BRANCH_COVERED")
  private Integer branchCovered;
  @CsvBindByName(column = "LINE_MISSED")
  private Integer lineMissed;
  @CsvBindByName(column = "LINE_COVERED")
  private Integer lineCovered;
  @CsvBindByName(column = "COMPLEXITY_MISSED")
  private Integer complexityMissed;
  @CsvBindByName(column = "COMPLEXITY_COVERED")
  private Integer complexityCovered;
  @CsvBindByName(column = "METHOD_MISSED")
  private Integer methodMissed;
  @CsvBindByName(column = "METHOD_COVERED")
  private Integer methodCovered;

  public double getTestCoverage() {
    return (double) this.getInstructionCovered() /
        (this.getInstructionMissed() + this.getInstructionCovered());
  }
}
