package io.github.prule.acc.messages;

import static io.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TrackDataTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(
      String hexString, Consumer<AccBroadcastingInbound.TrackData> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.TRACK_DATA, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.TrackData);

    verifier.accept((AccBroadcastingInbound.TrackData) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
        Arguments.of(
            "05030000000d005265642042756c6c2052696e671a000000de1000000708004472697661626c650705004368617365080046617243686173650600426f6e6e657407004461736850726f0700436f636b706974040044617368060048656c6d6574070048656c6963616d01070048656c6963616d07004f6e626f6172640408004f6e626f6172643008004f6e626f6172643108004f6e626f6172643208004f6e626f6172643307007069746c616e65020c007069746c616e655f43616d310c007069746c616e655f43616d320400736574310d0900536574315f43616d330900536574315f43616d340900536574315f43616d350900536574315f43616d360900536574315f43616d370900536574315f43616d390a00536574315f43616d31300a00536574315f43616d31310a00536574315f43616d31320a00536574315f43616d31330d00536574315f43616d31345f31340900536574315f43616d310900536574315f43616d320400736574320a0900536574325f43616d330900536574325f43616d340900536574325f43616d350900536574325f43616d360900536574325f43616d370900536574325f43616d380900536574325f43616d390a00536574325f43616d31300900536574325f43616d310900536574325f43616d32050073657456520b090043616d657261565231090043616d657261565232090043616d657261565233090043616d657261565234090043616d657261565235090043616d657261565236090043616d657261565237090043616d657261565238090043616d6572615652390a0043616d657261565231300a0043616d65726156523131060500426c616e6b0900426173696320485544040048656c70090054696d655461626c650c0042726f616463617374696e670800547261636b4d6170",
            expect(2, 3, "ERRR")));
  }

  private static Consumer<AccBroadcastingInbound.TrackData> expect(
      int success, int readOnly, String error) {
    return result -> {
      System.out.println(result);
    };
  }

}
