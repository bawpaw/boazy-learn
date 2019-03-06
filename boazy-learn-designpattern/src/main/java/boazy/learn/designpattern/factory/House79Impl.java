package boazy.learn.designpattern.factory;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class House79Impl implements IHouse {
    @Override
    public String getHuserName() {
        return "天誉花园三区79栋";
    }

    @Override
    public Integer getFloorNumber() {
        return 33;
    }

    @Override
    public Integer getFloorHouseholdNumber() {
        return 7;
    }

    @Override
    public Integer getTotalHouseholdNumber() {
        return (getFloorNumber() - 1) * getFloorHouseholdNumber();
    }
}
