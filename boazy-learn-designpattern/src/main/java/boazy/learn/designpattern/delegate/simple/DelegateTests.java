package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class DelegateTests {

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new GdsClient().queryLowPrice("查询条件" +(i + 1), new GdsDataSourceDelegator());
        }
    }

}
