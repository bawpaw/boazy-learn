package boazy.learn.designpattern.prototype;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class PrototypeSerializableTest {

    @Test
    public void testSerializable1() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子");
        myHouse.setPosition("广东省广州市增城区");
        myHouse.setTel("82288118");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory1");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory2");
        tvMemory2.setSize(4);

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        MyHouse myHouseCopy = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(myHouse);
            byte[] bytes = bos.toByteArray();

            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);

            myHouseCopy = (MyHouse) ois.readObject();   //克隆好的对象！
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if(null != bos) {
                try {
                    bos.close();
                } catch (Throwable t) {
                }
            }
            if(null != oos) {
                try {
                    oos.close();
                } catch (Throwable t) {
                }
            }
            if(null != bis) {
                try {
                    bis.close();
                } catch (Throwable t) {
                }
            }
            if(null != ois) {
                try {
                    ois.close();
                } catch (Throwable t) {
                }
            }
        }

        ProtorypeTestHelper.assertObject(myHouse, myHouseCopy);
    }

    @Test
    public void testSerializable2() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子");
        myHouse.setPosition("广东省广州市增城区");
        myHouse.setTel("82288118");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory1");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory2");
        tvMemory2.setSize(4);

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        MyNatatorium myNatatorium = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(myHouse);
            byte[] bytes = bos.toByteArray();

            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);

            /**
             * TODO
             * 序列化不可以复制不同class对象，会抛异常的（java.lang.ClassCastException）
             */
            myNatatorium = (MyNatatorium) ois.readObject();   //克隆好的对象！
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if(null != bos) {
                try {
                    bos.close();
                } catch (Throwable t) {
                }
            }
            if(null != oos) {
                try {
                    oos.close();
                } catch (Throwable t) {
                }
            }
            if(null != bis) {
                try {
                    bis.close();
                } catch (Throwable t) {
                }
            }
            if(null != ois) {
                try {
                    ois.close();
                } catch (Throwable t) {
                }
            }
        }

        ProtorypeTestHelper.assertObject(myHouse, myNatatorium);
    }

}
