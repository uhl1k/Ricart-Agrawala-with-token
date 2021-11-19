package cz.cvut.fel.dsv.distributedComputation.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NodeIDTest {

  //  Getters and setters are not being tested due to their simplicity.

  @Test
  void compareTo_compareIPv4Addresses() {
    NodeID nodeId1 = null;
    NodeID nodeId2 = null;
    NodeID nodeId3 = null;
    NodeID nodeId4 = null;
    try{
      nodeId1 = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
      nodeId2 = new NodeID(InetAddress.getByName("10.0.0.1"), 124);
      nodeId3 = new NodeID(InetAddress.getByName("192.168.1.158"), 123);
      nodeId4 = new NodeID(InetAddress.getByName("87.65.97.14"), 874);
    } catch (UnknownHostException ex) {
      fail();
    }

    assertTrue(nodeId1.compareTo(nodeId2) < 0);
    assertTrue(nodeId2.compareTo(nodeId1) > 0);
    assertTrue(nodeId1.compareTo(nodeId3) < 0);
    assertTrue(nodeId1.compareTo(nodeId4) < 0);
    assertTrue(nodeId3.compareTo(nodeId2) > 0);
  }

  @Test
  void compareTo_compareIPv6Addresses() {
    NodeID nodeId1 = null;
    NodeID nodeId2 = null;
    NodeID nodeId3 = null;
    NodeID nodeId4 = null;
    try{
      nodeId1 = new NodeID(InetAddress.getByName("::1"), 123);
      nodeId2 = new NodeID(InetAddress.getByName("::1"), 124);
      nodeId3 = new NodeID(InetAddress.getByName("1234::abcd"), 123);
      nodeId4 = new NodeID(InetAddress.getByName("8745:5477:adfe::2020"), 874);
    } catch (UnknownHostException ex) {
      fail();
    }

    assertTrue(nodeId1.compareTo(nodeId2) < 0);
    assertTrue(nodeId2.compareTo(nodeId1) > 0);
    assertTrue(nodeId1.compareTo(nodeId3) < 0);
    assertTrue(nodeId1.compareTo(nodeId4) < 0);
    assertTrue(nodeId3.compareTo(nodeId2) > 0);
  }

  @Test
  void compareTo_compareIPv4AndIPv6Addresses() {
    NodeID nodeId1 = null;
    NodeID nodeId2 = null;
    try{
      nodeId1 = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
      nodeId2 = new NodeID(InetAddress.getByName("1234:abcd::2020"), 123);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertTrue(nodeId1.compareTo(nodeId2) < 0);
    assertTrue(nodeId2.compareTo(nodeId1) > 0);
  }

  @ParameterizedTest
  @CsvSource({
      "10.0.0.1,125,10.0.0.1:125",
      "192.168.45.14,1,192.168.45.14:1",
      "192.168.44.57,54321,192.168.44.57:54321",
      "255.255.255.255,65535,255.255.255.255:65535",
      "::1,320,[0:0:0:0:0:0:0:1]:320",
      "1234:5ad7::2001:2020,457,[1234:5ad7:0:0:0:0:2001:2020]:457"
  })
  void toString_shouldReturnIpAndPort(String ip, int port, String result) {
    NodeID nodeId = null;
    try{
      nodeId = new NodeID(InetAddress.getByName(ip), port);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertEquals(result, nodeId.toString());
  }

  @Test
  void equals_notSameClassOrNull_shouldReturnFalse() {
    NodeID nodeId = null;
    try{
      nodeId = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertFalse(nodeId.equals(null));
    assertFalse(nodeId.equals("test"));
    assertFalse(nodeId.equals(5));
  }

  @Test
  void equals_sameInstance_shouldReturnTrue() {
    NodeID nodeId = null;
    try{
      nodeId = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertTrue(nodeId.equals(nodeId));
  }

  @Test
  void equals_differentInstanceButSameContent_shouldReturnTrue() {
    NodeID nodeId1 = null;
    NodeID nodeId2 = null;
    try{
      nodeId1 = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
      nodeId2 = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertTrue(nodeId1.equals(nodeId2));
  }

  @Test
  void equals_differentContent_shouldReturnFalse() {
    NodeID nodeId1 = null;
    NodeID nodeId2 = null;
    NodeID nodeId3 = null;
    NodeID nodeId4 = null;
    try{
      nodeId1 = new NodeID(InetAddress.getByName("10.0.0.1"), 123);
      nodeId2 = new NodeID(InetAddress.getByName("10.0.0.1"), 124);
      nodeId3 = new NodeID(InetAddress.getByName("192.168.1.158"), 123);
      nodeId4 = new NodeID(InetAddress.getByName("87.65.97.14"), 874);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertFalse(nodeId1.equals(nodeId2));
    assertFalse(nodeId1.equals(nodeId3));
    assertFalse(nodeId1.equals(nodeId4));
    assertFalse(nodeId2.equals(nodeId3));
    assertFalse(nodeId2.equals(nodeId4));
    assertFalse(nodeId3.equals(nodeId4));
  }

  @ParameterizedTest
  @CsvSource({
      "10.0.0.1,125,136",
      "192.168.45.14,1,420",
      "192.168.44.57,54321,54782",
      "255.255.255.255,65535,66555",
      "::1,320,321",
      "1234:5ad7::2001:2020,457,929"
  })
  void hashCode_shouldReturnSumOfBytesAndPort(String ip, int port, int hash) {
    NodeID nodeId = null;
    try{
      nodeId = new NodeID(InetAddress.getByName(ip), port);
    } catch (UnknownHostException ex) {
      fail();
    }
    assertEquals(hash, nodeId.hashCode(), ip + ":" + port + " should give " + hash + " hash code.");
  }
}