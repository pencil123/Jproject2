import cn.gud.controller.ProjectUtils;
import cn.gud.controller.TagUtils;
import cn.gud.dao.JdbcUtils;
import cn.gud.dao.ProjectDaoImpl;
import cn.gud.domain.Tag;
import java.io.IOException;
import java.util.List;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 张鲁燕
 * @since 2019/8/7 16:36
 */
public class JdbcTemplateDemo2 {

  /*
  =========================================================================================================
  public static void main(String[] args) {
      // 1.获取 Spring 容器
      ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
      // 2.根据 id 获取 bean 对象
      JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
      // 3.执行操作
      jt.execute("insert into account(name,money)values('eee',500)");
    }*/

  /*
  =====================================================================================================

  @Test
  public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
      ProjectDaoImpl ob = ac.getBean(ProjectDaoImpl.class);
    List<String> names = ob.selectNames();
    for (int i = 0; i < names.size();i++ ) {
      System.out.println(names.get(i));
      }
    }*/

  /*
  ===================================================================================
  @Test
    public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
      TagUtils ob = ac.getBean(TagUtils.class);
      ob.updateProjectTags();
    }*/

  /*  @Test
  public static void main(String[] args) throws IOException, GitAPIException {
    ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
    TagUtils ob = ac.getBean(TagUtils.class);
    ob.updateProjectsTags();
  }*/

  @Test
  public static void main(String[] args) throws IOException, GitAPIException {
    ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
    ProjectUtils ob = ac.getBean(ProjectUtils.class);
    ob.main();
    }
}
