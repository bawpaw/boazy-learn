package boazy.learn.designpattern.delegate.simple;

/**
 * 被委派者
 *
 * @author boazy
 * @date 2019/3/13
 */
public class GdsDataSourceIbeDelegateTarget implements GdsDataSourceDelegateTarget {

    @Override
    public String queryLowPrice(String target) {
        System.out.println("我是被委派者（IBE GDS数据源），我来处理" + target);
        return "{\"dataSouce\":\"IBE\"}";
    }

}
