package boazy.learn.designpattern.factory.abstractfactory;

import boazy.learn.designpattern.factory.INatatorium;
import boazy.learn.designpattern.factory.IPropertyServiceCentre;

/**
 * 抽象工厂模式（天誉花园）
 * <p>
 * 违背开闭原则，添加一个道口把有之前的实现都要修改代码实现新添加的
 * 提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类
 * <p>
 * 规定了所有可能被创建的产品集合，产品族中扩展新的产品困难，需要修改抽象工厂的接口；增加了系统的抽象性和理解难度
 *
 * @author boazy
 * @date 2018/8/5
 */
public interface TianyuPlotAbstractFactory {
    /**
     * 创建物业服务中心
     *
     * @return 物业服务中心
     */
    IPropertyServiceCentre createPSC();

    /**
     * 创建游泳池
     *
     * @return 游泳池
     */
    INatatorium createNatatorium();

}
