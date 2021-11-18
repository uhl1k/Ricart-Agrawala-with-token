package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.model.NodeID;
import java.util.List;

public class StubImplementation implements StubInterface {
  @Override
  public void token() {}

  @Override
  public void disconnecting(NodeID nodeID) {}

  @Override
  public void request(NodeID nodeID) {}

  @Override
  public List<NodeID> connecting(NodeID nodeID) {
    return null;
  }

  @Override
  public void dropped(NodeID nodeID) {}
}
