package boazy.learn.designpattern.proxy.staticproxy;

import boazy.learn.designpattern.proxy.ExpressBusiness;

/**
 * 静态代理
 * <p>
 * 实现与被代理类相同接口、代理类持有被代理对象、实现相同方法进行方法前后的代理逻辑
 *
 * @author boazy
 * @date 2019/3/12
 */
public class BeeBoxStaticProxy implements ExpressBusiness {
    private ExpressBusiness expressBusiness;

    public BeeBoxStaticProxy(ExpressBusiness expressBusiness) {
        this.expressBusiness = expressBusiness;
    }

    @Override
    public void sendExpress() {
        System.out.println("通知快递公司取快递，发送取快递码...");
        System.out.println("------------------------------");
        expressBusiness.sendExpress();
        System.out.println("------------------------------");
        System.out.println("验证取快递码，将快递给快递员邮寄...");
    }

}
