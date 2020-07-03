package com.thoughtworks.estimate.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class EstimateResult {

  private String name;
  private double score;
}
