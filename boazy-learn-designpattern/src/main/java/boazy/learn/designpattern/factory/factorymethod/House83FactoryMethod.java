package boazy.learn.designpattern.factory.factorymethod;

import boazy.learn.designpattern.factory.House83Impl;
import boazy.learn.designpattern.factory.IHouse;

/**
 * 工厂方法模式（83栋房子工厂）
 *
 * @author boazy
 * @date 2018/8/5
 */
public class House83FactoryMethod implements HouseFactoryMethod {
    @Override
    public IHouse createInstance() {
        return new House83Impl();
    }
}
