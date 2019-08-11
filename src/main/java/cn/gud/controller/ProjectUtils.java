package cn.gud.controller;

import cn.gud.dao.PomDaoImpl;
import cn.gud.dao.ProjectDaoImpl;
import cn.gud.domain.Dependency;
import cn.gud.domain.Project;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author 张鲁燕
 * @since 2019/8/9 16:56
 */
@Service
@PropertySource({"classpath:config.properties"})
public class ProjectUtils {
  private String projectName;
  @Autowired
  private Project project;
  @Autowired
  private ProjectDaoImpl projectDaoImpl;
  @Autowired
  private Dependency dependency;
  private GitUtils gitUtils;

  @Autowired
  private PomDaoImpl pomDaoImpl;

  @Value("${parrentDir.path}")
  private String parrentDir;

  private File fullDir;
  /**
   * 设置操作的工厂的名，初始函数的作用
   * @param projectNameParam
   */
  public void setProject(String projectNameParam) {
    projectName = projectNameParam;
    try {
      this.gitUtils = new GitUtils(new File(parrentDir + projectName));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
  }

  /**
   * 将工程代码切换到指定的分支
   * @param branchName
   * @return
   */
  public boolean checkoutBranch(String branchName) {
    dependency.setEnv(branchName);
    this.gitUtils.checkoutBranch(branchName);
    return true;
  }

  public boolean pullBranch(String branchName) {
   dependency.setEnv(branchName);
   this.gitUtils.pullBranch(branchName);
   return true;
  }

  public boolean createTag() {
   return true;
  }

  public boolean getPom() {
    File pomFile = new File(parrentDir + this.projectName + System.getProperty("file.separator") + "pom.xml");
    PomUtils pomUtils = new PomUtils(pomFile);
    dependency.setProjectVersion(pomUtils.getVersion());
    dependency.setDependencies(pomUtils.getDependency());
    dependency.setProjectName(projectName);
    pomDaoImpl.updatePom(dependency);
    return true;
  }

}
