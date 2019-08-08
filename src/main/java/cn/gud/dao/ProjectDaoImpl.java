package cn.gud.dao;

import cn.gud.domain.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 更新工程信息
     * @param project
     */
    public void updateProject(Project project) {
        int i =3;
        i++;
    }

    /**
     * 查询所有工程的名称
     * @return List  Type   工程名
     */
    public List<String> selectNames() {
        String sql = "select name from publish_projects_list";
        final List<String> names = new ArrayList<String>();
        jdbcTemplate.query(sql,new Object[ ]{},new RowCallbackHandler(){
            public void processRow(ResultSet rs) throws SQLException {
                String name ;
                name = rs.getString("name");
                names.add(name);
            }
        });
        return names;
    }
}
