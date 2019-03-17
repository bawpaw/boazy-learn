package boazy.learn.designpattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * GDS数据源 委派者
 *
 * @author boazy
 * @date 2019/3/13
 */
public class GdsDataSourceDelegator implements GdsDataSourceDelegateTarget {

    private Map<String, GdsDataSourceDelegateTarget> dataSourceTargets = new HashMap<>();

    public GdsDataSourceDelegator() {
        dataSourceTargets.put("IBE", new GdsDataSourceIbeDelegateTarget());
        dataSourceTargets.put("SABRE", new GdsDataSourceSabreDelegateTarget());
    }

    @Override
    public String queryLowPrice(String target) {
        return dataSourceTargets.get(target).queryLowPrice(target);
    }


}
