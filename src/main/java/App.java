import cli.CommandLineController;
import dependencyinjection.ServiceFactory;

public class App {
    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
        CommandLineController commandLineController = new CommandLineController(serviceFactory);

        while (commandLineController.run());
    }
}
