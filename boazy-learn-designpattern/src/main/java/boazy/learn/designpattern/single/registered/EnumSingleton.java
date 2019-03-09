package boazy.learn.designpattern.single.registered;

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

    public static final EnumSingleton getInstance() {
        return INSTANCE;
    }

}
