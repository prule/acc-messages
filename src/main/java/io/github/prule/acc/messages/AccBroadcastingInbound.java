// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package io.github.prule.acc.messages;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * Inbound messages (ACC → app).
 * Reference: Kunos Simulazioni ksBroadcastingNetwork SDK
 */
public class AccBroadcastingInbound extends KaitaiStruct {
    public static AccBroadcastingInbound fromFile(String fileName) throws IOException {
        return new AccBroadcastingInbound(new ByteBufferKaitaiStream(fileName));
    }

    public enum BroadcastType {
        NONE(0),
        GREENFLAG(1),
        SESSIONOVER(2),
        PENALTYCOMMMSG(3),
        ACCIDENT(4),
        LAPCOMPLETED(5),
        BESTSESSIONLAP(6),
        BESTPERSONALLAP(7);

        private final long id;
        BroadcastType(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, BroadcastType> byId = new HashMap<Long, BroadcastType>(8);
        static {
            for (BroadcastType e : BroadcastType.values())
                byId.put(e.id(), e);
        }
        public static BroadcastType byId(long id) { return byId.get(id); }
    }

    public enum CarLocation {
        UNKNOWN(0),
        TRACK(1),
        PITLANE(2),
        PIT_ENTRY(3),
        PIT_EXIT(4);

        private final long id;
        CarLocation(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, CarLocation> byId = new HashMap<Long, CarLocation>(5);
        static {
            for (CarLocation e : CarLocation.values())
                byId.put(e.id(), e);
        }
        public static CarLocation byId(long id) { return byId.get(id); }
    }

    public enum CupCategory {
        OVERALL_PRO(0),
        PRO_AM(1),
        AM(2),
        SILVER(3),
        NATIONAL(4);

        private final long id;
        CupCategory(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, CupCategory> byId = new HashMap<Long, CupCategory>(5);
        static {
            for (CupCategory e : CupCategory.values())
                byId.put(e.id(), e);
        }
        public static CupCategory byId(long id) { return byId.get(id); }
    }

    public enum DriverCategory {
        BRONZE(0),
        SILVER(1),
        GOLD(2),
        PLATINUM(3);

        private final long id;
        DriverCategory(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, DriverCategory> byId = new HashMap<Long, DriverCategory>(4);
        static {
            for (DriverCategory e : DriverCategory.values())
                byId.put(e.id(), e);
        }
        public static DriverCategory byId(long id) { return byId.get(id); }
    }

    public enum InboundMsgType {
        REGISTRATION_RESULT(1),
        REALTIME_UPDATE(2),
        REALTIME_CAR_UPDATE(3),
        ENTRY_LIST(4),
        TRACK_DATA(5),
        ENTRY_LIST_CAR(6),
        BROADCASTING_EVENT(7);

        private final long id;
        InboundMsgType(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, InboundMsgType> byId = new HashMap<Long, InboundMsgType>(7);
        static {
            for (InboundMsgType e : InboundMsgType.values())
                byId.put(e.id(), e);
        }
        public static InboundMsgType byId(long id) { return byId.get(id); }
    }

    public enum SessionPhase {
        NONE(0),
        STARTING(1),
        PRE_FORMATION(2),
        FORMATION_LAP(3),
        PRE_SESSION(4),
        SESSION(5),
        SESSION_OVER(6),
        POST_SESSION(7),
        RESULT_UI(8);

        private final long id;
        SessionPhase(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, SessionPhase> byId = new HashMap<Long, SessionPhase>(9);
        static {
            for (SessionPhase e : SessionPhase.values())
                byId.put(e.id(), e);
        }
        public static SessionPhase byId(long id) { return byId.get(id); }
    }

    public enum SessionType {
        PRACTICE(0),
        QUALIFYING(1),
        SUPERPOLE(2),
        RACE(3),
        HOTLAP(4),
        UNKNOWN(9);

        private final long id;
        SessionType(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, SessionType> byId = new HashMap<Long, SessionType>(6);
        static {
            for (SessionType e : SessionType.values())
                byId.put(e.id(), e);
        }
        public static SessionType byId(long id) { return byId.get(id); }
    }

    public AccBroadcastingInbound(KaitaiStream _io) {
        this(_io, null, null);
    }

