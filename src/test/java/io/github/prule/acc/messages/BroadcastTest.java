package io.github.prule.acc.messages;

import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BroadcastTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(
      String hexString, Consumer<AccBroadcastingInbound.BroadcastingEvent> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.BROADCASTING_EVENT, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.BroadcastingEvent);

    verifier.accept((AccBroadcastingInbound.BroadcastingEvent) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
        Arguments.of(
            "0705090030313a33332e36393511cf0d0000000000",
            expect(2, 3, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.BroadcastingEvent> expect(
      int success, int readOnly, String error) {
    return result -> {
      System.out.println(result);
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
