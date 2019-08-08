package cn.gud.controller;

import cn.gud.dao.ProjectDaoImpl;
import cn.gud.dao.TagDaoImpl;
import cn.gud.domain.Tag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
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

  public void updateProjectTags() throws IOException, GitAPIException {
    String parrentDir = "C:\\Users\\pencil\\work\\git\\jsh-bak\\";
    List<String> projectsName = new ArrayList<String>();
    projectsName = projectDaoImpl.selectNames();
    for (int i = 0;i < projectsName.size();i++) {
      String projectDir =  parrentDir + projectsName.get(i);
      GitUtils git = new GitUtils(new File(projectDir));
      List<Tag> tags =  git.getAllTags();
      for(i = 0; i < tags.size();i++) {
        System.out.println(tags.get(i).getProjectName());
        System.out.println(tags.get(i).getTagVersion());
      }
    }
  }

}
