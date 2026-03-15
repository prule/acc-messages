package io.github.prule.acc.messages;

import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EntryListCarTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(
      String hexString, Consumer<AccBroadcastingInbound.EntryListCar> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.ENTRY_LIST_CAR, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.EntryListCar);

    verifier.accept((AccBroadcastingInbound.EntryListCar) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
        Arguments.of(
            "060000010c00426c61636b2046616c636f6e04000000000002000104004c756361050053746f6c7a030053544f010200",
            expect(2, 3, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.EntryListCar> expect(
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
