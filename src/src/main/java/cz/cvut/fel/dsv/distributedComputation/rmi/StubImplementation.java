package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.model.ClientID;
import java.util.List;

public class StubImplementation implements StubInterface {
  @Override
  public void token() {}

  @Override
  public void disconnecting(ClientID clientID) {}

  @Override
  public void request(ClientID clientID) {}

  @Override
  public List<ClientID> connecting(ClientID clientID) {
    return null;
  }

  @Override
  public void dropped(ClientID clientID) {}
}
