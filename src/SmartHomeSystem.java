import java.util.*;

public class SmartHomeSystem {

    private Map<String, Device> devices = new HashMap<>();
    private List<Rule> rules = new ArrayList<>();
    private List<String> deviceOrder = new ArrayList<>();

    public String addDevice (String deviceType, String name, String protocol) {
        if (devices.containsKey(name)) {
            return "duplicate device name";
        }

        if (!deviceType.equals("light") && !deviceType.equals("thermostat")) {
            return "invalid input";
        }

        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth")) {
            return "invalid input";
        }

        if (deviceType.equals("light")) {
            devices.put(name, new Light(name, protocol));
        } else {
            devices.put(name, new Thermostat(name, protocol));
        }
        deviceOrder.add(name);
        return "Device added succesfully";
    }

    public String setDevice(String name, String propertyName, String value) {
        if (!devices.containsKey(name)) {
            return "Device not found";
        }

        Device device = devices.get(name);

        if (!device.setProperty(propertyName, value)) {
            if (propertyName.equals("status") || propertyName.equals("brightness") ||
                    propertyName.equals("temperature")) {
                return "Invalid value";
            }
            return "Invalid property";
        }
        return "Device updated successfully";
    }

    public String removeDevice(String name) {
        if (!devices.containsKey(name)) {
            return "Device not found";
        }
        devices.remove(name);
        deviceOrder.remove(name);
        rules.removeIf(rule -> rule.deviceName.equals(name));
        return "Device removed successfully !";
    }

    public String addRule(String deviceName, String time, String action) {
        if (!devices.containsKey(deviceName)) {
            return "Device not found";
        }
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            return "Invalid time";
        }
        if (!action.equals("on") && !action.equals("off")) {
            return "Invalid action";
        }
        for (Rule rule : rules) {
            if (rule.deviceName.equals(deviceName) && rule.time.equals(time)) {
                return "Duplicate Rule";
            }
        }
        rules.add(new Rule(deviceName, time, action));
        return "Rule added successfully";
    }


    public String checkRules(String time) {
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            return "Invalid time";
        }
        for (Rule rule : rules) {
            if (rule.time.equals(time) && devices.containsKey(rule.deviceName)) {
                Device device = devices.get(rule.deviceName);
                device.setProperty("status", rule.action);
            }
        }
        return "Rules checked";
    }

    public String listRules() {
        if (rules.isEmpty()) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (Rule rule : rules) {
            output.append(rule.toString()).append("\n");
        }
        return output.toString().trim();
    }

    public String listDevices() {
        if (deviceOrder.isEmpty()) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (String name : deviceOrder) {
            output.append(devices.get(name).getState()).append("\n");
        }
        return output.toString().trim();
    }
}
