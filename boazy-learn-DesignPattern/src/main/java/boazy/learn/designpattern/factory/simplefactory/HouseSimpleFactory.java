package boazy.learn.designpattern.factory.simplefactory;

import boazy.learn.designpattern.factory.House79Impl;
import boazy.learn.designpattern.factory.House82Impl;
import boazy.learn.designpattern.factory.House83Impl;
import boazy.learn.designpattern.factory.IHouse;

/**
 * 简单工厂（房子简单工厂）
 *
 * 违背开闭原则，每添加一个产品（一栋房子）都会导致修改工厂类
 * 工厂类的职责相对过重，不易于扩展过于复杂的产品结构
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class HouseSimpleFactory {

    /**
     * 传入参数还可以是 className 或 class，这个创建方法不用作if判断直接通过反射创建实现
     *
     * @param type 类型
     * @return type的IHouse实例
     */
    public static IHouse createInstance(String type) {
        if("79".equalsIgnoreCase(type)) {
            return new House79Impl();
        } else if("82".equalsIgnoreCase(type)) {
            return new House82Impl();
        } else if("83".equalsIgnoreCase(type)) {
            return new House83Impl();
        } else {
            throw new RuntimeException("type invalid.");
        }
    }

}
