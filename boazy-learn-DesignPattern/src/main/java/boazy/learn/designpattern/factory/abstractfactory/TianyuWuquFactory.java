package boazy.learn.designpattern.factory.abstractfactory;

import boazy.learn.designpattern.factory.INatatorium;
import boazy.learn.designpattern.factory.IPropertyServiceCentre;
import boazy.learn.designpattern.factory.TianyuWuquNatatoriumImpl;
import boazy.learn.designpattern.factory.TianyuWuquPropertyServiceCentreImpl;

/**
 * 抽象工厂模式（天誉花园五区）
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TianyuWuquFactory implements TianyuPlotAbstractFactory {

    @Override
    public IPropertyServiceCentre createPSC() {
        return new TianyuWuquPropertyServiceCentreImpl();
    }

    @Override
    public INatatorium createNatatorium() {
        return new TianyuWuquNatatoriumImpl();
    }

}
