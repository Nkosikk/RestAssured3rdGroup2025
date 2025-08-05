package Utils;

public class Station {

    public String externalId;
    public String name;
    public double latitude;
    public double longitude;
    public int altitude;

    public Station(String externalId, String name, double latitude, double longitude, int altitude) {
        this.externalId = externalId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

}
