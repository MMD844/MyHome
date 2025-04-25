public class Thermostat extends Device{

    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        this.temperature = 20;
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
        else if (propertyName.equals("temperature")) {
            try {
                int temp = Integer.parseInt(value);
                if (temp >= 10 && temp <= 30) {
                    this.temperature = temp;
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
        return "thermostat: " + name + " " + status + " " + temperature + "C " + protocol;
    }
}
