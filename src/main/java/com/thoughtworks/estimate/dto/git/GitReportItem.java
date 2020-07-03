package com.thoughtworks.estimate.dto.git;

import lombok.Builder;
import lombok.Data;
import org.eclipse.jgit.revwalk.RevCommit;

@Data
@Builder
public class GitReportItem {

  private String commitMessage;

  public static GitReportItem from(RevCommit revCommit) {
    return GitReportItem.builder().commitMessage(revCommit.getFullMessage()).build();
  }

}
