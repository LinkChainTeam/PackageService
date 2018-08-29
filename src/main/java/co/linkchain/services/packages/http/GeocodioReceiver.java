package co.linkchain.services.packages.http;

public class GeocodioReceiver {
    private String formattedAddress;
    private double accuracy;
    private String accuracyType;
    private String source;

    private GeocodioReceiverComponents addressComponents;
    private GeocodioReceiverPosition location;

    public String getFormatted_address() {
        return formattedAddress;
    }

    public void setFormatted_address(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getAccuracy_type() {
        return accuracyType;
    }

    public void setAccuracy_type(String accuracyType) {
        this.accuracyType = accuracyType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public GeocodioReceiverComponents getAddress_components() {
        return addressComponents;
    }

    public void setAddress_components(GeocodioReceiverComponents addressComponents) {
        this.addressComponents = addressComponents;
    }

    public GeocodioReceiverPosition getLocation() {
        return location;
    }

    public void setLocation(GeocodioReceiverPosition location) {
        this.location = location;
    }
}
