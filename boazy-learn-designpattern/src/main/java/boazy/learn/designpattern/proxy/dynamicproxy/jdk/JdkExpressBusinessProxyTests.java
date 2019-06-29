package boazy.learn.designpattern.proxy.dynamicproxy.jdk;

import boazy.learn.designpattern.proxy.ExpressBusiness;
import boazy.learn.designpattern.proxy.ExpressMailer;

/**
 * @author boazy
 * @date 2019/4/27
 */
public class JdkExpressBusinessProxyTests {

    public static void main(String[] args) {
        ExpressBusiness expressMailer = new JdkExpressBusinessProxy().getInstance(new ExpressMailer());
        expressMailer.sendExpress();
    }

}
