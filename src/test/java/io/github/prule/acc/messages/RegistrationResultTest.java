package io.github.prule.acc.messages;

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
    return Stream.of(
        // Success=1, ReadOnly=0, Error=""
        Arguments.of("0101000000", expect(1, 0, "")),
        Arguments.of("010203040045525252", expect(2, 3, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.RegistrationResult> expect(
      int success, int readOnly, String error) {
    return result -> {
      assertEquals(success, result.connectionSuccess());
      assertEquals(readOnly, result.isReadOnly());
      assertEquals(error, result.errorMessage().data());
    };
  }

  private static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      int digit1 = Character.digit(s.charAt(i), 16);
      int digit2 = Character.digit(s.charAt(i + 1), 16);
      data[i / 2] = (byte) ((digit1 << 4) + digit2);
    }
    return data;
  }
}
