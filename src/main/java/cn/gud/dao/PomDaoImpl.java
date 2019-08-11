package cn.gud.dao;

import cn.gud.domain.Dependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PomDaoImpl implements PomDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean updatePom(Dependency dependency) {
        String updateVersion = "update publish_projects_list set " + dependency.getEnv() + " = ? where name = ?";
        System.out.println(updateVersion);
        Object[ ] updateVersionParams = new Object[] {dependency.getProjectVersion(),dependency.getProjectName()};
        System.out.println(dependency.getEnv()+ dependency.getProjectName() + dependency.getProjectVersion());
        jdbcTemplate.update(updateVersion,updateVersionParams);

        Object[] deleteDenParams = new Object[] {dependency.getProjectName(),dependency.getEnv()};
        jdbcTemplate.update("delete from dependencies where projectname=? and env = ?",deleteDenParams);

        List<Object[]> batch = new ArrayList<Object[]>();
        for (Map.Entry<String,String> entry : dependency.getDependencies().entrySet()) {
            Object[] values = new Object[] {dependency.getProjectName(),dependency.getEnv(),entry.getKey(),entry.getValue()};
            batch.add(values);
        }
        String updateDependencies = "insert into dependencies (projectname,env,depen_project,depen_version) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(updateDependencies,batch);
        return true;
    }

    @Override
    public Dependency getPom(Dependency dependency) {
        return null;
    }
}
