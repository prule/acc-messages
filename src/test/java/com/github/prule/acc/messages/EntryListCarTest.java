package com.github.prule.acc.messages;

import static com.github.prule.acc.messages.Utils.hexStringToByteArray;
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
                    expect(
                            0,
                            1,
                            "Black Falcon",
                            4,
                            AccBroadcastingInbound.CupCategory.OVERALL_PRO,
                            0,
                            2,
                            1,
                            "Luca",
                            "Stolz",
                            "STO",
                            AccBroadcastingInbound.DriverCategory.SILVER,
                            2)));
  }

  private static Consumer<AccBroadcastingInbound.EntryListCar> expect(
          int carId,
          int carModelType,
          String teamName,
          int raceNumber,
          AccBroadcastingInbound.CupCategory cupCategory,
          int driverIndex,
          int nationality,
          int numDrivers,
          String firstName,
          String lastName,
          String shortName,
          AccBroadcastingInbound.DriverCategory driverCategory,
          int driverNationality) {
    return result -> {
      assertEquals(carId, result.carId());
      assertEquals(carModelType, result.carModelType());
      assertEquals(teamName, result.teamName().data());
      assertEquals(raceNumber, result.raceNumber());
      assertEquals(cupCategory, result.cupCategory());
      assertEquals(driverIndex, result.driverIndex());
      assertEquals(nationality, result.nationality());
      assertEquals(numDrivers, result.numDrivers());

      assertEquals(1, result.drivers().size());
      var driver = result.drivers().getFirst();
      assertEquals(firstName, driver.firstName().data());
      assertEquals(lastName, driver.lastName().data());
      assertEquals(shortName, driver.shortName().data());
      assertEquals(driverCategory, driver.category());
      assertEquals(driverNationality, driver.nationality());
    };
  }

}
