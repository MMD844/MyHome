public abstract class Device {
    protected String name;
    protected String protocol;
    protected String status;

    public Device(String name, String protocol) {
        this.name = name;
        this.protocol = protocol;
        this.status = "off";
    }

    public abstract boolean setProperty(String propertyName, String value);
    public abstract String getState();
}
