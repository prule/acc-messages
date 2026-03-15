package io.github.prule.acc.messages;

import static io.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RealTimeUpdateTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(
      String hexString, Consumer<AccBroadcastingInbound.RealtimeUpdate> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.REALTIME_UPDATE, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.RealtimeUpdate);

    verifier.accept((AccBroadcastingInbound.RealtimeUpdate) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
        Arguments.of(
            "02000000000a0200000000001bb7490d0000000400736574310900536574315f43616d3109004261736963204855440003f052471e27000000ffffff7f0000000003ffffff7fffffff7fffffff7f00010000",
            expect(2, 3, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.RealtimeUpdate> expect(
      int success, int readOnly, String error) {
    return result -> {
      System.out.println(result);
    };
  }

}
