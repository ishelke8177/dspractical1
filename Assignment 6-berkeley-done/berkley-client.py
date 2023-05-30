# Python program imitating a client process

from timeit import default_timer as timer
from dateutil import parser
import threading
import datetime
import socket
import time

'''
Client thread function used to send time at client side 
'''

def startSendingTime(slave_client):
    
    while True:
        # Provide server with clock time at the client
        slave_client.send(str(datetime.datetime.now()).encode())
        print("Recent time sent successfully", end = "\n\n")
        time.sleep(5)
        

'''
Client Thread function used to receive synchronized time
'''

def startReceivingTime(slave_client):
    
    while True:
        # Receive data from the server
        Synchronized_time = parser.parse(slave_client.recv(1024).decode())
        print("Synchronized time at the client is: " + str(Synchronized_time), end = "\n\n")
        

        
'''
Function used to Synchronize client process time
'''

def initiateSlaveClient(port = 8080):

    slave_client = socket.socket()
    # Connect to the clock server on local computer
    slave_client.connect(('127.0.0.1', port))
    
    # Start sending time to server
    print("Starting to receive time from server\n")
    send_time_thread = threading.Thread(target = startSendingTime, args = (slave_client, ))
    send_time_thread.start()
    
    # Start receiving synchronized from server
    print("Starting to receiving synchronized time from server\n")
    receive_time_thread = threading.Thread(target = startReceivingTime, args = (slave_client, ))
    receive_time_thread.start()
    

# Driver function
if __name__=="__main__":
    # Initialize the slave/client
    initiateSlaveClient(port = 8080)


'''
The provided code represents a basic implementation of a client process in Python. It acts as a client connecting to the clock server and sends its local time to the server while receiving synchronized time from the server. Here's a summary of the main functions and their purposes:

startSendingTime(slave_client): This function runs in a separate thread and continuously sends the current local time of the client to the clock server. It uses the datetime.datetime.now() function to get the current time and sends it as a string to the server using the send method of the slave_client socket. It sleeps for 5 seconds before sending the next time update.

startReceivingTime(slave_client): This function also runs in a separate thread and continuously receives synchronized time from the clock server. It uses the recv method of the slave_client socket to receive data from the server. The received data is decoded, parsed into a datetime object using parser.parse from the dateutil library, and printed as the synchronized time at the client. This process repeats indefinitely.

initiateSlaveClient(port): This function is the entry point for the client process. It creates a socket using socket.socket(), connects it to the clock server at the specified IP address ('127.0.0.1') and port number, and starts two separate threads for sending time and receiving synchronized time from the server.

The client process continuously sends its local time to the server and receives synchronized time from the server, ensuring that its clock remains synchronized with the clock server.

It's important to note that this code snippet provides a basic implementation of a client process. Depending on your specific requirements, you may need to modify and enhance the code to handle error cases, implement additional functionalities, or address any security considerations.
'''