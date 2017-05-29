package rmiexample;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client 
{
    private int port = 1099;    // the port on the server; default for rmi is 1099
    private String host = "localhost"; // the server's host
    private String name = "Andrea";
 
    public Client() {
    }
   
    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }
 
    public int getPort() {
        return port;
    }
 
    public void setPort(int port) {
        this.port = port;
    }
 
    public String getHost() {
        return host;
    }
 
    public void setHost(String host) {
        this.host = host;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
   
    public static void main (String[] argv) {
        try {
            Client c = new Client();
            String serverName = "rmi://" + c.getHost() + ":" + c.getPort() + "/Server";
            HelloInterface hello = (HelloInterface) Naming.lookup(serverName);
            System.out.println(hello.helloFrom(c.getName()));
           
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            ex.printStackTrace();
        }
    }
   
   
}