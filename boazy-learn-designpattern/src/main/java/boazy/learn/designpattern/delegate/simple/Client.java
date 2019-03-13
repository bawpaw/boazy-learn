package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class Client {

    public void handling(String target, Delegator delegator) {
        delegator.handling(target);
    }

}
