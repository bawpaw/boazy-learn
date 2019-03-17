package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class DelegateTests {

    public static void main(String[] args) {
        // 采用IBE查低价
        new GdsClient().queryLowPrice("IBE", new GdsDataSourceDelegator());
        // 采用Sabre查低价
        new GdsClient().queryLowPrice("SABRE", new GdsDataSourceDelegator());
    }

}
