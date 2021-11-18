package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.model.ClientID;
import java.rmi.Remote;
import java.util.List;

public interface StubInterface extends Remote {
  void token();
  void disconnecting(ClientID clientID);
  void request(ClientID clientID);
  List<ClientID> connecting(ClientID clientID);
  void dropped(ClientID clientID);
}
