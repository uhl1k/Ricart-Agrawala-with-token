package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.Remote;
import java.util.Map;

public interface DsvStub extends Remote, Serializable {
  void connected(InetAddress address, int port);
  Map<InetAddress, Integer> connecting(InetAddress address, int port);
}
