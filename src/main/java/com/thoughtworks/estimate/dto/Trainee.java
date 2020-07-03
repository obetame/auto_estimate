package com.thoughtworks.estimate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trainee {

  private String name;
  private String repo;
}
