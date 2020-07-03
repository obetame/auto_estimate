package com.thoughtworks.estimate.dto.pmd;

import lombok.Data;

@Data
public class PMDFileReport {

  private String filename;

  private Violations[] violations;

}
