package boazy.learn.designpattern.factory.factorymethod;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        HouseFactoryMethod houseFactoryMethod = new House79FactoryMethod();
        System.out.println("工厂方法.房子：" + houseFactoryMethod.createInstance().getHuserName());

        houseFactoryMethod = new House83FactoryMethod();
        System.out.println("工厂方法.房子：" + houseFactoryMethod.createInstance().getHuserName());
    }

}
