package boazy.learn.designpattern.singleton.registered;

/**
 * 枚举单例
 *
 * @author boazy
 * @date 2019/3/9
 */
public enum EnumSingleton {

    /**
     *
     */
    INSTANCE(new Object());

    private Object data;

    EnumSingleton(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

}
