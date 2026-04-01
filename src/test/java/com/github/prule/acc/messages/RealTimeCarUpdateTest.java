package com.github.prule.acc.messages;

import static com.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RealTimeCarUpdateTest {

  @ParameterizedTest
  @MethodSource("provideResults")
  void testResult(
      String hexString, Consumer<AccBroadcastingInbound.RealtimeCarUpdate> verifier) {

    byte[] data = hexStringToByteArray(hexString);
    ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
    AccBroadcastingInbound packet = new AccBroadcastingInbound(stream);

    assertEquals(AccBroadcastingInbound.InboundMsgType.REALTIME_CAR_UPDATE, packet.msgType());
    assertTrue(packet.body() instanceof AccBroadcastingInbound.RealtimeCarUpdate);

    verifier.accept((AccBroadcastingInbound.RealtimeCarUpdate) packet.body());
  }

  private static Stream<Arguments> provideResults() {
    return Stream.of(
            Arguments.of(
                    "030000000001031948d6c2675fb34317642240017800010001000000f623f33d0c00fbffffffff6d01000000000003fe5b0000c8a0000007710000000100004070010000000000035f5c000054a100008d7200000001000032280000000000000000010000",
                    expect(
                            0,
                            0,
                            1,
                            (byte) 3,
                            AccBroadcastingInbound.CarLocation.TRACK,
                            120,
                            1,
                            1,
                            0,
                            12,
                            -5,
                            93695,
                            94272,
                            10290)));
  }

  private static Consumer<AccBroadcastingInbound.RealtimeCarUpdate> expect(
          int carIndex,
          int driverIndex,
          int driverCount,
          byte gear,
          AccBroadcastingInbound.CarLocation carLocation,
          int kmh,
          int position,
          int cupPosition,
          int trackPosition,
          int laps,
          int delta,
          int bestLapTimeMs,
          int lastLapTimeMs,
          int currentLapTimeMs) {
    return result -> {
      assertEquals(carIndex, result.carIndex());
      assertEquals(driverIndex, result.driverIndex());
      assertEquals(driverCount, result.driverCount());
      assertEquals(gear, result.gear());
      assertEquals(carLocation, result.carLocation());
      assertEquals(kmh, result.kmh());
      assertEquals(position, result.position());
      assertEquals(cupPosition, result.cupPosition());
      assertEquals(trackPosition, result.trackPosition());
      assertEquals(laps, result.laps());
      assertEquals(delta, result.delta());

      assertEquals(bestLapTimeMs, result.bestSessionLap().lapTimeMs());
      assertEquals(lastLapTimeMs, result.lastLap().lapTimeMs());
      assertEquals(currentLapTimeMs, result.currentLap().lapTimeMs());
    };
  }
}
