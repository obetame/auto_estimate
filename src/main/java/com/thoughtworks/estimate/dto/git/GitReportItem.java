package com.thoughtworks.estimate.dto.git;

import com.thoughtworks.estimate.configuration.GitConfiguration;
import com.thoughtworks.estimate.dto.summary.ViolationItem;
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

  public ViolationItem getViolationItems() {
    return ViolationItem.builder()
        .position("Git Commit: " + commitMessage)
        .rule("Commit message is short")
        .deductScore(GitConfiguration.getCommitMessageLengthDeductScore())
        .build();
  }

}
