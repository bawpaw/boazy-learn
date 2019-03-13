package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class DelegateTargetB implements DelegateTarget {

    @Override
    public void handling(String target) {
        System.out.println("我是被委派者B，我来处理" + target);
    }

}
