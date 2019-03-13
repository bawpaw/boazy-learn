package boazy.learn.designpattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class Delegator implements DelegateTarget {

    private Map<String, DelegateTarget> targets = new HashMap<>();

    public Delegator() {
        targets.put("加密", new DelegateTargetA());
        targets.put("登录", new DelegateTargetB());
    }

    @Override
    public void handling(String target) {
        targets.get(target).handling(target);
    }

}
