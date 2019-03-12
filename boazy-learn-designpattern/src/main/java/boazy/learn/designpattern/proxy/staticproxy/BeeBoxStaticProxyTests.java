package boazy.learn.designpattern.proxy.staticproxy;

/**
 * @author boazy
 * @company boazy
 * @date 2019/3/12
 */
public class BeeBoxStaticProxyTests {

    public static void main(String[] args) {
        new BeeBoxStaticProxy(new ExpressMailer()).sendExpress();
    }

}
