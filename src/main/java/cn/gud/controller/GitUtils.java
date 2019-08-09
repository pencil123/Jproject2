package cn.gud.controller;

import cn.gud.domain.Tag;
import cn.gud.utils.Timestamp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
   * 初始化工程
   * @param parrentFolder
   * @throws IOException
   * @throws GitAPIException
   */
  GitUtils(File parrentFolder) throws IOException,GitAPIException {
    FileRepositoryBuilder builder = new FileRepositoryBuilder();
    System.out.println(parrentFolder.getName());
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
   * 获取当前工程目前所在分支
   * @return branch 名称
   */
  public String getCurrentBranch() throws IOException{
    String currentBranch = this.repository.getBranch();
    return currentBranch;
  }

  /**
   * 将工程的工作目录强制回滚到Head
   * @return
   */
  public boolean workDirResetToHead() {
    try {
      this.git.reset().setMode(ResetCommand.ResetType.HARD).call();
    } catch (GitAPIException e) {
      return false;
    }
    return true;
  }

  public boolean createBranchIfNotExist(String branchName){
    try {
      List<Ref> call = this.git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
      System.out.println(call.size());
      for (Ref ref : call) {
        System.out.println(ref.getName());
      }
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
  return true;
  }

  /**
   * 将工程切换到指定分支
   * @param branchName
   * @return
   */
  public boolean checkoutBranch(String branchName) throws IOException,GitAPIException{
    if (branchName.equals(getCurrentBranch())) {return true;}
    if (!workDirResetToHead()) {return false;}
    this.git.checkout().setName(branchName).call();
    return true;
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
      ObjectId objectId = ref.getObjectId().toObjectId();
      RevCommit revCommit = this.walk.parseCommit(objectId);

      System.out.println(revCommit.getCommitTime());
      //设置tag的属性，并添加到列表中
      tag.setTagVersion(ref.getName().split("/")[2]);
      tag.setProjectName(this.projectName);
      tag.setDate(Timestamp.stampToDate(revCommit.getCommitTime()));
      tags.add(tag);
    }
    return tags;
  }


}
