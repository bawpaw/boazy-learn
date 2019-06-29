package boazy.learn.designpattern.prototype.test;

import boazy.learn.designpattern.prototype.MyHouse;
import boazy.learn.designpattern.prototype.MyNatatorium;
import com.alibaba.fastjson.JSONObject;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class ProtorypeTestHelper {

    public static void assertObject(MyHouse s, MyNatatorium t) {
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myNatatorium：" + JSONObject.toJSONString(t));

        assert s.getName().equals(t.getName());
        assert s.getPosition().equals(t.getPosition());
        assert s.getTel().equals(t.getTel());
        assert s.getCustom().getName().equals(t.getCustom().getName());

        t.setName("xxx");
        t.setPosition("yyy");
        t.setTel("8888888");
        t.getCustom().setName("zzz");

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myNatatorium：" + JSONObject.toJSONString(t));

        assert !s.getName().equals(t.getName());
        assert !s.getPosition().equals(t.getPosition());
        assert !s.getTel().equals(t.getTel());
        assert !s.getCustom().getName().equals(t.getCustom().getName());
    }

    public static void assertObject(MyHouse s, MyHouse t) {
        System.out.println("myHouse：" + JSONObject.toJSONString(s));
        System.out.println("myHouseCopy：" + JSONObject.toJSONString(t));

        assert null != t;
        assert !s.equals(t);
        assert null != t.getMyTV();
        assert !s.getMyTV().equals(t.getMyTV());
        assert null != t.getMyTV().getTvCpu();
        assert !s.getMyTV().getTvCpu().equals(t.getMyTV().getTvCpu());
        assert null != t.getMyTV().getTvMemories();
        assert !s.getMyTV().getTvMemories().equals(t.getMyTV().getTvMemories());

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


        assert !s.getName().equals(t.getName());
        assert !s.getPosition().equals(t.getPosition());
        assert !s.getTel().equals(t.getTel());
        assert !s.getTotalFloorNumber().equals(t.getTotalFloorNumber());
        assert !s.getMyTV().getLength().equals(t.getMyTV().getLength());
        assert !s.getMyTV().getTvCpu().getName().equals(t.getMyTV().getTvCpu().getName());
        assert !s.getMyTV().getTvMemories().get(0).getName().equals(t.getMyTV().getTvMemories().get(0).getName());
        assert !s.getMyTV().equals(t.getMyTV());
    }

}
