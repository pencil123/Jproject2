package cn.gud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 张鲁燕
 * @since 2019/8/7 16:18
 */
public class JdbcUtils {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void func() {
    jdbcTemplate.execute("INSERT INTO tb_name (name, value) VALUES ('Lucas', '26')");
  }
}
