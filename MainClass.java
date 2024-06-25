public class MainClass {
    public static void main(String[] args) {
        SystemSanguain system = new SystemSanguain();
        UserInterface ui = new UserInterface(system);
        ui.displayMenu();
    }
}