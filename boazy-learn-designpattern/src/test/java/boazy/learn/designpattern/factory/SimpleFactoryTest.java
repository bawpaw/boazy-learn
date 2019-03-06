package boazy.learn.designpattern.factory;

import boazy.learn.designpattern.factory.simplefactory.HouseSimpleFactory;
import org.junit.Test;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class SimpleFactoryTest {

    @Test
    public void testSimpleFactory() {
        IHouse house = HouseSimpleFactory.createInstance("82");
        System.out.println("简单工厂.房子：" + house.getHuserName());

        house = HouseSimpleFactory.createInstance("79");
        System.out.println("简单工厂.房子：" + house.getHuserName());
    }

}
