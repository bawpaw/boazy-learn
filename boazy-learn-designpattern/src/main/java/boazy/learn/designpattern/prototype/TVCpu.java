package boazy.learn.designpattern.prototype;

import java.io.Serializable;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class TVCpu implements Serializable {
    private String name;
    private Integer kernelNumber;

    public TVCpu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKernelNumber() {
        return kernelNumber;
    }

    public void setKernelNumber(Integer kernelNumber) {
        this.kernelNumber = kernelNumber;
    }
}
