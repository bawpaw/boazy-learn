package boazy.learn.designpattern.prototype;

import java.io.Serializable;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TVMemory implements Serializable {
    private String name;
    private Integer size;

    public TVMemory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
