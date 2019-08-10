package cn.gud.controller;

import cn.gud.dao.ProjectDaoImpl;
import cn.gud.dao.TagDaoImpl;
import cn.gud.domain.Tag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 张鲁燕
 * @since 2019/8/8 15:36
 */
@Component
public class TagUtils {
  @Autowired
  private TagDaoImpl tagDaoImpl;
  @Autowired
  private ProjectDaoImpl projectDaoImpl;
  @Value("${parrentDir.path}")
  private String parrentDir;

  /**
   * 遍历工程的Tags 写入到数据库
   * @throws IOException
   * @throws GitAPIException
   */
  public void updateProjectsTags() throws IOException, GitAPIException {
    List<String> projectsName = new ArrayList<String>();
    projectsName = projectDaoImpl.selectNames();

    for (int i = 0; i < projectsName.size(); i++) {
      String projectDir = parrentDir + projectsName.get(i);
      updateProjectTags(new File(projectDir));
      }
  }

  /**
   * 遍历一个工程的Tags 写入到数据库
   * @param projectDir 工程的存储目录
   * @return
   * @throws IOException
   * @throws GitAPIException
   */
  public boolean updateProjectTags(File projectDir) throws IOException,GitAPIException {
    GitUtils git = new GitUtils(projectDir);
    List<Tag> tags =  git.getAllTags();

    for(int l = 0; l < tags.size();l++) {
      System.out.println(tags.get(l).getTagVersion());
      tagDaoImpl.InsertTagIfNotExist(tags.get(l));
    }
    return true;
  }


  /**
   * 指定工程创建tag,PS:将工程切换到master分支后，才会创建
   * @param projectName
   * @param tagName
   * @return
   * @throws IOException
   * @throws GitAPIException
   */
  public boolean createTag(String projectName,String tagName) throws IOException,GitAPIException {
    String projectDir = parrentDir + projectName;
    GitUtils git = new GitUtils(new File(projectDir));
    git.pullBranch("master");
    git.createTag(tagName);
    return true;
  }

}
