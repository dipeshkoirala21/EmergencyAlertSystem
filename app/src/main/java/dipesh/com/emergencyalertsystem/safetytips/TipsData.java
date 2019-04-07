package dipesh.com.emergencyalertsystem.safetytips;

public class TipsData {
    private String TipsName;
    private String TipsDesc;

    public TipsData(String name, String desc) {
        this.TipsName = name;
        this.TipsDesc = desc;
    }

    String getTipsName() {
        return TipsName;
    }

    String getTipsDesc() {
        return TipsDesc;
    }
}
