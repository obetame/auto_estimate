package com.thoughtworks.estimate.dto.summary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViolationItem {

  private String position;
  private String rule;
  private Double deductScore;
}
