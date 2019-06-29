package boazy.learn.designpattern.proxy.dynamicproxy.cglib;

import boazy.learn.designpattern.proxy.ExpressMailer;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author boazy
 * @date 2019/4/27
 */
public class CglibExpressBusinessProxyTests {

    public static void main(String[] args) {
        //利用 cglib 的代理类可以将内存中的 class 文件写入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D://cglib_proxy_class/");

        ExpressMailer expressMailer = new CglibExpressBusinessProxy().getInstance(ExpressMailer.class);
        expressMailer.sendExpress();
    }

}
