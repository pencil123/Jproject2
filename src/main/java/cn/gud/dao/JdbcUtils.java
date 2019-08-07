package cn.gud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;

/**
 * @author 张鲁燕
 * @since 2019/8/7 16:18
 */
public class JdbcUtils {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  private ResultSet rs;
  private Object ResultSet;

  public void func() {
    jdbcTemplate.execute("INSERT INTO tb_name (name, value) VALUES ('Lucas', '26')");
  }
  
  public boolean executeSql(String stringSQL) {
    jdbcTemplate.execute(stringSQL);
    return true;
  }
  
  public ResultSet executeQuery(String stringSQL) {
   // jdbcTemplate.query(stringSQL,(ResultSet rs));
    return rs;
  }
}
