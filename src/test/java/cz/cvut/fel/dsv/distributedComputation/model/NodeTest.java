package cz.cvut.fel.dsv.distributedComputation.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

class NodeTest {
  //@Test
  void constructor_nodeFromNodeIDAndAddressAndPortShouldBeSame() {
    Node node1 = null;
    Node node2 = null;
    try {
      node1 = Node.getNodeFromNodeID(new NodeID(InetAddress.getByName("10.0.0.1"), 123));
      node2 = Node.getNodeFromAddressAndPort(InetAddress.getByName("10.0.0.1"), 123);
    } catch (UnknownHostException | NotBoundException | RemoteException ex) {
      fail();
    }
    assertEquals(node1, node2);
  }

  //@Test
  void constructor_nodeFromNodeIDAndAddressAndPortShouldNotBeSameIfDifferentIPOrPort() {
    Node node1 = null;
    Node node2 = null;
    Node node3 = null;
    try {
      node1 = Node.getNodeFromNodeID(new NodeID(InetAddress.getByName("10.0.0.1"), 123));
      node2 = Node.getNodeFromAddressAndPort(InetAddress.getByName("192.168.1.200"), 123);
      node3 = Node.getNodeFromNodeID(new NodeID(InetAddress.getByName("10.0.0.1"), 125));
    } catch (UnknownHostException | NotBoundException | RemoteException ex) {
      fail();
    }
    assertNotEquals(node1, node2);
    assertNotEquals(node1, node3);
    assertNotEquals(node2, node3);
  }

  //  compareTo(), hashCode() and equals() are not tested as they rely on NodeId methods which have
  //  been thoroughly tested
}
