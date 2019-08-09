package cn.gud.controller;

import cn.gud.dao.ProjectDaoImpl;
import cn.gud.domain.Project;
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

  @Autowired
  private ProjectDaoImpl projectDaoImpl;

  @Value("${parrentDir}")
  private String parrentDir;

  public  void main() {
    System.out.println(parrentDir);
  }
}
