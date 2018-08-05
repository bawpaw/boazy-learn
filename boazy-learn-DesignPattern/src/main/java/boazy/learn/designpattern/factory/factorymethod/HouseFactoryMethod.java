package boazy.learn.designpattern.factory.factorymethod;

import boazy.learn.designpattern.factory.IHouse;

/**
 * 工厂方法模式（房子工厂）
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public interface HouseFactoryMethod {
    IHouse createInstance();
}
