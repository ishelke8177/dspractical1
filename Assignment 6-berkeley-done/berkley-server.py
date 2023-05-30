# Python program imitating a clock server

# Importing necessary libraries

# from functools import reduce
from dateutil import parser
import threading
import datetime
import socket
import time 

# datastructure used to store client address and clock data
client_data = {}

'''
Nested thread function used to receive clock time 
from a connected client
'''

def startReceivingClockTime(connector, address):
    
    while True:
        # Receive clock time
        clock_time_string = connector.recv(1024).decode()
        clock_time = parser.parse(clock_time_string)
        clock_time_diff = datetime.datetime.now() - clock_time
        
        client_data[address] = {
            "clock_time" : clock_time,
            "time_difference" : clock_time_diff,
            "connector" : connector
        }
        
        print("Client data updated with : " + str(address), end = "\n\n")
        time.sleep(5)
        

'''
Master thread function used to open portal for accepting clients over given port
'''

def startConnecting(master_server):
    
    # Fetch clock time at slaves/clients
    while True:
        # Accepting a client/slave clockc client
        master_slave_connector, addr = master_server.accept()
        slave_address = str(addr[0]) + ":" + str(addr[1])
        
        print(slave_address + " got connected successfully")
        
        current_thread = threading.Thread(target = startReceivingClockTime, args = (master_slave_connector, slave_address))
        
        current_thread.start()
        

'''
Subroutine function used to fetch average clock difference
'''

def getAverageClockDiff():
    
    current_client_data = client_data.copy()
    
    time_difference_list = list(client['time_difference'] for client_addr, client in client_data.items())
    
    sum_of_clock_difference = sum(time_difference_list, datetime.timedelta(0, 0))
    
    average_clock_difference = sum_of_clock_difference / len(client_data)
    
    return average_clock_difference


'''
Master sync thread function used to generate cycles of clock synchronization in the network 
'''

def synchronizeAllClocks():
    
    while True:
        
        print("New synchronization cycle started")
        print("Number of clients to be synchronized: " + str(len(client_data)))
        
        if len(client_data) > 0:
            average_clock_difference = getAverageClockDiff()
            for client_addr, client in client_data.items():
                try:
                    synchronized_time = datetime.datetime.now() + average_clock_difference
                    
                    client['connector'].send(str(synchronized_time).encode())
                    
                except Exception as e:
                    print("Something went wrong while sending synchronized time through " + str(client_addr))
                        
                        
        else:
            print("No client data. " + "Synchronization not applicable")
            
        print("\n\n")
        
        time.sleep(5)
        

'''
Function used to initiate the Clock Server / Master Node
'''

def initiateClockServer(port = 8080):
    master_server = socket.socket()
    master_server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    print("Socket at master node createdsuccessfully\n")
    
    master_server.bind(('', port))
    
    # Start listening to requests
    master_server.listen(10)
    print("Clock server started...\n")
    
    # Start making connections
    print("Starting to make connections...\n")
    master_thread = threading.Thread(target = startConnecting, args = (master_server, ))
    master_thread.start()
    
    # Start synchronization
    print("Starting synchronization parallelly...\n")
    sync_thread = threading.Thread(target = synchronizeAllClocks, args = ())
    sync_thread.start()

    
    
# Driver
if __name__== "__main__":
    #Trigger the Clock Server
    initiateClockServer(port = 8080)


'''
explain "initiateClockServer" method


The initiateClockServer method is responsible for setting up and starting the clock server or the master node. It creates a socket, binds it to the specified port, and listens for incoming connections from clients. It also starts two separate threads to handle the connection acceptance and clock synchronization processes concurrently.

Here's a breakdown of the initiateClockServer method:

It takes an optional parameter port, which specifies the port number on which the clock server will listen for connections. The default value is set to 8080 if no port number is provided.

It creates a socket using the socket.socket() function.

The setsockopt method is called on the socket to set the SO_REUSEADDR option. This option allows reusing the same address if the server is restarted, which can be useful for development and testing purposes.

The socket is bound to an empty IP address ('') and the specified port using the bind method.

The server socket is set to the listening mode by calling the listen method and passing the backlog parameter as 10, which specifies the maximum number of queued connections.

Messages indicating the successful creation and start of the clock server are printed.

A new thread is started to handle the acceptance of client connections. The startConnecting function is executed in this thread, passing the master_server socket as an argument.

Another thread is started to handle the clock synchronization process. The synchronizeAllClocks function is executed in this thread.

The purpose of using threads is to allow concurrent execution of the connection acceptance and clock synchronization processes. The main thread remains free to continue executing other parts of the program or perform additional tasks.

It's important to note that this code snippet provides a basic outline for a clock server implementation. Depending on your specific requirements, you may need to modify and enhance the code to handle error cases, implement additional functionalities, or address any security considerations.
'''


'''
explain "startConnecting"


The startConnecting method is a thread function that handles the process of accepting client connections in the clock server. It runs in a separate thread to allow concurrent execution with other parts of the program. The purpose of this method is to continuously accept client connections and start a new thread for each connected client to receive their clock time.

Here's an explanation of the startConnecting method:

It takes a parameter master_server, which represents the server socket created in the initiateClockServer method.

The method enters an infinite loop, continuously accepting client connections and starting a new thread for each client.

Inside the loop, the accept method is called on the master_server socket. This method blocks until a client connection is established, and it returns a new socket (master_slave_connector) representing the connection and the address (addr) of the client.

The address of the connected client is extracted from addr and converted to a string representation.

A message is printed to indicate that the client has been successfully connected.

A new thread is created, with the target function set to startReceivingClockTime and the arguments set to master_slave_connector and slave_address (client address).

The newly created thread is started using the start method.

The loop continues to the next iteration, accepting the next client connection.

By using separate threads for each client connection, the clock server can handle multiple clients concurrently and receive clock time updates from them. Each client runs in its own thread, allowing independent processing and synchronization with the master clock.

It's important to note that this code snippet provides a basic implementation for accepting client connections. Depending on your specific requirements, you may need to modify and enhance the code to handle error cases, implement additional functionalities, or address any security considerations.
'''

'''
explain "startReceivingClockTime"

The startReceivingClockTime method is a nested thread function that runs in a separate thread for each connected client in the clock server. Its purpose is to receive the clock time from the client, update the client_data dictionary with the received data, and keep the clock time synchronized with the server.

Here's an explanation of the startReceivingClockTime method:

It takes two parameters: connector, which represents the socket connection with the client, and address, which is the address of the client.

The method enters an infinite loop to continuously receive clock time updates from the client.

Inside the loop, the recv method is called on the connector socket to receive data from the client. It expects the clock time as a string representation.

The received clock time string is decoded using the decode method to convert it into a human-readable format.

The clock time string is parsed into a datetime object using the parser.parse method from the dateutil library.

The time difference between the received clock time and the current server time is calculated by subtracting the parsed clock time from the current time (datetime.datetime.now()).

The received clock time, time difference, and the connector socket are stored in the client_data dictionary using the client's address as the key. This allows tracking the clock data of each connected client.

A message is printed to indicate that the client data has been updated with the address.

The thread sleeps for 5 seconds using the time.sleep method before going through the loop again. This delay allows time for receiving subsequent clock time updates from the client.

By running this method in a separate thread for each client, the clock server can receive clock time updates from multiple clients concurrently and maintain their clock synchronization information in the client_data dictionary.
'''