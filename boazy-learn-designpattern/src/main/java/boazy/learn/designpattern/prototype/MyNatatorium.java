package boazy.learn.designpattern.prototype;

import java.io.Serializable;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class MyNatatorium implements Serializable {

    private String name;

    private String position;

    private String tel;

    private Integer size;

    private Integer depth;

    private Custom custom;

    public MyNatatorium() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}
