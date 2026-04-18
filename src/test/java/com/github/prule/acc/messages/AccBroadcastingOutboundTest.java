package com.github.prule.acc.messages;

import static com.github.prule.acc.messages.Utils.hexStringToByteArray;
import static org.junit.jupiter.api.Assertions.*;

import io.kaitai.struct.ByteBufferKaitaiStream;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AccBroadcastingOutboundTest {

    @ParameterizedTest
    @MethodSource("provideOutboundMessages")
    void testOutboundMessages(
            String hexString, 
            AccBroadcastingOutbound.OutboundMsgType expectedType,
            Consumer<AccBroadcastingOutbound> verifier) {

        byte[] data = hexStringToByteArray(hexString);
        ByteBufferKaitaiStream stream = new ByteBufferKaitaiStream(data);
        AccBroadcastingOutbound packet = new AccBroadcastingOutbound(stream);

        assertEquals(expectedType, packet.msgType());
        verifier.accept(packet);
    }

    private static Stream<Arguments> provideOutboundMessages() {
        return Stream.of(
            // REGISTER_COMMAND_APPLICATION (01)
            // Protocol: 04, Name: "Test" (0400 54657374), Pass: "pw" (0200 7077), Interval: 1000ms (E8030000), CmdPass: "" (0000)
            Arguments.of(
                "01 04 0400 54657374 0200 7077 E8030000 0000",
                AccBroadcastingOutbound.OutboundMsgType.REGISTER_COMMAND_APPLICATION,
                (Consumer<AccBroadcastingOutbound>) p -> {
                    var body = (AccBroadcastingOutbound.RegisterCommandApplication) p.body();
                    assertEquals(4, body.protocolVersion());
                    assertEquals("Test", body.displayName().data());
                    assertEquals("pw", body.connectionPassword().data());
                    assertEquals(1000, body.msRealtimeUpdateInterval());
                    assertEquals("", body.commandPassword().data());
                }
            ),

            // UNREGISTER_COMMAND_APPLICATION (09)
            // ConnectionId: 123 (7B000000)
            Arguments.of(
                "09 7B000000",
                AccBroadcastingOutbound.OutboundMsgType.UNREGISTER_COMMAND_APPLICATION,
                (Consumer<AccBroadcastingOutbound>) p -> {
                    var body = (AccBroadcastingOutbound.UnregisterCommandApplication) p.body();
                    assertEquals(123, body.connectionId());
                }
            ),

            // REQUEST_ENTRY_LIST (0A -> 10)
            // ConnectionId: 456 (C8010000)
            Arguments.of(
                "0A C8010000",
                AccBroadcastingOutbound.OutboundMsgType.REQUEST_ENTRY_LIST,
                (Consumer<AccBroadcastingOutbound>) p -> {
                    var body = (AccBroadcastingOutbound.RequestEntryList) p.body();
                    assertEquals(456, body.connectionId());
                }
            ),

            // REQUEST_TRACK_DATA (0B -> 11)
            // ConnectionId: 789 (15030000)
            Arguments.of(
                "0B 15030000",
                AccBroadcastingOutbound.OutboundMsgType.REQUEST_TRACK_DATA,
                (Consumer<AccBroadcastingOutbound>) p -> {
                    var body = (AccBroadcastingOutbound.RequestTrackData) p.body();
                    assertEquals(789, body.connectionId());
                }
            ),

            // CHANGE_FOCUS (32 -> 50)
            // ConnectionId: 1 (01000000), Focus: 1, CarIdx: 12 (0C00), Cam: 1, Set: "Main" (0400 4D61696E), Cam: "Dash" (0400 44617368)
            Arguments.of(
                "32 01000000 01 0C00 01 0400 4D61696E 0400 44617368",
                AccBroadcastingOutbound.OutboundMsgType.CHANGE_FOCUS,
                (Consumer<AccBroadcastingOutbound>) p -> {
                    var body = (AccBroadcastingOutbound.ChangeFocus) p.body();
                    assertEquals(1, body.connectionId());
                    assertEquals(1, body.changeCarFocus());
                    assertEquals(12, body.carIndex());
                    assertEquals(1, body.changeCamera());
                    assertEquals("Main", body.cameraSet().data());
                    assertEquals("Dash", body.camera().data());
                }
            )
        );
    }
}
