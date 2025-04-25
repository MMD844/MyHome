class Rule {
    public String deviceName;
    public String time;
    public String action;

    public Rule(String deviceName, String time, String action) {
        this.deviceName = deviceName;
        this.time = time;
        this.action = action;
    }

    public String toString() {
       return deviceName + " " + time + " " + action;
    }
}
