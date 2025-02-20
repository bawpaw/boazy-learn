package boazy.learn.designpattern.factory;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class TianyuWuquPropertyServiceCentreImpl implements IPropertyServiceCentre {

    @Override
    public String getPscName() {
        return "天誉花园五区物业服务中心";
    }

    @Override
    public String getPscPosition() {
        return "天誉花园五区10栋楼下首层";
    }

    @Override
    public String getPscTel() {
        return "87788668";
    }
}
