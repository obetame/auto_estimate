package com.thoughtworks.estimate;

import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;
import com.thoughtworks.estimate.config.CommonConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.Data;
import lombok.SneakyThrows;
import org.eclipse.jgit.api.Git;

@Data
public class GitRepo {

  private final File file;
  private final Git git;

  public GitRepo(String gitRepo) {
    final String path = Paths.get(CommonConfig.getWorkDirectory(), UUID.randomUUID().toString())
        .toString();
    this.file = FileUtils.create(path);
    this.git = this.clone(gitRepo, this.file);
  }

  @lombok.SneakyThrows
  private Git clone(String gitRepo, File directory) {
    return Git.cloneRepository()
        .setURI(gitRepo)
        .setDirectory(directory)
        .setCloneAllBranches(true)
        .call();
  }

  public String getPath() {
    return file.getAbsolutePath();
  }

  public Git getGit() {
    return git;
  }

  @SneakyThrows
  public void delete() {
    MoreFiles.deleteRecursively(Paths.get(this.file.getAbsolutePath()),
        RecursiveDeleteOption.ALLOW_INSECURE);
  }
}
