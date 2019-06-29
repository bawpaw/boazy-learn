package boazy.learn.designpattern.factory.abstractfactory;

/**
 * @author boazy
 * @date 2018/8/5
 */
public class AbstractFactoryTests {

    public static void main(String[] args) {
        TianyuPlotAbstractFactory tianyuPlotAbstractFactory = new TianyuShanquFactory();
        System.out.println("抽象工厂.物业服务中心：" + tianyuPlotAbstractFactory.createPSC().getPscName());
        System.out.println("抽象工厂.游泳池：" + tianyuPlotAbstractFactory.createNatatorium().getNatatoriumName());

        tianyuPlotAbstractFactory = new TianyuWuquFactory();
        System.out.println("抽象工厂.物业服务中心：" + tianyuPlotAbstractFactory.createPSC().getPscName());
        System.out.println("抽象工厂.游泳池：" + tianyuPlotAbstractFactory.createNatatorium().getNatatoriumName());
    }

}
