package cn.gud.controller;

import cn.gud.domain.Tag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.util.ChangeIdUtil;
import org.springframework.stereotype.Component;

/**
 * @author 张鲁燕
 * @since 2019/8/8 15:53
 */

public class GitUtils {
  private Git git;
  private Repository repository;
  private RevWalk walk;
  private String projectName;

  /**
   * 初始化工厂
   * @param parrentFolder
   * @throws IOException
   * @throws GitAPIException
   */
  GitUtils(File parrentFolder) throws IOException,GitAPIException {
    FileRepositoryBuilder builder = new FileRepositoryBuilder();
    try (Repository repository = builder
        .setGitDir(new File(parrentFolder.getAbsolutePath() + System.getProperty("file.separator") + ".git"))
        .readEnvironment()
        .findGitDir()
        .build()) {
      this.git = new Git(repository);
      this.walk = new RevWalk(repository);
      this.repository = repository;
      this.projectName = parrentFolder.getName();
    }
  }

  /**
   * 获取工程的tag的列表
   * @return
   * @throws GitAPIException
   * @throws IOException
   */
  public List<Tag> getAllTags() throws GitAPIException, IOException {
    List<Ref> call;
    List<Tag> tags = new LinkedList<Tag>();
    call = this.git.tagList().call();
    for(Ref ref : call) {
      Tag tag = new Tag();
      System.out.println(ref.getName());
      System.out.println(ref.getObjectId());
      System.out.println(ref.getObjectId().toObjectId());
      ObjectId objectId = ref.getObjectId();
      RevTag revTag = this.walk.lookupTag(objectId);
      System.out.println(revTag.getFullMessage() + revTag.getShortMessage() + revTag.getTagName() + revTag.toString());
      tag.setTagVersion(ref.getName().split("/")[2]);
      tag.setProjectName(this.projectName);
      tags.add(tag);
    }
    return tags;
  }


}
