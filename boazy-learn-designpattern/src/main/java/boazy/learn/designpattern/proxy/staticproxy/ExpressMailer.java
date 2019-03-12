package boazy.learn.designpattern.proxy.staticproxy;

/**
 * @author boazy
 * @company boazy
 * @date 2019/3/12
 */
public class ExpressMailer implements ExpressBusiness {

    @Override
    public void sendExpress() {
        System.out.println("地址：广州市天河区建中路");
        System.out.println("邮编：51000");
        System.out.println("收件人：李先生");
        System.out.println("收件人电话：18680228022");
    }

}
