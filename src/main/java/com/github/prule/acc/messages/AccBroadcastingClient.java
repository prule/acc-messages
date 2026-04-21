package com.github.prule.acc.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * Builds byte arrays representing messages we might send. Since Kaitai Struct doesn't support
 * writing we need to do this manually.
 */
public class AccBroadcastingClient {

  private static final int PROTOCOL_VERSION = 4;
  private static final byte OP_REGISTER_COMMAND_APPLICATION = 1;
  private static final byte OP_UNREGISTER_COMMAND_APPLICATION = 9;
  private static final byte OP_REQUEST_ENTRY_LIST = 10;
  private static final byte OP_REQUEST_TRACK_DATA = 11;
  private static final byte OP_CHANGE_FOCUS = 50;

  /** Constructs the payload for a REGISTER_COMMAND_APPLICATION message. */
  public byte[] buildRegisterCommandApplication(
      String displayName, String connectionPassword, int updateIntervalMs, String commandPassword) {
    byte[] displayNameBytes = displayName.getBytes(StandardCharsets.UTF_8);
    byte[] connPwdBytes = connectionPassword.getBytes(StandardCharsets.UTF_8);
    byte[] cmdPwdBytes = commandPassword.getBytes(StandardCharsets.UTF_8);

    // Calculate total size:
    // 1 byte (MsgType) + 1 byte (ProtocolVersion)
    // + (2 bytes len + N bytes data) for DisplayName
    // + (2 bytes len + N bytes data) for ConnectionPassword
    // + 4 bytes (UpdateInterval)
    // + (2 bytes len + N bytes data) for CommandPassword
    int size =
        1 + 1 + 2 + displayNameBytes.length + 2 + connPwdBytes.length + 4 + 2 + cmdPwdBytes.length;

    ByteBuffer buffer = ByteBuffer.allocate(size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);

    buffer.put(OP_REGISTER_COMMAND_APPLICATION);
    buffer.put((byte) PROTOCOL_VERSION);

    writeAccString(buffer, displayNameBytes);
    writeAccString(buffer, connPwdBytes);

    buffer.putInt(updateIntervalMs);

    writeAccString(buffer, cmdPwdBytes);

    return buffer.array();
  }

  /** Constructs the payload for an UNREGISTER_COMMAND_APPLICATION message. */
  public byte[] buildUnregisterCommandApplication(int connectionId) {
    ByteBuffer buffer = ByteBuffer.allocate(1 + 4);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.put(OP_UNREGISTER_COMMAND_APPLICATION);
    buffer.putInt(connectionId);
    return buffer.array();
  }

  /** Constructs the payload for a REQUEST_ENTRY_LIST message. */
  public byte[] buildRequestEntryList(int connectionId) {
    ByteBuffer buffer = ByteBuffer.allocate(1 + 4);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.put(OP_REQUEST_ENTRY_LIST);
    buffer.putInt(connectionId);
    return buffer.array();
  }

  /** Constructs the payload for a REQUEST_TRACK_DATA message. */
  public byte[] buildRequestTrackData(int connectionId) {
    ByteBuffer buffer = ByteBuffer.allocate(1 + 4);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.put(OP_REQUEST_TRACK_DATA);
    buffer.putInt(connectionId);
    return buffer.array();
  }

  /** Constructs the payload for a CHANGE_FOCUS message. */
  public byte[] buildChangeFocus(
      int connectionId,
      boolean changeCarFocus,
      int carIndex,
      boolean changeCamera,
      String cameraSet,
      String camera) {
    byte[] cameraSetBytes = cameraSet.getBytes(StandardCharsets.UTF_8);
    byte[] cameraBytes = camera.getBytes(StandardCharsets.UTF_8);

    // Calculate total size:
    // 1 byte (MsgType) + 4 bytes (ConnectionId)
    // + 1 byte (changeCarFocus) + 2 bytes (carIndex)
    // + 1 byte (changeCamera)
    // + (2 bytes len + N bytes data) for cameraSet
    // + (2 bytes len + N bytes data) for camera
    int size = 1 + 4 + 1 + 2 + 1 + 2 + cameraSetBytes.length + 2 + cameraBytes.length;

    ByteBuffer buffer = ByteBuffer.allocate(size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);

    buffer.put(OP_CHANGE_FOCUS);
    buffer.putInt(connectionId);
    buffer.put((byte) (changeCarFocus ? 1 : 0));
    buffer.putShort((short) carIndex);
    buffer.put((byte) (changeCamera ? 1 : 0));
    writeAccString(buffer, cameraSetBytes);
    writeAccString(buffer, cameraBytes);

    return buffer.array();
  }

  private void writeAccString(ByteBuffer buffer, byte[] bytes) {
    // ACC strings are prefixed with a 16-bit length
    buffer.putShort((short) bytes.length);
    buffer.put(bytes);
  }

  /** Helper to send the data over a DatagramSocket. */
  public void send(DatagramSocket socket, InetAddress address, int port, byte[] data)
      throws IOException {
    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
    socket.send(packet);
  }
}
