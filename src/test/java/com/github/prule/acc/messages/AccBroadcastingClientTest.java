package com.github.prule.acc.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.kaitai.struct.ByteBufferKaitaiStream;
import org.junit.jupiter.api.Test;

class AccBroadcastingClientTest {

  private final AccBroadcastingClient client = new AccBroadcastingClient();

  @Test
  void testBuildRegisterCommandApplication() {
    String name = "TestClient";
    String connPw = "abc";
    int interval = 500;
    String cmdPw = "def";

    byte[] result = client.buildRegisterCommandApplication(name, connPw, interval, cmdPw);

    // Verify by parsing with the Kaitai-generated class
    AccBroadcastingOutbound packet =
        new AccBroadcastingOutbound(new ByteBufferKaitaiStream(result));
    assertEquals(
        AccBroadcastingOutbound.OutboundMsgType.REGISTER_COMMAND_APPLICATION, packet.msgType());

    var body = (AccBroadcastingOutbound.RegisterCommandApplication) packet.body();
    assertEquals(name, body.displayName().data());
    assertEquals(connPw, body.connectionPassword().data());
    assertEquals(interval, body.msRealtimeUpdateInterval());
    assertEquals(cmdPw, body.commandPassword().data());
  }

  @Test
  void testBuildRequestEntryList() {
    int connectionId = 1234;
    byte[] result = client.buildRequestEntryList(connectionId);

    AccBroadcastingOutbound packet =
        new AccBroadcastingOutbound(new ByteBufferKaitaiStream(result));
    assertEquals(AccBroadcastingOutbound.OutboundMsgType.REQUEST_ENTRY_LIST, packet.msgType());

    var body = (AccBroadcastingOutbound.RequestEntryList) packet.body();
    assertEquals(connectionId, body.connectionId());
  }

  @Test
  void testBuildRequestTrackData() {
    int connectionId = 5678;
    byte[] result = client.buildRequestTrackData(connectionId);

    AccBroadcastingOutbound packet =
        new AccBroadcastingOutbound(new ByteBufferKaitaiStream(result));
    assertEquals(AccBroadcastingOutbound.OutboundMsgType.REQUEST_TRACK_DATA, packet.msgType());

    var body = (AccBroadcastingOutbound.RequestTrackData) packet.body();
    assertEquals(connectionId, body.connectionId());
  }

  @Test
  void testBuildUnregisterCommandApplication() {
    int connectionId = 9012;
    byte[] result = client.buildUnregisterCommandApplication(connectionId);

    AccBroadcastingOutbound packet =
        new AccBroadcastingOutbound(new ByteBufferKaitaiStream(result));
    assertEquals(
        AccBroadcastingOutbound.OutboundMsgType.UNREGISTER_COMMAND_APPLICATION, packet.msgType());

    var body = (AccBroadcastingOutbound.UnregisterCommandApplication) packet.body();
    assertEquals(connectionId, body.connectionId());
  }

  @Test
  void testBuildChangeFocus() {
    int connectionId = 123;
    boolean changeCarFocus = true;
    int carIndex = 42;
    boolean changeCamera = true;
    String cameraSet = "VR";
    String camera = "Onboard";

    byte[] result =
        client.buildChangeFocus(
            connectionId, changeCarFocus, carIndex, changeCamera, cameraSet, camera);

    AccBroadcastingOutbound packet =
        new AccBroadcastingOutbound(new ByteBufferKaitaiStream(result));
    assertEquals(AccBroadcastingOutbound.OutboundMsgType.CHANGE_FOCUS, packet.msgType());

    var body = (AccBroadcastingOutbound.ChangeFocus) packet.body();
    assertEquals(connectionId, body.connectionId());
    assertEquals(1, body.changeCarFocus());
    assertEquals(carIndex, body.carIndex());
    assertEquals(1, body.changeCamera());
    assertEquals(cameraSet, body.cameraSet().data());
    assertEquals(camera, body.camera().data());
  }
}
