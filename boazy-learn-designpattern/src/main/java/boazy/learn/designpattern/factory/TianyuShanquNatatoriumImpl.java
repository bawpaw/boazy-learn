package boazy.learn.designpattern.factory;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TianyuShanquNatatoriumImpl implements INatatorium {
    @Override
    public String getNatatoriumName() {
        return "天誉花园三区游泳池";
    }

    @Override
    public String getNatatoriumPosition() {
        return "天誉花园三区81栋西侧";
    }

    @Override
    public String getNatatoriumTel() {
        return "82288118";
    }
}
