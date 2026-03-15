// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package io.github.prule.acc.messages;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.nio.charset.StandardCharsets;


/**
 * Outbound messages (app → ACC).
 * Reference: Kunos Simulazioni ksBroadcastingNetwork SDK
 */
public class AccBroadcastingOutbound extends KaitaiStruct {
    public static AccBroadcastingOutbound fromFile(String fileName) throws IOException {
        return new AccBroadcastingOutbound(new ByteBufferKaitaiStream(fileName));
    }

    public enum OutboundMsgType {
        REGISTER_COMMAND_APPLICATION(1),
        UNREGISTER_COMMAND_APPLICATION(9),
        REQUEST_ENTRY_LIST(10),
        REQUEST_TRACK_DATA(11),
        CHANGE_FOCUS(50);

        private final long id;
        OutboundMsgType(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, OutboundMsgType> byId = new HashMap<Long, OutboundMsgType>(5);
        static {
            for (OutboundMsgType e : OutboundMsgType.values())
                byId.put(e.id(), e);
        }
        public static OutboundMsgType byId(long id) { return byId.get(id); }
    }

    public AccBroadcastingOutbound(KaitaiStream _io) {
        this(_io, null, null);
    }

    public AccBroadcastingOutbound(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public AccBroadcastingOutbound(KaitaiStream _io, KaitaiStruct _parent, AccBroadcastingOutbound _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.msgType = OutboundMsgType.byId(this._io.readU1());
        {
            OutboundMsgType on = msgType();
            if (on != null) {
                switch (msgType()) {
                case CHANGE_FOCUS: {
                    this.body = new ChangeFocus(this._io, this, _root);
                    break;
                }
                case REGISTER_COMMAND_APPLICATION: {
                    this.body = new RegisterCommandApplication(this._io, this, _root);
                    break;
                }
                case REQUEST_ENTRY_LIST: {
                    this.body = new RequestEntryList(this._io, this, _root);
                    break;
                }
                case REQUEST_TRACK_DATA: {
                    this.body = new RequestTrackData(this._io, this, _root);
                    break;
                }
                case UNREGISTER_COMMAND_APPLICATION: {
                    this.body = new UnregisterCommandApplication(this._io, this, _root);
                    break;
                }
                }
            }
        }
    }

    public void _fetchInstances() {
        {
            OutboundMsgType on = msgType();
            if (on != null) {
                switch (msgType()) {
                case CHANGE_FOCUS: {
                    ((ChangeFocus) (this.body))._fetchInstances();
                    break;
                }
                case REGISTER_COMMAND_APPLICATION: {
                    ((RegisterCommandApplication) (this.body))._fetchInstances();
                    break;
                }
                case REQUEST_ENTRY_LIST: {
                    ((RequestEntryList) (this.body))._fetchInstances();
                    break;
                }
                case REQUEST_TRACK_DATA: {
                    ((RequestTrackData) (this.body))._fetchInstances();
                    break;
                }
                case UNREGISTER_COMMAND_APPLICATION: {
                    ((UnregisterCommandApplication) (this.body))._fetchInstances();
                    break;
                }
                }
            }
        }
    }
    public static class AccString extends KaitaiStruct {
        public static AccString fromFile(String fileName) throws IOException {
            return new AccString(new ByteBufferKaitaiStream(fileName));
        }

        public AccString(KaitaiStream _io) {
            this(_io, null, null);
        }

        public AccString(KaitaiStream _io, KaitaiStruct _parent) {
            this(_io, _parent, null);
        }

        public AccString(KaitaiStream _io, KaitaiStruct _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.length = this._io.readU2le();
            this.data = new String(this._io.readBytes(length()), StandardCharsets.UTF_8);
        }

        public void _fetchInstances() {
        }
        private int length;
        private String data;
        private AccBroadcastingOutbound _root;
        private KaitaiStruct _parent;
        public int length() { return length; }
        public String data() { return data; }
        public AccBroadcastingOutbound _root() { return _root; }
        public KaitaiStruct _parent() { return _parent; }
    }
    public static class ChangeFocus extends KaitaiStruct {
        public static ChangeFocus fromFile(String fileName) throws IOException {
            return new ChangeFocus(new ByteBufferKaitaiStream(fileName));
        }

        public ChangeFocus(KaitaiStream _io) {
            this(_io, null, null);
        }

        public ChangeFocus(KaitaiStream _io, AccBroadcastingOutbound _parent) {
            this(_io, _parent, null);
        }

        public ChangeFocus(KaitaiStream _io, AccBroadcastingOutbound _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
            this.changeCarFocus = this._io.readU1();
            this.carIndex = this._io.readU2le();
            this.changeCamera = this._io.readU1();
            this.cameraSet = new AccString(this._io, this, _root);
            this.camera = new AccString(this._io, this, _root);
        }

        public void _fetchInstances() {
            this.cameraSet._fetchInstances();
            this.camera._fetchInstances();
        }
        private int connectionId;
        private int changeCarFocus;
        private int carIndex;
        private int changeCamera;
        private AccString cameraSet;
        private AccString camera;
        private AccBroadcastingOutbound _root;
        private AccBroadcastingOutbound _parent;
        public int connectionId() { return connectionId; }
        public int changeCarFocus() { return changeCarFocus; }
        public int carIndex() { return carIndex; }
        public int changeCamera() { return changeCamera; }
        public AccString cameraSet() { return cameraSet; }
        public AccString camera() { return camera; }
        public AccBroadcastingOutbound _root() { return _root; }
        public AccBroadcastingOutbound _parent() { return _parent; }
    }
    public static class RegisterCommandApplication extends KaitaiStruct {
        public static RegisterCommandApplication fromFile(String fileName) throws IOException {
            return new RegisterCommandApplication(new ByteBufferKaitaiStream(fileName));
        }

        public RegisterCommandApplication(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RegisterCommandApplication(KaitaiStream _io, AccBroadcastingOutbound _parent) {
            this(_io, _parent, null);
        }

        public RegisterCommandApplication(KaitaiStream _io, AccBroadcastingOutbound _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.protocolVersion = this._io.readU1();
            this.displayName = new AccString(this._io, this, _root);
            this.connectionPassword = new AccString(this._io, this, _root);
            this.msRealtimeUpdateInterval = this._io.readS4le();
            this.commandPassword = new AccString(this._io, this, _root);
        }

        public void _fetchInstances() {
            this.displayName._fetchInstances();
            this.connectionPassword._fetchInstances();
            this.commandPassword._fetchInstances();
        }
        private int protocolVersion;
        private AccString displayName;
        private AccString connectionPassword;
        private int msRealtimeUpdateInterval;
        private AccString commandPassword;
        private AccBroadcastingOutbound _root;
        private AccBroadcastingOutbound _parent;
        public int protocolVersion() { return protocolVersion; }
        public AccString displayName() { return displayName; }
        public AccString connectionPassword() { return connectionPassword; }
        public int msRealtimeUpdateInterval() { return msRealtimeUpdateInterval; }
        public AccString commandPassword() { return commandPassword; }
        public AccBroadcastingOutbound _root() { return _root; }
        public AccBroadcastingOutbound _parent() { return _parent; }
    }
    public static class RequestEntryList extends KaitaiStruct {
        public static RequestEntryList fromFile(String fileName) throws IOException {
            return new RequestEntryList(new ByteBufferKaitaiStream(fileName));
        }

        public RequestEntryList(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RequestEntryList(KaitaiStream _io, AccBroadcastingOutbound _parent) {
            this(_io, _parent, null);
        }

        public RequestEntryList(KaitaiStream _io, AccBroadcastingOutbound _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
        }

        public void _fetchInstances() {
        }
        private int connectionId;
        private AccBroadcastingOutbound _root;
        private AccBroadcastingOutbound _parent;
        public int connectionId() { return connectionId; }
        public AccBroadcastingOutbound _root() { return _root; }
        public AccBroadcastingOutbound _parent() { return _parent; }
    }
    public static class RequestTrackData extends KaitaiStruct {
        public static RequestTrackData fromFile(String fileName) throws IOException {
            return new RequestTrackData(new ByteBufferKaitaiStream(fileName));
        }

        public RequestTrackData(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RequestTrackData(KaitaiStream _io, AccBroadcastingOutbound _parent) {
            this(_io, _parent, null);
        }

        public RequestTrackData(KaitaiStream _io, AccBroadcastingOutbound _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
        }

        public void _fetchInstances() {
        }
        private int connectionId;
        private AccBroadcastingOutbound _root;
        private AccBroadcastingOutbound _parent;
        public int connectionId() { return connectionId; }
        public AccBroadcastingOutbound _root() { return _root; }
        public AccBroadcastingOutbound _parent() { return _parent; }
    }
    public static class UnregisterCommandApplication extends KaitaiStruct {
        public static UnregisterCommandApplication fromFile(String fileName) throws IOException {
            return new UnregisterCommandApplication(new ByteBufferKaitaiStream(fileName));
        }

        public UnregisterCommandApplication(KaitaiStream _io) {
            this(_io, null, null);
        }

        public UnregisterCommandApplication(KaitaiStream _io, AccBroadcastingOutbound _parent) {
            this(_io, _parent, null);
        }

        public UnregisterCommandApplication(KaitaiStream _io, AccBroadcastingOutbound _parent, AccBroadcastingOutbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
        }

        public void _fetchInstances() {
        }
        private int connectionId;
        private AccBroadcastingOutbound _root;
        private AccBroadcastingOutbound _parent;
        public int connectionId() { return connectionId; }
        public AccBroadcastingOutbound _root() { return _root; }
        public AccBroadcastingOutbound _parent() { return _parent; }
    }
    private OutboundMsgType msgType;
    private KaitaiStruct body;
    private AccBroadcastingOutbound _root;
    private KaitaiStruct _parent;
    public OutboundMsgType msgType() { return msgType; }
    public KaitaiStruct body() { return body; }
    public AccBroadcastingOutbound _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
