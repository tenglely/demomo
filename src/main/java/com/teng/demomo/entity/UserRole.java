package com.teng.demomo.entity;

/**
 * 用户角色关联表
 * 编号           id
 * 用户编号       uid
 * 类型编号       rid
 */
public class UserRole {
    private int id;
    private int uid;
    private int rid;

    public UserRole() {
    }

    public UserRole(int id, int uid, int rid) {
        this.id = id;
        this.uid = uid;
        this.rid = rid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
