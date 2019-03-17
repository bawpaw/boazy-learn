package boazy.learn.designpattern.delegate.simple;

/**
 * @author boazy
 * @date 2019/3/13
 */
public class DelegatorTests {

    public static void main(String[] args) {
        /**
         * 客户端 不直接向 被 被委派者 发送指令（或调用）
         * 而是 客户端 向 委派者发送 指令（或调用 ），由 委派者 调用被委派者
         *
         * 客户端是     老板
         * 委派者是     经理
         * 被委派者是   职员
         *
         * 老板 分派任务给               经理
         * 经理 再根据任务类型分派给不同的  职员  处理
         *
         */

        new Client().handling("登录", new Delegator());
        new Client().handling("加密", new Delegator());
    }

}
