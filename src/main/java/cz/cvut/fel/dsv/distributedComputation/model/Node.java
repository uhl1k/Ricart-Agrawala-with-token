package cz.cvut.fel.dsv.distributedComputation.model;

import java.net.InetAddress;

/**
 * Representation of node in the program containing all information and connections.
 *
 * @author Roman Janků (jankurom@fel.cvut.cz)
 */
public class Node implements Comparable<Node> {
  private final NodeID nodeID;

  private Node(NodeID nodeID) {
    this.nodeID = nodeID;
  }

  /**
   * Creates a new Node instance with all connections and with the given NodeID.
   *
   * @param nodeID ID to create from.
   * @return Newly created Node.
   */
  public static Node getNodeFromNodeID(NodeID nodeID) {
    return new Node(nodeID);
  }

  /**
   * Creates a new Node instance with all connections and with NodeID created from given address and
   * port number.
   *
   * @param address Address of the new Node.
   * @param port Port number of the new node.
   * @return Newly created node.
   */
  public static Node getNodeFromAddressAndPort(InetAddress address, int port) {
    return new Node(new NodeID(address, port));
  }

  /**
   * Returns the ID of the node.
   *
   * @return ID of the node.
   */
  public NodeID getNodeID() {
    return nodeID;
  }

  @Override
  public String toString() {
    return "Node at " + nodeID.toString() + ".";
  }

  @Override
  public int compareTo(Node node) {
    return nodeID.compareTo(node.getNodeID());
  }

  @Override
  public int hashCode() {
    return nodeID.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || !(object instanceof Node)) {
      return false;
    } else if (object == this) {
      return true;
    }
    var node = (Node) object;
    return nodeID.equals(node.getNodeID());
  }
}
