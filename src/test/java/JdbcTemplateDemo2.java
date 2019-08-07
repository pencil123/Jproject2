import cn.gud.dao.JdbcUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 张鲁燕
 * @since 2019/8/7 16:36
 */
public class JdbcTemplateDemo2 {
  /*  public static void main(String[] args) {
    //1.获取 Spring 容器
    ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
    //2.根据 id 获取 bean 对象
    JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
    //3.执行操作
    jt.execute("insert into account(name,money)values('eee',500)");
  }*/

  public static void main(String[] args) {
    JdbcUtils ob = new JdbcUtils();
    ob.func();
  }
}
