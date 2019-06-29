package boazy.learn.designpattern.factory.simplefactory;

import boazy.learn.designpattern.factory.IHouse;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class SimpleFactoryTests {

    public static void main(String[] args) {
        IHouse house = HouseSimpleFactory.createInstance("82");
        System.out.println("简单工厂.房子：" + house.getHuserName());

        house = HouseSimpleFactory.createInstance("79");
        System.out.println("简单工厂.房子：" + house.getHuserName());
    }

}
