package boazy.learn.designpattern.delegate.simple;

/**
 * 被委派者
 *
 * @author boazy
 * @date 2019/3/13
 */
public class DelegateTargetA implements DelegateTarget {

    @Override
    public void handling(String target) {
        System.out.println("我是被委派者A，我来处理" + target);
    }

}
