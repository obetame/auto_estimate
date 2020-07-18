package com.thoughtworks.estimate.dto.git;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GitConfig {

  private double messageLengthBaseline;
  private double messageLengthDeductScore;
  private double messageTotalScore;
  private double minimumQuantity;
  private double quantityTotalScore;

}
