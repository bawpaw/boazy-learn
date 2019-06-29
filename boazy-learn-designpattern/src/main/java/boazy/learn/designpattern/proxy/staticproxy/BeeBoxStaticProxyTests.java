package boazy.learn.designpattern.proxy.staticproxy;

import boazy.learn.designpattern.proxy.ExpressMailer;

/**
 * @author boazy
 * @date 2019/3/12
 */
public class BeeBoxStaticProxyTests {

    public static void main(String[] args) {
        new BeeBoxStaticProxy(new ExpressMailer()).sendExpress();
    }

}
