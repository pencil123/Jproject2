package cn.gud.domain;

import org.springframework.stereotype.Component;

@Component
public class Project {
    private int id;
    private String name;
    private boolean isParrent;
    private boolean isChild;
    private boolean isMaven;
    private String tag;
    private String master;
    private String master_follow;
    private String dev;
    private String newtag;
    private int newtag_type;
    private boolean skip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParrent() {
        return isParrent;
    }

    public void setParrent(boolean parrent) {
        isParrent = parrent;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public boolean isMaven() {
        return isMaven;
    }

    public void setMaven(boolean maven) {
        isMaven = maven;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getMaster_follow() {
        return master_follow;
    }

    public void setMaster_follow(String master_follow) {
        this.master_follow = master_follow;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getNewtag() {
        return newtag;
    }

    public void setNewtag(String newtag) {
        this.newtag = newtag;
    }

    public int getNewtag_type() {
        return newtag_type;
    }

    public void setNewtag_type(int newtag_type) {
        this.newtag_type = newtag_type;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
