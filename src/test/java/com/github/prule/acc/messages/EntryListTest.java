package com.github.prule.acc.messages;

import static com.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EntryListTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(String hexString, Consumer<AccBroadcastingInbound.EntryList> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.ENTRY_LIST, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.EntryList);

    verifier.accept((AccBroadcastingInbound.EntryList) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
        Arguments.of(
            "04020000001900000001000400020005000700060009000d00030008000a000c000f000e001100100013000b00120017001800150014001600",
            expect(
                2, 25, 0, 1, 4, 2, 5, 7, 6, 9, 13, 3, 8, 10, 12, 15, 14, 17, 16, 19, 11, 18, 23, 24,
                21, 20, 22)));
  }

  private static Consumer<AccBroadcastingInbound.EntryList> expect(
      int connectionId, int numCarIndexes, Integer... carIndexes) {
    return result -> {
      assertEquals(connectionId, result.connectionId());
      assertEquals(numCarIndexes, result.numCarIndexes());
      assertEquals(numCarIndexes, result.carIndexes().size());
      assertIterableEquals(java.util.List.of(carIndexes), result.carIndexes());
    };
  }
}
