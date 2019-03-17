package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class GdsClient {

    public String queryLowPrice(String target, GdsDataSourceDelegator delegator) {
        return delegator.queryLowPrice(target);
    }

}
