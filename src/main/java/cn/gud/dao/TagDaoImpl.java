package cn.gud.dao;

import cn.gud.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 张鲁燕
 * @since 2019/8/8 13:23
 */
@Repository
public class TagDaoImpl implements TagDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void InsertTag(Tag tag) {
    String sql = "insert into project_tags_list(name,tag_name) values(?,?)";
    Object[ ] params = new Object [] {tag.getProjectName(),tag.getTagVersion()};
    jdbcTemplate.update(sql,params);
  }
}
