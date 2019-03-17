package boazy.learn.designpattern.delegate.simple;

import boazy.learn.designpattern.delegate.simple.gdsport.GdsDataSourceDelegateTarget;
import boazy.learn.designpattern.delegate.simple.gdsport.GdsDataSourceIbeDelegateTarget;
import boazy.learn.designpattern.delegate.simple.gdsport.GdsDataSourceSabreDelegateTarget;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    public String queryLowPrice(String queryParameter) {

        /**
         * 这里采用随机数委派来处理请求
         * 委派模式注重内部，策略注重外部 两者区别之一
         */
        int r = new Random().nextInt(100) + 1;
        GdsDataSourceDelegateTarget delegateTarget;
        if (0 == r % 2) {
            // 这个请求让IBE处理最合适分派给IBE处理
            delegateTarget = dataSourceTargets.get("IBE");
        } else {
            // 这个请求让SABRE处理最合适分派给SABRE处理
            delegateTarget = dataSourceTargets.get("SABRE");
        }

        return delegateTarget.queryLowPrice(queryParameter);
    }


}
