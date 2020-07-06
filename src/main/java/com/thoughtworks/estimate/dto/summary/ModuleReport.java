package com.thoughtworks.estimate.dto.summary;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuleReport {

  private String module;
  private List<ViolationItem> violationItems;
  private Double score;
}
