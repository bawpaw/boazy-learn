package boazy.learn.designpattern.prototype;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class ProtorypeTestHelper {

    public static void assertObject(MyHouse s, MyNatatorium t) {
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myNatatorium：" + JSONObject.toJSONString(t));

        Assert.assertEquals(s.getName(), t.getName());
        Assert.assertEquals(s.getPosition(), t.getPosition());
        Assert.assertEquals(s.getTel(), t.getTel());
        Assert.assertEquals(s.getCustom().getName(), t.getCustom().getName());

        t.setName("xxx");
        t.setPosition("yyy");
        t.setTel("8888888");
        t.getCustom().setName("zzz");

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myNatatorium：" + JSONObject.toJSONString(t));

        Assert.assertNotEquals(s.getName(), t.getName());
        Assert.assertNotEquals(s.getPosition(), t.getPosition());
        Assert.assertNotEquals(s.getTel(), t.getTel());
        Assert.assertNotEquals(s.getCustom().getName(), t.getCustom().getName());
    }

    public static void assertObject(MyHouse s, MyHouse t) {
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myHouseCopy：" + JSONObject.toJSONString(t));

        Assert.assertNotNull(t);
        Assert.assertNotEquals(s, t);
        Assert.assertNotNull(t.getMyTV());
        Assert.assertNotEquals(s.getMyTV(), t.getMyTV());
        Assert.assertNotNull(t.getMyTV().getTvCpu());
        Assert.assertNotEquals(s.getMyTV().getTvCpu(), t.getMyTV().getTvMemories());
        Assert.assertNotNull(t.getMyTV().getTvMemories());
        Assert.assertNotEquals(s.getMyTV().getTvMemories(), t.getMyTV().getTvMemories());

        t.setName("xxx");
        t.setPosition("yyy");
        t.setTel("88888888");
        t.setTotalFloorNumber(0);
        t.getMyTV().setLength(2500);
        t.getMyTV().getTvCpu().setName("zzz");
        t.getMyTV().getTvMemories().get(0).setName("mmm");

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myHouseCopy：" + JSONObject.toJSONString(t));

        Assert.assertNotEquals(s.getName(), t.getName());
        Assert.assertNotEquals(s.getPosition(), t.getPosition());
        Assert.assertNotEquals(s.getTel(), t.getTel());
        Assert.assertNotEquals(s.getTotalFloorNumber(), t.getTotalFloorNumber());
        Assert.assertNotEquals(s.getMyTV().getLength(), t.getMyTV().getLength());
        Assert.assertNotEquals(s.getMyTV().getTvCpu().getName(), t.getMyTV().getTvCpu().getName());
        Assert.assertNotEquals(s.getMyTV().getTvMemories().get(0).getName(), t.getMyTV().getTvMemories().get(0).getName());
    }

}
