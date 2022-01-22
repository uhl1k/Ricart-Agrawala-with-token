package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.model.NodeID;
import java.rmi.Remote;
import java.util.List;

/**
 * Definition of the interface for RMI.
 *
 * @author Roman Janků (jankurom@fel.cvut.cz)
 */
public interface StubInterface extends Remote {
  /**
   * Used to pass the token to a target node. Calling node must release the token and called node
   * must accept the token.
   */
  void token();

  /**
   * Node with given ID is disconnecting from network.
   *
   * @param nodeID
   */
  void disconnecting(NodeID nodeID);

  /**
   * Node with given ID is requesting the token.
   *
   * @param nodeID ID of the client requesting the token.
   */
  void request(NodeID nodeID);

  /**
   * New client with given ID is connecting to the network. Return him list of all known nodes.
   *
   * @param nodeID ID of the newly connected client.
   * @return List of all known clients.
   */
  List<NodeID> connecting(NodeID nodeID);

  /**
   * Information that connection to some node dropped. This client must be removed from the network.
   *
   * @param nodeID ID of the dropped client.
   */
  void dropped(NodeID nodeID);
}
