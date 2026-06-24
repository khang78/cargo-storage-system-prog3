import geschaeftslogik.Manager;
import simulation.AddSimulation;
import simulation.DeleteSimulation;

public class Simulation1 {
    public static void main(String[] args) {
        int capacity = Integer.parseInt(args[0]);
        Manager manager = new Manager(capacity);

        AddSimulation adder = new AddSimulation(manager);
        DeleteSimulation deleter = new DeleteSimulation(manager);

        adder.start();
        deleter.start();
    }
}
