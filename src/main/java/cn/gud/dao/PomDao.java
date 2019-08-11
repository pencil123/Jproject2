package cn.gud.dao;

import cn.gud.domain.Dependency;
import com.sun.corba.se.impl.orbutil.DenseIntMapImpl;

public interface PomDao {
    public boolean updatePom(Dependency dependency);
    public Dependency getPom(Dependency dependency);
}
