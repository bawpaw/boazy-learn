package boazy.learn.designpattern.factory.factorymethod;

import boazy.learn.designpattern.factory.IHouse;

/**
 * 工厂方法模式（房子工厂）
 *
 * 简单工厂的升级解决违背开闭原则问题
 * 定义一个创建对象接口，但由子类决定实例化的类是哪一个。工厂方法让类把实例化推迟到子类
 *
 * 类的个数容易过多，增加复杂度；增加了系统的抽象性和理解难度
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public interface HouseFactoryMethod {
    IHouse createInstance();
}
