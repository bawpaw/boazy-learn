package boazy.learn.designpattern.prototype;

import java.io.Serializable;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class Custom implements Serializable {
    private String name;

    public Custom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
