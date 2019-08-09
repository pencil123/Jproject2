package cn.gud.controller;

import cn.gud.dao.ProjectDaoImpl;
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
@PropertySource({"classpath:config.properties"})
public class ProjectUtils {
  private Project project;
  @Autowired
  private ProjectDaoImpl projectDaoImpl;
  @Value("${parrentDir}")
  private String parrentDir;
  private GitUtils gitUtils;

  ProjectUtils(String projectName) {
    project.setName(projectName);
    try {
      this.gitUtils = new GitUtils(new File(parrentDir + projectName));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
  }

  public boolean checkoutBranch(String branchName) {
    try {
      this.gitUtils.checkoutBranch(branchName);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return true;

  }
  public  void main() {
    System.out.println(parrentDir);
  }
}
