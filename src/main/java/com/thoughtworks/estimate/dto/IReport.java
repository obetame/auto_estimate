package com.thoughtworks.estimate.dto;

import com.thoughtworks.estimate.dto.summary.ViolationItem;
import java.util.List;

public interface IReport {

  double getScore();

  String getModuleName();

  List<ViolationItem> getViolationItems();

}
