meta:
  id: acc_broadcasting_outbound
  title: Assetto Corsa Competizione Broadcasting UDP Protocol (Outbound)
  license: MIT
  endian: le
  encoding: UTF-8

doc: |
  Outbound messages (app → ACC).
  Reference: Kunos Simulazioni ksBroadcastingNetwork SDK

types:
  acc_string:
    seq:
      - id: length
        type: u2
      - id: data
        type: str
        size: length

  register_command_application:
    seq:
      - id: protocol_version
        type: u1
      - id: display_name
        type: acc_string
      - id: connection_password
        type: acc_string
      - id: ms_realtime_update_interval
        type: s4
      - id: command_password
        type: acc_string

  unregister_command_application:
    seq:
      - id: connection_id
        type: s4

  request_entry_list:
    seq:
      - id: connection_id
        type: s4

  request_track_data:
    seq:
      - id: connection_id
        type: s4

  change_focus:
    seq:
      - id: connection_id
        type: s4
      - id: change_car_focus
        type: u1
      - id: car_index
        type: u2
      - id: change_camera
        type: u1
      - id: camera_set
        type: acc_string
      - id: camera
        type: acc_string

seq:
  - id: msg_type
    type: u1
    enum: outbound_msg_type
  - id: body
    type:
      switch-on: msg_type
      cases:
        'outbound_msg_type::register_command_application':    register_command_application
        'outbound_msg_type::unregister_command_application':  unregister_command_application
        'outbound_msg_type::request_entry_list':              request_entry_list
        'outbound_msg_type::request_track_data':              request_track_data
        'outbound_msg_type::change_focus':                    change_focus

enums:
  outbound_msg_type:
    1:  register_command_application
    9:  unregister_command_application
    10: request_entry_list
    11: request_track_data
    50: change_focus
