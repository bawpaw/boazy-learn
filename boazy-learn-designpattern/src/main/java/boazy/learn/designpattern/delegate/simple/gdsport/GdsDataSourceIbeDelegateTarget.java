package boazy.learn.designpattern.delegate.simple.gdsport;

/**
 * 被委派者
 *
 * @author boazy
 * @date 2019/3/13
 */
public class GdsDataSourceIbeDelegateTarget implements GdsDataSourceDelegateTarget {

    @Override
    public String queryLowPrice(String queryParameter) {
        System.out.println("我是被委派者（IBE GDS数据源），我来处理这个查询：" + queryParameter);
        return "{\"dataSouce\":\"IBE\"}";
    }

}
