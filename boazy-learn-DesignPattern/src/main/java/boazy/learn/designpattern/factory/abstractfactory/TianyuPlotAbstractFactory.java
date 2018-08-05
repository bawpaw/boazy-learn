package boazy.learn.designpattern.factory.abstractfactory;

import boazy.learn.designpattern.factory.INatatorium;
import boazy.learn.designpattern.factory.IPropertyServiceCentre;

/**
 * 抽象工厂模式（天誉花园）
 *
 * @author boazy
 * @company boazy
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
