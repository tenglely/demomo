package com.teng.demomo.entity;

/**
 * 文章浏览表
 * 编号          id
 * 用户编号      uid
 * 文章编号      aid
 * 浏览状态      state   （1,0 表示已浏览与未浏览）
 * 点赞状态      g_state  (1,0 表示已点赞与未点赞)
 */
public class Browse {
    private Integer id;

    private Integer uid;

    private String aid;

    private Integer state;

    private Integer gState;

    private Integer cState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getgState() {
        return gState;
    }

    public void setgState(Integer gState) {
        this.gState = gState;
    }

    public Integer getcState() {
        return cState;
    }

    public void setcState(Integer cState) {
        this.cState = cState;
    }
}