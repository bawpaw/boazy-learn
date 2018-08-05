package boazy.learn.designpattern.factory;

import boazy.learn.designpattern.factory.factorymethod.House79FactoryMethod;
import boazy.learn.designpattern.factory.factorymethod.House83FactoryMethod;
import boazy.learn.designpattern.factory.factorymethod.HouseFactoryMethod;
import org.junit.Test;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class FactoryMethodTest {

    @Test
    public void testFactoryMethod() {
        HouseFactoryMethod houseFactoryMethod = new House79FactoryMethod();
        System.out.println("工厂方法.房子：" + houseFactoryMethod.createInstance().getHuserName());

        houseFactoryMethod = new House83FactoryMethod();
        System.out.println("工厂方法.房子：" + houseFactoryMethod.createInstance().getHuserName());
    }

}
