package com.thoughtworks.estimate.dto.summary;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndividualReport {

  private String name;
  private Double score;
  private List<ModuleReport> moduleReports;
  private boolean hasException;
}
