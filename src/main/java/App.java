import cli.CommandLineController;
import dependencyinjection.ServiceProvider;

public class App {
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProvider();
        CommandLineController commandLineController = new CommandLineController(serviceProvider);

        while (true) {
            commandLineController.run();
        }
    }
}
