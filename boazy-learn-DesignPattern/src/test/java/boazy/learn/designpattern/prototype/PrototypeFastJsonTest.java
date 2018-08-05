package boazy.learn.designpattern.prototype;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class PrototypeFastJsonTest {

    @Test
    public void testFastjson1() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子2");
        myHouse.setPosition("广东省广州市增城区2");
        myHouse.setTel("83388228");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU2");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory12");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory22");
        tvMemory2.setSize(4);

        String myHouseJsonStr = JSONObject.toJSONString(myHouse);
        MyHouse myHouseCopy = JSONObject.parseObject(myHouseJsonStr, MyHouse.class);

        ProtorypeTestHelper.assertObject(myHouse, myHouseCopy);
    }

    @Test
    public void testFastjson2() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子2");
        myHouse.setPosition("广东省广州市增城区2");
        myHouse.setTel("83388228");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU2");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory12");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory22");
        tvMemory2.setSize(4);
        Custom custom;
        myHouse.setCustom(custom = new Custom());
        custom.setName("custom for boazy");

        String myHouseJsonStr = JSONObject.toJSONString(myHouse);
        MyNatatorium myNatatorium = JSONObject.parseObject(myHouseJsonStr, MyNatatorium.class);

        ProtorypeTestHelper.assertObject(myHouse, myNatatorium);
    }

}
