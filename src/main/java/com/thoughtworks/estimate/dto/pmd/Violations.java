package com.thoughtworks.estimate.dto.pmd;

import lombok.Data;

@Data
public class Violations {

  private String endline;

  private String beginline;

  private String ruleset;

  private String begincolumn;

  private String description;

  private String rule;

  private String externalInfoUrl;

  private String priority;

  private String endcolumn;

}
