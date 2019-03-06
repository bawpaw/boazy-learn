package boazy.learn.designpattern.factory;

import boazy.learn.designpattern.factory.abstractfactory.TianyuPlotAbstractFactory;
import boazy.learn.designpattern.factory.abstractfactory.TianyuShanquFactory;
import boazy.learn.designpattern.factory.abstractfactory.TianyuWuquFactory;
import org.junit.Test;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class AbstractFactoryTest {

    @Test
    public void testAbstractFactory() {
        TianyuPlotAbstractFactory tianyuPlotAbstractFactory = new TianyuShanquFactory();
        System.out.println("抽象工厂.物业服务中心：" + tianyuPlotAbstractFactory.createPSC().getPscName());
        System.out.println("抽象工厂.游泳池：" + tianyuPlotAbstractFactory.createNatatorium().getNatatoriumName());

        tianyuPlotAbstractFactory = new TianyuWuquFactory();
        System.out.println("抽象工厂.物业服务中心：" + tianyuPlotAbstractFactory.createPSC().getPscName());
        System.out.println("抽象工厂.游泳池：" + tianyuPlotAbstractFactory.createNatatorium().getNatatoriumName());
    }

}
