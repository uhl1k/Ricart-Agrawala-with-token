package cz.cvut.fel.dsv.distributedComputation.model;

import cz.cvut.fel.dsv.distributedComputation.rmi.StubInterface;
import cz.cvut.fel.dsv.distributedComputation.server.Server;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Representation of node in the program containing all information and connections.
 *
 * @author Roman Janků (jankurom@fel.cvut.cz)
 */
public class Node implements Comparable<Node> {
  private final NodeID nodeID;
  private final StubInterface remote;

  private Node(NodeID nodeID) throws RemoteException, NotBoundException {
    this.nodeID = nodeID;
    Registry registry = LocateRegistry.getRegistry(nodeID.getAddress().toString(), nodeID.getPort());
    remote = (StubInterface) registry.lookup(Server.DSV_REGISTRY_NAME);
  }

  /**
   * Creates a new Node instance with all connections and with the given NodeID.
   *
   * @param nodeID ID to create from.
   * @return Newly created Node.
   */
  public static Node getNodeFromNodeID(NodeID nodeID) throws NotBoundException, RemoteException {
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
  public static Node getNodeFromAddressAndPort(InetAddress address, int port) throws NotBoundException, RemoteException {
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

  public StubInterface getRemote() {
    return remote;
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