    public AccBroadcastingInbound(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public AccBroadcastingInbound(KaitaiStream _io, KaitaiStruct _parent, AccBroadcastingInbound _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.msgType = InboundMsgType.byId(this._io.readU1());
        {
            InboundMsgType on = msgType();
            if (on != null) {
                switch (msgType()) {
                case BROADCASTING_EVENT: {
                    this.body = new BroadcastingEvent(this._io, this, _root);
                    break;
                }
                case ENTRY_LIST: {
                    this.body = new EntryList(this._io, this, _root);
                    break;
                }
                case ENTRY_LIST_CAR: {
                    this.body = new EntryListCar(this._io, this, _root);
                    break;
                }
                case REALTIME_CAR_UPDATE: {
                    this.body = new RealtimeCarUpdate(this._io, this, _root);
                    break;
                }
                case REALTIME_UPDATE: {
                    this.body = new RealtimeUpdate(this._io, this, _root);
                    break;
                }
                case REGISTRATION_RESULT: {
                    this.body = new RegistrationResult(this._io, this, _root);
                    break;
                }
                case TRACK_DATA: {
                    this.body = new TrackData(this._io, this, _root);
                    break;
                }
                }
            }
        }
    }

    public void _fetchInstances() {
        {
            InboundMsgType on = msgType();
            if (on != null) {
                switch (msgType()) {
                case BROADCASTING_EVENT: {
                    ((BroadcastingEvent) (this.body))._fetchInstances();
                    break;
                }
                case ENTRY_LIST: {
                    ((EntryList) (this.body))._fetchInstances();
                    break;
                }
                case ENTRY_LIST_CAR: {
                    ((EntryListCar) (this.body))._fetchInstances();
                    break;
                }
                case REALTIME_CAR_UPDATE: {
                    ((RealtimeCarUpdate) (this.body))._fetchInstances();
                    break;
                }
                case REALTIME_UPDATE: {
                    ((RealtimeUpdate) (this.body))._fetchInstances();
                    break;
                }
                case REGISTRATION_RESULT: {
                    ((RegistrationResult) (this.body))._fetchInstances();
                    break;
                }
                case TRACK_DATA: {
                    ((TrackData) (this.body))._fetchInstances();
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

        public AccString(KaitaiStream _io, KaitaiStruct _parent, AccBroadcastingInbound _root) {
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
        private AccBroadcastingInbound _root;
        private KaitaiStruct _parent;
        public int length() { return length; }
        public String data() { return data; }
        public AccBroadcastingInbound _root() { return _root; }
        public KaitaiStruct _parent() { return _parent; }
    }
    public static class BroadcastingEvent extends KaitaiStruct {
        public static BroadcastingEvent fromFile(String fileName) throws IOException {
            return new BroadcastingEvent(new ByteBufferKaitaiStream(fileName));
        }

        public BroadcastingEvent(KaitaiStream _io) {
            this(_io, null, null);
        }

        public BroadcastingEvent(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public BroadcastingEvent(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.type = AccBroadcastingInbound.BroadcastType.byId(this._io.readU1());
            this.msg = new AccString(this._io, this, _root);
            this.timeMs = this._io.readS4le();
            this.carId = this._io.readS4le();
        }

        public void _fetchInstances() {
            this.msg._fetchInstances();
        }
        private BroadcastType type;
        private AccString msg;
        private int timeMs;
        private int carId;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public BroadcastType type() { return type; }
        public AccString msg() { return msg; }
        public int timeMs() { return timeMs; }
        public int carId() { return carId; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class CameraSet extends KaitaiStruct {
        public static CameraSet fromFile(String fileName) throws IOException {
            return new CameraSet(new ByteBufferKaitaiStream(fileName));
        }

        public CameraSet(KaitaiStream _io) {
            this(_io, null, null);
        }

        public CameraSet(KaitaiStream _io, AccBroadcastingInbound.TrackData _parent) {
            this(_io, _parent, null);
        }

        public CameraSet(KaitaiStream _io, AccBroadcastingInbound.TrackData _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.cameraSetName = new AccString(this._io, this, _root);
            this.numCameras = this._io.readU1();
            this.cameras = new ArrayList<AccString>();
            for (int i = 0; i < numCameras(); i++) {
                this.cameras.add(new AccString(this._io, this, _root));
            }
        }

        public void _fetchInstances() {
            this.cameraSetName._fetchInstances();
            for (int i = 0; i < this.cameras.size(); i++) {
                this.cameras.get(((Number) (i)).intValue())._fetchInstances();
            }
        }
        private AccString cameraSetName;
        private int numCameras;
        private List<AccString> cameras;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound.TrackData _parent;
        public AccString cameraSetName() { return cameraSetName; }
        public int numCameras() { return numCameras; }
        public List<AccString> cameras() { return cameras; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound.TrackData _parent() { return _parent; }
    }
    public static class DriverEntry extends KaitaiStruct {
        public static DriverEntry fromFile(String fileName) throws IOException {
            return new DriverEntry(new ByteBufferKaitaiStream(fileName));
        }

        public DriverEntry(KaitaiStream _io) {
            this(_io, null, null);
        }

        public DriverEntry(KaitaiStream _io, AccBroadcastingInbound.EntryListCar _parent) {
            this(_io, _parent, null);
        }

        public DriverEntry(KaitaiStream _io, AccBroadcastingInbound.EntryListCar _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.firstName = new AccString(this._io, this, _root);
            this.lastName = new AccString(this._io, this, _root);
            this.shortName = new AccString(this._io, this, _root);
            this.category = AccBroadcastingInbound.DriverCategory.byId(this._io.readU1());
            this.nationality = this._io.readU2le();
        }

        public void _fetchInstances() {
            this.firstName._fetchInstances();
            this.lastName._fetchInstances();
            this.shortName._fetchInstances();
        }
        private AccString firstName;
        private AccString lastName;
        private AccString shortName;
        private DriverCategory category;
        private int nationality;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound.EntryListCar _parent;
        public AccString firstName() { return firstName; }
        public AccString lastName() { return lastName; }
        public AccString shortName() { return shortName; }
        public DriverCategory category() { return category; }
        public int nationality() { return nationality; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound.EntryListCar _parent() { return _parent; }
    }
    public static class EntryList extends KaitaiStruct {
        public static EntryList fromFile(String fileName) throws IOException {
            return new EntryList(new ByteBufferKaitaiStream(fileName));
        }

        public EntryList(KaitaiStream _io) {
            this(_io, null, null);
        }

        public EntryList(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public EntryList(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
            this.numCarIndexes = this._io.readU2le();
            this.carIndexes = new ArrayList<Integer>();
            for (int i = 0; i < numCarIndexes(); i++) {
                this.carIndexes.add(this._io.readU2le());
            }
        }

        public void _fetchInstances() {
            for (int i = 0; i < this.carIndexes.size(); i++) {
            }
        }
        private int connectionId;
        private int numCarIndexes;
        private List<Integer> carIndexes;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int connectionId() { return connectionId; }
        public int numCarIndexes() { return numCarIndexes; }
        public List<Integer> carIndexes() { return carIndexes; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class EntryListCar extends KaitaiStruct {
        public static EntryListCar fromFile(String fileName) throws IOException {
            return new EntryListCar(new ByteBufferKaitaiStream(fileName));
        }

        public EntryListCar(KaitaiStream _io) {
            this(_io, null, null);
        }

        public EntryListCar(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public EntryListCar(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.carId = this._io.readU2le();
            this.carModelType = this._io.readU1();
            this.teamName = new AccString(this._io, this, _root);
            this.raceNumber = this._io.readS4le();
            this.cupCategory = AccBroadcastingInbound.CupCategory.byId(this._io.readU1());
            this.driverIndex = this._io.readU1();
            this.nationality = this._io.readU2le();
            this.numDrivers = this._io.readU1();
            this.drivers = new ArrayList<DriverEntry>();
            for (int i = 0; i < numDrivers(); i++) {
                this.drivers.add(new DriverEntry(this._io, this, _root));
            }
        }

        public void _fetchInstances() {
            this.teamName._fetchInstances();
            for (int i = 0; i < this.drivers.size(); i++) {
                this.drivers.get(((Number) (i)).intValue())._fetchInstances();
            }
        }
        private int carId;
        private int carModelType;
        private AccString teamName;
        private int raceNumber;
        private CupCategory cupCategory;
        private int driverIndex;
        private int nationality;
        private int numDrivers;
        private List<DriverEntry> drivers;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int carId() { return carId; }
        public int carModelType() { return carModelType; }
        public AccString teamName() { return teamName; }
        public int raceNumber() { return raceNumber; }
        public CupCategory cupCategory() { return cupCategory; }
        public int driverIndex() { return driverIndex; }
        public int nationality() { return nationality; }
        public int numDrivers() { return numDrivers; }
        public List<DriverEntry> drivers() { return drivers; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class LapInfo extends KaitaiStruct {
        public static LapInfo fromFile(String fileName) throws IOException {
            return new LapInfo(new ByteBufferKaitaiStream(fileName));
        }

        public LapInfo(KaitaiStream _io) {
            this(_io, null, null);
        }

        public LapInfo(KaitaiStream _io, KaitaiStruct _parent) {
            this(_io, _parent, null);
        }

        public LapInfo(KaitaiStream _io, KaitaiStruct _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.lapTimeMs = this._io.readS4le();
            this.carIndex = this._io.readU2le();
            this.driverIndex = this._io.readU2le();
            this.numSplits = this._io.readU1();
            this.splits = new ArrayList<Integer>();
            for (int i = 0; i < numSplits(); i++) {
                this.splits.add(this._io.readS4le());
            }
            this.isInvalid = this._io.readU1();
            this.isValidForBest = this._io.readU1();
            this.isOutlap = this._io.readU1();
            this.isInlap = this._io.readU1();
        }

        public void _fetchInstances() {
            for (int i = 0; i < this.splits.size(); i++) {
            }
        }
        private int lapTimeMs;
        private int carIndex;
        private int driverIndex;
        private int numSplits;
        private List<Integer> splits;
        private int isInvalid;
        private int isValidForBest;
        private int isOutlap;
        private int isInlap;
        private AccBroadcastingInbound _root;
        private KaitaiStruct _parent;
        public int lapTimeMs() { return lapTimeMs; }
        public int carIndex() { return carIndex; }
        public int driverIndex() { return driverIndex; }
        public int numSplits() { return numSplits; }
        public List<Integer> splits() { return splits; }
        public int isInvalid() { return isInvalid; }
        public int isValidForBest() { return isValidForBest; }
        public int isOutlap() { return isOutlap; }
        public int isInlap() { return isInlap; }
        public AccBroadcastingInbound _root() { return _root; }
        public KaitaiStruct _parent() { return _parent; }
    }
    public static class RealtimeCarUpdate extends KaitaiStruct {
        public static RealtimeCarUpdate fromFile(String fileName) throws IOException {
            return new RealtimeCarUpdate(new ByteBufferKaitaiStream(fileName));
        }

        public RealtimeCarUpdate(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RealtimeCarUpdate(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public RealtimeCarUpdate(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.carIndex = this._io.readU2le();
            this.driverIndex = this._io.readU2le();
            this.driverCount = this._io.readU1();
            this.gear = this._io.readS1();
            this.worldPosX = this._io.readF4le();
            this.worldPosY = this._io.readF4le();
            this.yaw = this._io.readF4le();
            this.carLocation = AccBroadcastingInbound.CarLocation.byId(this._io.readU1());
            this.kmh = this._io.readU2le();
            this.position = this._io.readU2le();
            this.cupPosition = this._io.readU2le();
            this.trackPosition = this._io.readU2le();
            this.splinePosition = this._io.readF4le();
            this.laps = this._io.readU2le();
            this.delta = this._io.readS4le();
            this.bestSessionLap = new LapInfo(this._io, this, _root);
            this.lastLap = new LapInfo(this._io, this, _root);
            this.currentLap = new LapInfo(this._io, this, _root);
        }

        public void _fetchInstances() {
            this.bestSessionLap._fetchInstances();
            this.lastLap._fetchInstances();
            this.currentLap._fetchInstances();
        }
        private int carIndex;
        private int driverIndex;
        private int driverCount;
        private byte gear;
        private float worldPosX;
        private float worldPosY;
        private float yaw;
        private CarLocation carLocation;
        private int kmh;
        private int position;
        private int cupPosition;
        private int trackPosition;
        private float splinePosition;
        private int laps;
        private int delta;
        private LapInfo bestSessionLap;
        private LapInfo lastLap;
        private LapInfo currentLap;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int carIndex() { return carIndex; }
        public int driverIndex() { return driverIndex; }
        public int driverCount() { return driverCount; }
        public byte gear() { return gear; }
        public float worldPosX() { return worldPosX; }
        public float worldPosY() { return worldPosY; }
        public float yaw() { return yaw; }
        public CarLocation carLocation() { return carLocation; }
        public int kmh() { return kmh; }
        public int position() { return position; }
        public int cupPosition() { return cupPosition; }
        public int trackPosition() { return trackPosition; }
        public float splinePosition() { return splinePosition; }
        public int laps() { return laps; }
        public int delta() { return delta; }
        public LapInfo bestSessionLap() { return bestSessionLap; }
        public LapInfo lastLap() { return lastLap; }
        public LapInfo currentLap() { return currentLap; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class RealtimeUpdate extends KaitaiStruct {
        public static RealtimeUpdate fromFile(String fileName) throws IOException {
            return new RealtimeUpdate(new ByteBufferKaitaiStream(fileName));
        }

        public RealtimeUpdate(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RealtimeUpdate(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public RealtimeUpdate(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.eventIndex = this._io.readU2le();
            this.sessionIndex = this._io.readU2le();
            this.sessionType = AccBroadcastingInbound.SessionType.byId(this._io.readU1());
            this.phase = AccBroadcastingInbound.SessionPhase.byId(this._io.readU1());
            this.sessionTimeMs = this._io.readF4le();
            this.sessionEndTimeMs = this._io.readF4le();
            this.focusedCarIndex = this._io.readS4le();
            this.activeCameraSet = new AccString(this._io, this, _root);
            this.activeCamera = new AccString(this._io, this, _root);
            this.currentHudPage = new AccString(this._io, this, _root);
            this.isReplayPlaying = this._io.readU1();
            if (isReplayPlaying() != 0) {
                this.replaySessionTime = this._io.readF4le();
            }
            if (isReplayPlaying() != 0) {
                this.replayRemainingTime = this._io.readF4le();
            }
            this.timeOfDaySeconds = this._io.readF4le();
            this.ambientTemp = this._io.readU1();
            this.trackTemp = this._io.readU1();
            this.clouds = this._io.readU1();
            this.rainLevel = this._io.readU1();
            this.wetness = this._io.readU1();
            this.bestSessionLap = new LapInfo(this._io, this, _root);
        }

        public void _fetchInstances() {
            this.activeCameraSet._fetchInstances();
            this.activeCamera._fetchInstances();
            this.currentHudPage._fetchInstances();
            if (isReplayPlaying() != 0) {
            }
            if (isReplayPlaying() != 0) {
            }
            this.bestSessionLap._fetchInstances();
        }
        private int eventIndex;
        private int sessionIndex;
        private SessionType sessionType;
        private SessionPhase phase;
        private float sessionTimeMs;
        private float sessionEndTimeMs;
        private int focusedCarIndex;
        private AccString activeCameraSet;
        private AccString activeCamera;
        private AccString currentHudPage;
        private int isReplayPlaying;
        private Float replaySessionTime;
        private Float replayRemainingTime;
        private float timeOfDaySeconds;
        private int ambientTemp;
        private int trackTemp;
        private int clouds;
        private int rainLevel;
        private int wetness;
        private LapInfo bestSessionLap;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int eventIndex() { return eventIndex; }
        public int sessionIndex() { return sessionIndex; }
        public SessionType sessionType() { return sessionType; }
        public SessionPhase phase() { return phase; }
        public float sessionTimeMs() { return sessionTimeMs; }
        public float sessionEndTimeMs() { return sessionEndTimeMs; }
        public int focusedCarIndex() { return focusedCarIndex; }
        public AccString activeCameraSet() { return activeCameraSet; }
        public AccString activeCamera() { return activeCamera; }
        public AccString currentHudPage() { return currentHudPage; }
        public int isReplayPlaying() { return isReplayPlaying; }
        public Float replaySessionTime() { return replaySessionTime; }
        public Float replayRemainingTime() { return replayRemainingTime; }
        public float timeOfDaySeconds() { return timeOfDaySeconds; }
        public int ambientTemp() { return ambientTemp; }
        public int trackTemp() { return trackTemp; }
        public int clouds() { return clouds; }
        public int rainLevel() { return rainLevel; }
        public int wetness() { return wetness; }
        public LapInfo bestSessionLap() { return bestSessionLap; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class RegistrationResult extends KaitaiStruct {
        public static RegistrationResult fromFile(String fileName) throws IOException {
            return new RegistrationResult(new ByteBufferKaitaiStream(fileName));
        }

        public RegistrationResult(KaitaiStream _io) {
            this(_io, null, null);
        }

        public RegistrationResult(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public RegistrationResult(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
            this.connectionSuccess = this._io.readU1();
            this.isReadOnly = this._io.readU1();
            this.errorMessage = new AccString(this._io, this, _root);
        }

        public void _fetchInstances() {
            this.errorMessage._fetchInstances();
        }
        private int connectionId;
        private int connectionSuccess;
        private int isReadOnly;
        private AccString errorMessage;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int connectionId() { return connectionId; }
        public int connectionSuccess() { return connectionSuccess; }
        public int isReadOnly() { return isReadOnly; }
        public AccString errorMessage() { return errorMessage; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    public static class TrackData extends KaitaiStruct {
        public static TrackData fromFile(String fileName) throws IOException {
            return new TrackData(new ByteBufferKaitaiStream(fileName));
        }

        public TrackData(KaitaiStream _io) {
            this(_io, null, null);
        }

        public TrackData(KaitaiStream _io, AccBroadcastingInbound _parent) {
            this(_io, _parent, null);
        }

        public TrackData(KaitaiStream _io, AccBroadcastingInbound _parent, AccBroadcastingInbound _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.connectionId = this._io.readS4le();
            this.trackName = new AccString(this._io, this, _root);
            this.trackId = this._io.readS4le();
            this.trackMeters = this._io.readS4le();
            this.numCameraSets = this._io.readU1();
            this.cameraSets = new ArrayList<CameraSet>();
            for (int i = 0; i < numCameraSets(); i++) {
                this.cameraSets.add(new CameraSet(this._io, this, _root));
            }
            this.numHudPages = this._io.readU1();
            this.hudPages = new ArrayList<AccString>();
            for (int i = 0; i < numHudPages(); i++) {
                this.hudPages.add(new AccString(this._io, this, _root));
            }
        }

        public void _fetchInstances() {
            this.trackName._fetchInstances();
            for (int i = 0; i < this.cameraSets.size(); i++) {
                this.cameraSets.get(((Number) (i)).intValue())._fetchInstances();
            }
            for (int i = 0; i < this.hudPages.size(); i++) {
                this.hudPages.get(((Number) (i)).intValue())._fetchInstances();
            }
        }
        private int connectionId;
        private AccString trackName;
        private int trackId;
        private int trackMeters;
        private int numCameraSets;
        private List<CameraSet> cameraSets;
        private int numHudPages;
        private List<AccString> hudPages;
        private AccBroadcastingInbound _root;
        private AccBroadcastingInbound _parent;
        public int connectionId() { return connectionId; }
        public AccString trackName() { return trackName; }
        public int trackId() { return trackId; }
        public int trackMeters() { return trackMeters; }
        public int numCameraSets() { return numCameraSets; }
        public List<CameraSet> cameraSets() { return cameraSets; }
        public int numHudPages() { return numHudPages; }
        public List<AccString> hudPages() { return hudPages; }
        public AccBroadcastingInbound _root() { return _root; }
        public AccBroadcastingInbound _parent() { return _parent; }
    }
    private InboundMsgType msgType;
    private KaitaiStruct body;
    private AccBroadcastingInbound _root;
    private KaitaiStruct _parent;
    public InboundMsgType msgType() { return msgType; }
    public KaitaiStruct body() { return body; }
    public AccBroadcastingInbound _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
