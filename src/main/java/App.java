import cli.CommandLineController;
import dependencyinjection.ServiceProvider;
import memory.ConversionMemory;

public class App {
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProvider();
        CommandLineController commandLineController = new CommandLineController(serviceProvider);

        while (commandLineController.run());

        ConversionMemory.getInstance().getConversions().forEach(System.out::println);
    }
}
