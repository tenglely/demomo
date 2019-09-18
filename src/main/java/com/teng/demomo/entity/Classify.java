package com.teng.demomo.entity;

/**
 * 文章分类类
 * 编号          id
 * 文章编号      aid
 * 分类          type
 * 类型          doctype （文章标签，个人分类，文章类型（原创/转载/翻译））
 * 用户编号      uid
 */
public class Classify {
    private Integer id;

    private String aid;

    private String type;

    private String doctype;

    private Integer uid;

    public Classify() {
    }

    public Classify(Integer id, String aid, String type, String doctype, Integer uid) {
        this.id = id;
        this.aid = aid;
        this.type = type;
        this.doctype = doctype;
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id=" + id +
                ", aid='" + aid + '\'' +
                ", type='" + type + '\'' +
                ", doctype='" + doctype + '\'' +
                ", uid=" + uid +
                '}';
    }
}