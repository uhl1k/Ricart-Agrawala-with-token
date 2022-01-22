package cz.cvut.fel.dsv.distributedComputation.model;

import java.net.InetAddress;

/**
 * ID of the node. It consists of IP address and port which should be sufficiently unique.
 *
 * @author Roman Janků (jankurom@fel.cvut.cz)
 */
public class NodeID implements Comparable<NodeID> {

  private final InetAddress address;
  private final int port;

  /**
   * Create a new ID from given parameters.
   *
   * @param address IP address of the node.
   * @param port Port number of the node.
   */
  public NodeID(InetAddress address, int port) {
    this.address = address;
    this.port = port;
  }

  /**
   * Returns the bytes of an address.
   *
   * @return Bytes of the address.
   */
  public byte[] getAddressBytes() {
    return address.getAddress();
  }

  /**
   * Returns the IP address.
   *
   * @return IP address.
   */
  public InetAddress getAddress() {
    return address;
  }

  /**
   * Returns the port number.
   *
   * @return Port number.
   */
  public int getPort() {
    return port;
  }

  @Override
  public int compareTo(NodeID nodeID) {
    var leftBytes = getAddressBytes();
    var rightBytes = nodeID.getAddressBytes();

    //  if one address is longer, it is bigger
    if (leftBytes.length < rightBytes.length) {
      return -1;
    } else if (leftBytes.length > rightBytes.length) {
      return 1;
    }

    //  let's compare the bytes, first address having bigger byte is bigger
    for (int i = 0; i < leftBytes.length; i++) {
      if (Byte.toUnsignedInt(leftBytes[i]) < Byte.toUnsignedInt(rightBytes[i])) {
        return -1;
      } else if (Byte.toUnsignedInt(leftBytes[i]) > Byte.toUnsignedInt(rightBytes[i])) {
        return 1;
      }
    }

    //  compare the ports
    if (port < nodeID.port) {
      return -1;
    } else if (port > nodeID.port) {
      return 1;
    }

    //  now they are equal
    return 0;
  }

  @Override
  public String toString() {
    if (getAddressBytes().length == 4) {
      return address.toString().substring(1) + ":" + port;
    }
    return "[" + address.toString().substring(1) + "]:" + port;
  }

  @Override
  public boolean equals(Object object) {
    //  if the object is null or it is not instance of NodeID there is nothing to compare
    if (object == null || !(object instanceof NodeID)) {
      return false;
    } else if (object == this) { //  isn't the object the same?
      return true;
    }
    //  they are equal if addresses and ports are the same
    var id = (NodeID) object;
    return id.getAddress().equals(address) && (id.getPort() == port);
  }

  @Override
  public int hashCode() {
    //  just sum the bytes
    var bytes = getAddressBytes();
    int sum = 0;
    for (int i = 0; i < bytes.length; i++) {
      sum += Byte.toUnsignedInt(bytes[i]);
    }
    //  and add port on top of it
    return sum + port;
  }
}
