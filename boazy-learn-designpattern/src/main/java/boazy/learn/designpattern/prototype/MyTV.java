package boazy.learn.designpattern.prototype;

import java.io.Serializable;
import java.util.List;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class MyTV implements Serializable {
    private Integer length;
    private Integer width;
    private Integer high;
    private TVCpu tvCpu;
    private List<TVMemory> tvMemories;

    public MyTV() {
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public TVCpu getTvCpu() {
        return tvCpu;
    }

    public void setTvCpu(TVCpu tvCpu) {
        this.tvCpu = tvCpu;
    }

    public List<TVMemory> getTvMemories() {
        return tvMemories;
    }

    public void setTvMemories(List<TVMemory> tvMemories) {
        this.tvMemories = tvMemories;
    }
}
