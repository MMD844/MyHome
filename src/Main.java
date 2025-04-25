import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();

        int q = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < q; i++) {
            String[] command = scanner.nextLine().split(" ");
            String cmd = command[0];

            try {
                switch (cmd) {
                    case "add_device":
                        if (command.length != 4) {
                            System.out.println("Invalid input");
                            continue;
                        }
                        System.out.println(system.addDevice(command[1], command[2], command[3]));
                        break;

                    case "set_device":
                        if (command.length != 4) {
                            System.out.println("Invalid property");
                            continue;
                        }
                        System.out.println(system.setDevice(command[1], command[2], command[3]));
                        break;

                    case "remove_device":
                        if (command.length != 2) {
                            System.out.println("Device not found");
                            continue;
                        }
                        System.out.println(system.removeDevice(command[1]));
                        break;

                    case "list_devices":
                        System.out.println(system.listDevices());
                        break;

                    case "add_rule":
                        if (command.length != 4) {
                            System.out.println("Invalid time");
                            continue;
                        }
                        System.out.println(system.addRule(command[1], command[2], command[3]));
                        break;

                    case "check_rules":
                        if (command.length != 2) {
                            System.out.println("Invalid time");
                            continue;
                        }
                        System.out.println(system.checkRules(command[1]));
                        break;

                    case "list_rules":
                        System.out.println(system.listRules());
                        break;

                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        scanner.close();
    }
}
