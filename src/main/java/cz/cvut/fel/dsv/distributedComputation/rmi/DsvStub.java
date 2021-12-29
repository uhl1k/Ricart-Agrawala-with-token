package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface DsvStub extends Remote, Serializable {
  List<InetAddress> connecting(InetAddress address, UUID uuid) throws RemoteException, NotBoundException, MalformedURLException;
  void disconnecting(InetAddress address) throws RemoteException;
  UUID getUUID() throws RemoteException;
}
