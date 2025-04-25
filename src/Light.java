public class Light extends Device{

    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    @Override
    public boolean setProperty(String propertyName, String value) {
        if (propertyName.equals("status")) {
            if (value.equals("on") || value.equals("off")) {
                this.status = value;
                return true;
            }
            return false;
        }
        else if (propertyName.equals("brightness")) {
            try {
                int brightness = Integer.parseInt(value);
                if (brightness >= 0 && brightness <= 100) {
                    this.brightness = brightness;
                    return true;
                }
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getState() {
        return "Light :" + name + " " + status + " " + brightness + " " + protocol;
    }
}
