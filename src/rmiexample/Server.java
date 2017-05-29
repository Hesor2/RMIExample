package rmiexample;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements HelloInterface 
{
    private int port = 1099;    // the port on the server; default for rmi is 1099
    private String host = "localhost"; // the server's host

    public Server() throws RemoteException {
        super();
    }
    
    public Server(int port) throws RemoteException {
        super();
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    @Override
    public String helloFrom(String from) throws RemoteException {
        return "Hello from " + from;
    }
    
    public static void main (String[] args) {
        try {
            Server remoteObj = new Server();
            // Start automatically the rmiregistry
            LocateRegistry.createRegistry(1099);
            
            // For this to work you need to start the rmiregistry manually
            // from the folder   classes/rmi4
            // Bind the remote object's stub in the registry  
            //Registry registry = LocateRegistry.getRegistry();
            //registry.bind("Hello", stub);
            
            Naming.rebind("rmi://" + remoteObj.getHost() + ":" + remoteObj.getPort() + "/Server", remoteObj);
            System.out.println ("Hello Server is ready.");
            
        } catch (RemoteException | MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
}
