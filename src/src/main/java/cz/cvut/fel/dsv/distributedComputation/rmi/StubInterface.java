package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.model.ClientID;
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
   * Client with given ID is disconnecting from network.
   *
   * @param clientID
   */
  void disconnecting(ClientID clientID);

  /**
   * Node with given ID is requesting the token.
   *
   * @param clientID ID of the client requesting the token.
   */
  void request(ClientID clientID);

  /**
   * New client with given ID is connecting to the network. Return him list of all known nodes.
   *
   * @param clientID ID of the newly connected client.
   * @return List of all known clients.
   */
  List<ClientID> connecting(ClientID clientID);

  /**
   * Information that connection to some node dropped. This client must be removed from the network.
   *
   * @param clientID ID of the dropped client.
   */
  void dropped(ClientID clientID);
}
