package boazy.learn.designpattern.factory;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TianyuWuquNatatoriumImpl implements INatatorium {
    @Override
    public String getNatatoriumName() {
        return "天誉花园五区游泳池";
    }

    @Override
    public String getNatatoriumPosition() {
        return "天誉花园五区5栋南侧";
    }

    @Override
    public String getNatatoriumTel() {
        return "83388228";
    }
}
