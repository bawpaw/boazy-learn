package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class DelegatorTests {

    public static void main(String[] args) {
        new Client().handling("登录", new Delegator());
    }

}
