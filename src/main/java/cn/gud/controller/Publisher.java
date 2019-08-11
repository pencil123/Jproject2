package cn.gud.controller;

import cn.gud.dao.ProjectDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张鲁燕
 * @since 2019/8/9 17:04
 */
@Component
public class Publisher {

    @Autowired
    private ProjectDaoImpl projectDaoImpl;
    @Autowired
    private ProjectUtils projectUtils;

    public  void main() {
        List<String> projectName = new ArrayList<String>();
        projectName = projectDaoImpl.selectMavenNames();
        for(String name : projectName) {
            System.out.println(name);
            projectUtils.setProject(name);
            projectUtils.pullBranch("dev");
            projectUtils.getPom();
        }
    }
}
