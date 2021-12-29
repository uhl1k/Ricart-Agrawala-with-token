package cz.cvut.fel.dsv.distributedComputation.rmi;

import java.net.InetAddress;
import java.util.UUID;

public class Remote implements Comparable<Remote> {
  private InetAddress address;
  private DsvStub remote;
  private UUID uuid;

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

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
    return "Node " + getUuid().toString() + " at: " + getAddress().getHostAddress();
  }

  @Override
  public int compareTo(Remote remote) {
    return uuid.compareTo(remote.getUuid());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Remote) {
      Remote rem = (Remote) obj;
      return uuid.equals(rem.getUuid());
    }
    return false;
  }
}
