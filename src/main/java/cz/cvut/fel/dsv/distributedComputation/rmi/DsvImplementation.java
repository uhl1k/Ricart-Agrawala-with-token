package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.net.InetAddress;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static cz.cvut.fel.dsv.distributedComputation.rmi.Server.NAME;
import static cz.cvut.fel.dsv.distributedComputation.rmi.Server.PORT;

public class DsvImplementation extends UnicastRemoteObject implements DsvStub {
  protected DsvImplementation() throws RemoteException {
  }

  @Override
  public void disconnecting(InetAddress address) throws RemoteException {
    Server.getInstance().removeRemote(address);
  }

  @Override
  public List<InetAddress> connecting(InetAddress address, UUID uuid) throws RemoteException, NotBoundException {
    System.out.println("Incomming from: " + address.getHostAddress());
    Remote remote = new Remote();
    remote.setAddress(address);
    remote.setUuid(uuid);
    remote.setRemote((DsvStub) LocateRegistry.getRegistry(address.getHostAddress(), PORT).lookup(NAME));
    Server.getInstance().addRemote(remote);
    return Server.getInstance().getRemotes().stream().filter(r -> r.getUuid() != remote.getUuid()).map(r -> r.getAddress()).collect(Collectors.toList());
  }

  @Override
  public UUID getUUID() {
    return Server.getInstance().getUuid();
  }

  @Override
  public void request(UUID uuid, int clock) throws RemoteException {
    var remote = Server.getInstance().getRemotes().stream().filter(r -> r.getUuid().equals(uuid)).findFirst();
    if (remote.isPresent()) {
      Server.getInstance().receivedRequest(remote.get(), clock);
    } else  {
      throw new RemoteException("Could not find remote with given uuid!");
    }
  }

  @Override
  public void dropped(InetAddress address) throws RemoteException {
    Server.getInstance().removeRemote(address);
  }

  @Override
  public void token() throws RemoteException {
    Server.getInstance().generateToken();
    System.out.println("Token received.");
  }

  @Override
  public void setVariable(int value) {
    Server.getInstance().setVariable(value);
  }

  @Override
  public int getVariable() {
    return Server.getInstance().getVariable();
  }
}
