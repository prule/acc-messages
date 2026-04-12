meta:
  id: acc_broadcasting_inbound
  title: Assetto Corsa Competizione Broadcasting UDP Protocol (Inbound)
  license: MIT
  endian: le
  encoding: UTF-8

doc: |
  Inbound messages (ACC → app).
  Reference: Kunos Simulazioni ksBroadcastingNetwork SDK

types:
  acc_string:
    seq:
      - id: length
        type: u2
      - id: data
        type: str
        size: length

  lap_info:
    seq:
      - id: lap_time_ms
        type: s4
      - id: car_index
        type: u2
      - id: driver_index
        type: u2
      - id: num_splits
        type: u1
      - id: splits
        type: s4
        repeat: expr
        repeat-expr: num_splits
      - id: is_invalid
        type: u1
      - id: is_valid_for_best
        type: u1
      - id: is_outlap
        type: u1
      - id: is_inlap
        type: u1

  broadcasting_event:
    seq:
      - id: type
        type: u1
        enum: broadcast_type
      - id: msg
        type: acc_string
      - id: time_ms
        type: s4
      - id: car_id
        type: s4

  registration_result:
    seq:
      - id: connection_id
        type: s4
      - id: connection_success
        type: u1
      - id: is_read_only
        type: u1
      - id: error_message
        type: acc_string

  realtime_update:
    seq:
      - id: event_index
        type: u2
      - id: session_index
        type: u2
      - id: session_type
        type: u1
        enum: session_type
      - id: phase
        type: u1
        enum: session_phase
      - id: session_time_ms
        type: f4
      - id: session_end_time_ms
        type: f4
      - id: focused_car_index
        type: s4
      - id: active_camera_set
        type: acc_string
      - id: active_camera
        type: acc_string
      - id: current_hud_page
        type: acc_string
      - id: is_replay_playing
        type: u1
      - id: replay_session_time
        type: f4
        if: is_replay_playing != 0
      - id: replay_remaining_time
        type: f4
        if: is_replay_playing != 0
      - id: time_of_day_seconds
        type: f4
      - id: ambient_temp
        type: u1
      - id: track_temp
        type: u1
      - id: clouds
        type: u1
      - id: rain_level
        type: u1
      - id: wetness
        type: u1
      - id: best_session_lap
        type: lap_info

  realtime_car_update:
    seq:
      - id: car_index
        type: u2
      - id: driver_index
        type: u2
      - id: driver_count
        type: u1
      - id: gear
        type: s1
      - id: world_pos_x
        type: f4
      - id: world_pos_y
        type: f4
      - id: yaw
        type: f4
      - id: car_location
        type: u1
        enum: car_location
      - id: kmh
        type: u2
      - id: position
        type: u2
      - id: cup_position
        type: u2
      - id: track_position
        type: u2
      - id: spline_position
        type: f4
      - id: laps
        type: u2
      - id: delta
        type: s4
      - id: best_session_lap
        type: lap_info
      - id: last_lap
        type: lap_info
      - id: current_lap
        type: lap_info

  entry_list:
    seq:
      - id: connection_id
        type: s4
      - id: num_car_indexes
        type: u2
      - id: car_indexes
        type: u2
        repeat: expr
        repeat-expr: num_car_indexes

  track_data:
    seq:
      - id: connection_id
        type: s4
      - id: track_name
        type: acc_string
      - id: track_id
        type: s4
      - id: track_meters
        type: s4
      - id: num_camera_sets
        type: u1
      - id: camera_sets
        type: camera_set
        repeat: expr
        repeat-expr: num_camera_sets
      - id: num_hud_pages
        type: u1
      - id: hud_pages
        type: acc_string
        repeat: expr
        repeat-expr: num_hud_pages

  camera_set:
    seq:
      - id: camera_set_name
        type: acc_string
      - id: num_cameras
        type: u1
      - id: cameras
        type: acc_string
        repeat: expr
        repeat-expr: num_cameras

  entry_list_car:
    seq:
      - id: car_id
        type: u2
      - id: car_model_type
        type: u1
      - id: team_name
        type: acc_string
      - id: race_number
        type: s4
      - id: cup_category
        type: u1
        enum: cup_category
      - id: driver_index
        type: u1
      - id: nationality
        type: u2
      - id: num_drivers
        type: u1
      - id: drivers
        type: driver_entry
        repeat: expr
        repeat-expr: num_drivers

  driver_entry:
    seq:
      - id: first_name
        type: acc_string
      - id: last_name
        type: acc_string
      - id: short_name
        type: acc_string
      - id: category
        type: u1
        enum: driver_category
      - id: nationality
        type: u2

seq:
  - id: msg_type
    type: u1
    enum: inbound_msg_type
  - id: body
    type:
      switch-on: msg_type
      cases:
        'inbound_msg_type::registration_result':  registration_result
        'inbound_msg_type::realtime_update':       realtime_update
        'inbound_msg_type::realtime_car_update':   realtime_car_update
        'inbound_msg_type::entry_list':            entry_list
        'inbound_msg_type::track_data':            track_data
        'inbound_msg_type::entry_list_car':        entry_list_car
        'inbound_msg_type::broadcasting_event':    broadcasting_event

enums:
  inbound_msg_type:
    1: registration_result
    2: realtime_update
    3: realtime_car_update
    4: entry_list
    5: track_data
    6: entry_list_car
    7: broadcasting_event

  broadcast_type:
    0: none
    1: greenflag
    2: sessionover
    3: penaltycommmsg
    4: accident
    5: lapcompleted
    6: bestsessionlap
    7: bestpersonallap

  session_type:
    0: practice
    4: qualifying
    9: super_pole
    10: race
    11: hotlap
    12: hot_stint
    13: hotlap_superpole
    14: replay

  session_phase:
    0: none
    1: starting
    2: pre_formation
    3: formation_lap
    4: pre_session
    5: session
    6: session_over
    7: post_session
    8: result_ui

  car_location:
    0: unknown
    1: track
    2: pitlane
    3: pit_entry
    4: pit_exit

  cup_category:
    0: overall_pro
    1: pro_am
    2: am
    3: silver
    4: national

  driver_category:
    0: bronze
    1: silver
    2: gold
    3: platinum
