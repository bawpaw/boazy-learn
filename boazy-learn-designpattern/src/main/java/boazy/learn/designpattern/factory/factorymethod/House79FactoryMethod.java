package boazy.learn.designpattern.factory.factorymethod;

import boazy.learn.designpattern.factory.House79Impl;
import boazy.learn.designpattern.factory.IHouse;

/**
 * 工厂方法模式（79栋房子工厂）
 *
 * @author boazy
 * @date 2018/8/5
 */
public class House79FactoryMethod implements HouseFactoryMethod {
    @Override
    public IHouse createInstance() {
        return new House79Impl();
    }
}
