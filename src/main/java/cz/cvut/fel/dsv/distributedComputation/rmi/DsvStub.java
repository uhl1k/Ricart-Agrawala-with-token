package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface DsvStub extends Remote, Serializable {
  void connected(InetAddress address) throws RemoteException;
  Map<InetAddress, Integer> connecting(InetAddress address) throws RemoteException, NotBoundException, MalformedURLException;
}
