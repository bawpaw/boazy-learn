package boazy.learn.designpattern.factory.abstractfactory;

import boazy.learn.designpattern.factory.INatatorium;
import boazy.learn.designpattern.factory.IPropertyServiceCentre;
import boazy.learn.designpattern.factory.TianyuShanquNatatoriumImpl;
import boazy.learn.designpattern.factory.TianyuShanquPropertyServiceCentreImpl;

/**
 * 抽象工厂模式（天誉花园三区）
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TianyuShanquFactory implements TianyuPlotAbstractFactory {

    @Override
    public IPropertyServiceCentre createPSC() {
        return new TianyuShanquPropertyServiceCentreImpl();
    }

    @Override
    public INatatorium createNatatorium() {
        return new TianyuShanquNatatoriumImpl();
    }

}
