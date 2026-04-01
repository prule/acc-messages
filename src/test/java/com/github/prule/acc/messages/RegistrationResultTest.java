package com.github.prule.acc.messages;

import static com.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegistrationResultTest {

  @ParameterizedTest
  @MethodSource("provideRegistrationResults")
  void testRegistrationResult(
      String hexString, Consumer<AccBroadcastingInbound.RegistrationResult> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.REGISTRATION_RESULT, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.RegistrationResult);

    verifier.accept((AccBroadcastingInbound.RegistrationResult) packet.body());
  }

  private static Stream<Arguments> provideRegistrationResults() {
    /*
      message type id (byte) then...

      - id: connection_id
        type: s4
      - id: connection_success
        type: u1
      - id: is_read_only
        type: u1
      - id: error_message
        type: acc_string
     */
    return Stream.of(
            // here, spaces are included to make it readable, these are removed before parsing
        Arguments.of("01 05000000 01 0000 000000", expect(5, 1, 0, "")),
        Arguments.of("01 02000000 03 04 0400 45525252", expect(2, 3, 4, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.RegistrationResult> expect(
      int connection_id, int success, int readOnly, String error) {
    return result -> {
      assertEquals(connection_id, result.connectionId());
      assertEquals(success, result.connectionSuccess());
      assertEquals(readOnly, result.isReadOnly());
      assertEquals(error, result.errorMessage().data());
    };
  }

}
