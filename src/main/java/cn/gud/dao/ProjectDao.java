package cn.gud.dao;

import cn.gud.domain.Project;
import java.util.List;

public interface ProjectDao {
    public void updateProject(Project project);
    public List<String> selectNames();
}
