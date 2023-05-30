import java.util.ArrayList;
import java.util.List;

class Process {
    private int processId;
    private boolean isCoordinator;
    private List<Process> processes;

    public Process(int processId) {
        this.processId = processId;
        this.isCoordinator = false;
        this.processes = new ArrayList<>();
    }

    public void addProcess(Process process) {
        processes.add(process);
    }

    public void election() {
        // Assume the current process initiates the election
        for (Process process : processes) {
            if (process.getProcessId() > this.processId) {
                process.election();
            }
        }
        announceCoordinator();
    }

    public void announceCoordinator() {
        isCoordinator = true;
        System.out.println("Process " + processId + " elected as the coordinator.");
        for (Process process : processes) {
            process.coordinatorChanged(this);
        }
    }

    public void coordinatorChanged(Process newCoordinator) {
        if (isCoordinator && newCoordinator.getProcessId() != processId) {
            isCoordinator = false;
            System.out.println("Process " + processId + " received new coordinator: " + newCoordinator.getProcessId());
        }
    }

    public int getProcessId() {
        return processId;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }
}

public class BullyAlgorithm {
    public static void main(String[] args) {
        Process p1 = new Process(1);
        Process p2 = new Process(2);
        Process p3 = new Process(3);
        Process p4 = new Process(4);
        Process p5 = new Process(5);

        // Add all processes to the network
        p1.addProcess(p2);
        p1.addProcess(p3);
        p1.addProcess(p4);
        p1.addProcess(p5);
        p2.addProcess(p3);
        p2.addProcess(p4);
        p2.addProcess(p5);
        p3.addProcess(p4);
        p3.addProcess(p5);
        p4.addProcess(p5);

        // Start the election
        p1.election();

        // Check the elected coordinator
        for (Process process : List.of(p1, p2, p3, p4, p5)) {
            if (process.isCoordinator()) {
                System.out.println("Elected Coordinator: Process " + process.getProcessId());
                break;
            }
        }
    }
}
