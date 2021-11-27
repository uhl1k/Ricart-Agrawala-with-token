package cz.cvut.fel.dsv.distributedComputation;

import cz.cvut.fel.dsv.distributedComputation.rmi.DsvStub;
import java.net.InetAddress;

public class Remote {
  private InetAddress address;
  private int port;
  private DsvStub remote;

  public InetAddress getAddress() {
    return address;
  }

  public void setAddress(InetAddress address) {
    this.address = address;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public DsvStub getRemote() {
    return remote;
  }

  public void setRemote(DsvStub remote) {
    this.remote = remote;
  }
}
