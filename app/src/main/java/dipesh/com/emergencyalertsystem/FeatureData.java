package dipesh.com.emergencyalertsystem;

class FeatureData {
    private String featureName;
    private int featureImage;

    FeatureData(String name, int image) {
        this.featureName = name;
        this.featureImage = image;
    }

    String getFeatureName() {
        return featureName;
    }

    int getFeatureImage() {
        return featureImage;
    }
}
