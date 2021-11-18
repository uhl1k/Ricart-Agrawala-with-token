package cz.cvut.fel.dsv.distributedComputation.model;

import java.net.Inet4Address;

public class ClientID implements Comparable<ClientID> {

  private final Inet4Address address;
  private final int port;

  public ClientID(Inet4Address address, int port) {
    this.address = address;
    this.port = port;
  }

  public byte[] getAddressBytes() {
    return address.getAddress();
  }

  public Inet4Address getAddress() {
    return address;
  }

  public int getPort() {
    return port;
  }

  @Override
  public int compareTo(ClientID clientID) {
    var leftBytes = getAddressBytes();
    var rightBytes = clientID.getAddressBytes();

    for (int i = 0; i <4; i++) {
      if (leftBytes[i] < rightBytes[i]) {
        return -1;
      } else if (leftBytes[i] > rightBytes[i]) {
        return 1;
      }
    }

    if (port < clientID.port) {
      return -1;
    } else if (port > clientID.port) {
      return 1;
    }

    return 0;
  }

  @Override
  public String toString() {
    return address.toString() + ":" + port;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || !(object instanceof ClientID)) {
      return false;
    } else if (object == this) {
      return true;
    }
    var id = (ClientID) object;
    return id.getAddress().equals(address) && (id.getPort() == port);
  }

  @Override
  public int hashCode() {
    var bytes = getAddressBytes();
    return bytes[0] + bytes[1] + bytes[2] + bytes[3] + port;
  }
}
