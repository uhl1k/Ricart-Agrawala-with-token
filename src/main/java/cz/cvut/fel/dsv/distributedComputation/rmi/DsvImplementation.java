package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.Main;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class DsvImplementation implements DsvStub {
  @Override
  public void connected(InetAddress address, int port) {

  }

  @Override
  public Map<InetAddress, Integer> connecting(InetAddress address, int port) {
    Main.getMain().newNode(address, port);
    return new HashMap<>();
  }
}
