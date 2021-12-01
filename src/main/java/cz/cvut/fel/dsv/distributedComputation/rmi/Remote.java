package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.net.InetAddress;

public class Remote {
  private InetAddress address;
  private DsvStub remote;

  public InetAddress getAddress() {
    return address;
  }

  public void setAddress(InetAddress address) {
    this.address = address;
  }

  public DsvStub getRemote() {
    return remote;
  }

  public void setRemote(DsvStub remote) {
    this.remote = remote;
  }

  @Override
  public String toString() {
    return address.getHostAddress();
  }
}
