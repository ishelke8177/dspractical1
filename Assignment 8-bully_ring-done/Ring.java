import java.util.Scanner;

public class Ring {

	int n, inactive_count;
	int coordinator;
	boolean[] process_state;
	
	public Ring(int n) {
		this.n = n;
		this.inactive_count = 0;
		this.process_state = new boolean[n];
		//	State all processes as active		
		for(int i = 0; i < n; i++) {
			this.process_state[i] = true;
		}
		this.coordinator = n - 1;
		System.out.println("Process " + n + " is set as initial coordinator");
	}
	
	public void deactivate_process(int id) {
		/*
		 *	Input	:	Process ID
		 *	Utility :	Deactivate process
		 *	Output	:	None	
		 */
		 if(id > n || id < 0) {
		 	System.out.println("Invalid ID");
		 	return;
		 }
		 if(!process_state[id - 1]) {
		 	System.out.println("Process already inactive");
		 } else {
		 	process_state[id - 1] = false;
		 	System.out.println("Process " + id + " deactivated");
		 	inactive_count += 1;
		 }
	}
	
	public void view_ring() {
		/*
		 *	Input	:	None
		 *	Utility :	Display ring 
		 *	Output	:	Console output
		 */
		 
		 if(this.inactive_count == n) {
		 	System.out.println("All members inactive...");
		 	return;
		 }
		 System.out.println("Active Ring members");
		 for(int i = 0; i < n; i++) {
		 	if(process_state[i]) System.out.println((i + 1) + " ");
		 }
	}
	
	public void election(int id) {
		/*
		 *	Input	:	Initiator
		 *	Utility :	Hold election process to select coordinator
		 *	Output	:	Coordinator id
		 */
		 if(this.inactive_count == this.n) {
		 	System.out.println("All members inactive...");
		 	System.out.println("Aborting election process...");
			this.coordinator = -1;
		 	return;
		 }
		 id = id - 1;		 
		 int current_coordinator = id;	 
		 int token = (id + 1) % n;
		 System.out.println("\nElection initiator : " + (id + 1));
		 //	Election algorithm
		 while(token != id) {
			System.out.println("Token at process " + (token + 1));
			if(this.process_state[token]) {
				if(token > current_coordinator) {
					current_coordinator = token;
				}
			}
			token = (token + 1) % this.n;
		 }
		 System.out.println("Elected coordinator : " + (current_coordinator + 1));
		 this.coordinator = current_coordinator;
	}

	public void ping_coordinator(int id) {
		if(!this.process_state[id - 1]) {
			System.out.println("Process inactive...");
			System.out.println("Aborting...");
			return;
		}
		if(id == coordinator) {
			if(this.process_state[id - 1]) {
				System.out.println("Coordinator active");
			} else {
				System.out.println("Coordinator inactive!\nInitiate election from other process");
			}
		}
		System.out.println("Sending message from process " + id + " to " + (this.coordinator + 1));
		if(!this.process_state[this.coordinator]) {
			System.out.println("Coordinator process not responding");
			System.out.println("Conducting election...");
			this.election(id);
		} else {
			System.out.println("Coordinator alive");
		}
	}

	public void setCoordinator(int c) {
		this.coordinator = c;
	}

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		int n = sc.nextInt();
		Ring ring = new Ring(n);
		
		while(choice < 5) {
			System.out.println("***********Menu***********");
			System.out.println("1. Deactivate a process");
			System.out.println("2. Ping coordinator");
			System.out.println("3. View Ring");
			System.out.println("4. Election");
			System.out.println("5. Exit");
			System.out.println("**************************");
			System.out.println("Enter Choice : ");
			choice = sc.nextInt();
			switch(choice) {
				case 1 : {
					int id;
					System.out.println("Enter process ID : ");
					id = sc.nextInt();
					ring.deactivate_process(id);
					System.out.println("");
					break;
				}
				case 2 : {
					int id;
					System.out.println("Enter process ID for sender");
					id = sc.nextInt();
					ring.ping_coordinator(id);
					System.out.println("");
					break;
				}
				case 3 : {
					ring.view_ring();
					System.out.println("");
					break;
				}
				case 4 : {
					int id;
					System.out.println("Enter process ID for election initiator");
					id = sc.nextInt();
					ring.election(id);
					System.out.println("");
					break;
				}
				case 5 :
				default : {
					System.out.println("");
					break;
				}
			}
		
		}			
		System.out.println("Program terminated..");	
		sc.close();
	}
}

/*
The given code implements the Ring election algorithm. Let's go through the code and understand how it works:

1. The `Ring` class is defined with various methods and variables to handle the ring election algorithm.

2. The constructor `Ring(int n)` initializes the class variables. It takes the number of processes `n` as input and sets all processes as active. The coordinator is set as the last process (n-1).

3. The `deactivate_process(int id)` method deactivates a specific process. It takes the process ID as input and marks the process as inactive by setting its corresponding element in the `process_state` array to `false`. The `inactive_count` is incremented to keep track of the number of inactive processes.

4. The `view_ring()` method displays the active processes in the ring. It iterates over the `process_state` array and prints the IDs of processes that are active.

5. The `election(int id)` method initiates the election process. It takes an initiator's ID as input. If all processes are inactive, it aborts the election process. Otherwise, it starts the election algorithm by setting the current coordinator as the initiator and initializing a token to the next process in the ring.

6. The election algorithm is implemented using a loop that passes the token through each process in the ring. Each active process checks if it has a higher ID than the current coordinator. If it does, it updates the current coordinator. The token moves to the next process in the ring using the modulo operator. After completing the loop, the elected coordinator is determined, and the `coordinator` variable is updated.

7. The `ping_coordinator(int id)` method checks the status of the coordinator. If the process with the given ID is inactive, it aborts the ping process. If the process is the coordinator, it checks if the coordinator is active or not. If the coordinator is inactive, it initiates an election process from another active process. If the coordinator is active, a message is sent to the coordinator indicating that it is alive.

8. The `setCoordinator(int c)` method allows manually setting the coordinator.

9. In the `main` method, the user is prompted to enter the number of processes. An instance of the `Ring` class is created with the given number of processes.

10. A menu is displayed, allowing the user to perform various actions: deactivate a process, ping the coordinator, view the ring, initiate an election, or exit the program.

11. Based on the user's choice, the corresponding method of the `Ring` class is called.

12. The program continues to loop until the user chooses to exit.

Overall, this code implements a simple simulation of the Ring election algorithm, where processes can be deactivated, and the coordinator can be checked and updated.
 */