package boazy.learn.designpattern.factory.simplefactory;

import boazy.learn.designpattern.factory.House79Impl;
import boazy.learn.designpattern.factory.House82Impl;
import boazy.learn.designpattern.factory.House83Impl;
import boazy.learn.designpattern.factory.IHouse;

/**
 * 简单工厂（房子简单工厂）
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class HouseSimpleFactory {

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
