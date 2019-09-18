package com.teng.demomo.entity;

/**
 * 文章内容类
 * 编号           id
 * 文章编号       aid  (a+date)
 * 用户编号       uid
 * 标题           title
 * 内容           content
 * 日期           date
 * 状态           state  (公开、私密)
 * 浏览次数       number
 */
public class BokeArticle {
    private Integer id;

    private String aid;

    private Integer uid;

    private String title;

    private String content;

    private String date;

    private String state;

    private Integer number;

    public BokeArticle() {
    }

    public BokeArticle(Integer id, String aid, Integer uid, String title, String content, String date, String state, Integer number) {
        this.id = id;
        this.aid = aid;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.date = date;
        this.state = state;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BokeArticle{" +
                "id=" + id +
                ", aid='" + aid + '\'' +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", number=" + number +
                '}';
    }
}