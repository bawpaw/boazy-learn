package boazy.learn.designpattern.prototype;

import java.io.Serializable;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class MyHouse implements Serializable {

    private String name;

    private String position;

    private String tel;

    private Integer totalFloorNumber;

    private MyTV myTV;

    private Custom custom;

    public MyHouse() {
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

    public Integer getTotalFloorNumber() {
        return totalFloorNumber;
    }

    public void setTotalFloorNumber(Integer totalFloorNumber) {
        this.totalFloorNumber = totalFloorNumber;
    }

    public MyTV getMyTV() {
        return myTV;
    }

    public void setMyTV(MyTV myTV) {
        this.myTV = myTV;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}
