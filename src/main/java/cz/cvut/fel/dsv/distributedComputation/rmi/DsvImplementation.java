package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.net.InetAddress;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import static cz.cvut.fel.dsv.distributedComputation.rmi.Server.NAME;
import static cz.cvut.fel.dsv.distributedComputation.rmi.Server.PORT;

public class DsvImplementation implements DsvStub {
  protected DsvImplementation() throws RemoteException {
  }

  @Override
  public void connected(InetAddress address) {

  }

  @Override
  public Map<InetAddress, Integer> connecting(InetAddress address) throws RemoteException, NotBoundException {
    System.out.println("Incomming from: " + address.getHostAddress());
    Remote remote = new Remote();
    remote.setAddress(address);
    remote.setRemote((DsvStub) LocateRegistry.getRegistry(address.getHostAddress(), PORT).lookup(NAME));
    Server.getInstance().addRemote(remote);
    return new HashMap<>();
  }
}
