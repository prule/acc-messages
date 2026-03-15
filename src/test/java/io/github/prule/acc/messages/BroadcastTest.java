package io.github.prule.acc.messages;

import static io.github.prule.acc.messages.Utils.hexStringToByteArray;
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
                    expect(
                            AccBroadcastingInbound.BroadcastType.LAPCOMPLETED,
                            "01:33.695",
                            904977,
                            0)));
  }

  private static Consumer<AccBroadcastingInbound.BroadcastingEvent> expect(
          AccBroadcastingInbound.BroadcastType type, String msg, int timeMs, int carId) {
    return result -> {
      assertEquals(type, result.type());
      assertEquals(msg, result.msg().data());
      assertEquals(timeMs, result.timeMs());
      assertEquals(carId, result.carId());
    };
  }


}
