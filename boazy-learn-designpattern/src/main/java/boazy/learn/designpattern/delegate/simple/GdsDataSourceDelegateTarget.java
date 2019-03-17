package boazy.learn.designpattern.delegate.simple;

/**
 * 被委派者接口
 *
 * @author boazy
 * @date 2019/3/13
 */
public interface GdsDataSourceDelegateTarget {

    String queryLowPrice(String target);

}
