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
                    "02000000000a0504988b49f00bae480d00000008004472697661626c650700436f636b706974090042617369632048554400ba9657471e26000000d96b01000500000003d15b0000bc9f00004b70000000010000",
                    expect(
                            0,
                            0,
                            null,
                            AccBroadcastingInbound.SessionPhase.SESSION,
                            "Drivable",
                            "Cockpit",
                            "Basic HUD",
                            0,
                            30,
                            38,
                            0,
                            0,
                            0,
                            93145,
                            5,
                            0,
                            3)));
  }

  private static Consumer<AccBroadcastingInbound.RealtimeUpdate> expect(
          int eventIndex,
          int sessionIndex,
          AccBroadcastingInbound.SessionType sessionType,
          AccBroadcastingInbound.SessionPhase phase,
          String activeCameraSet,
          String activeCamera,
          String currentHudPage,
          int isReplayPlaying,
          int ambientTemp,
          int trackTemp,
          int clouds,
          int rainLevel,
          int wetness,
          int bestLapTimeMs,
          int bestLapCarIndex,
          int bestLapDriverIndex,
          int bestLapNumSplits) {
    return result -> {
      assertEquals(eventIndex, result.eventIndex());
      assertEquals(sessionIndex, result.sessionIndex());
      assertEquals(sessionType, result.sessionType());
      assertEquals(phase, result.phase());
      assertEquals(activeCameraSet, result.activeCameraSet().data());
      assertEquals(activeCamera, result.activeCamera().data());
      assertEquals(currentHudPage, result.currentHudPage().data());
      assertEquals(isReplayPlaying, result.isReplayPlaying());
      assertNull(result.replaySessionTime());
      assertNull(result.replayRemainingTime());
      assertEquals(ambientTemp, result.ambientTemp());
      assertEquals(trackTemp, result.trackTemp());
      assertEquals(clouds, result.clouds());
      assertEquals(rainLevel, result.rainLevel());
      assertEquals(wetness, result.wetness());

      var lap = result.bestSessionLap();
      assertEquals(bestLapTimeMs, lap.lapTimeMs());
      assertEquals(bestLapCarIndex, lap.carIndex());
      assertEquals(bestLapDriverIndex, lap.driverIndex());
      assertEquals(bestLapNumSplits, lap.numSplits());
    };
  }

}
