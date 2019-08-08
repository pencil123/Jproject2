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

  /**
   * 将tag信息写入到数据库中
   * @param tag
   */
  public void InsertTagIfNotExist(Tag tag) {
    if (! TagIfExist(tag)) {
      String sql = "insert into project_tags_list(name,tag_name) values(?,?)";
      Object[ ] params = new Object [] {tag.getProjectName(),tag.getTagVersion()};
      jdbcTemplate.update(sql,params);
    }
  }

  /**
   * 判断tag信息是否已经存在
   * @param tag
   * @return
   */
  public boolean TagIfExist(Tag tag) {
    String sql = "select count(1) from project_tags_list where name=? and tag_name = ?";
    Object[ ] params = new Object [] {tag.getProjectName(),tag.getTagVersion()};
    int count =  jdbcTemplate.queryForObject(sql,Integer.class,params);
    if ( count == 0)    return false;
    else   return true;
  }
}
