# ACC Broadcasting Messages

[![](https://jitpack.io/v/prule/acc-messages.svg)](https://jitpack.io/#prule/acc-messages)

This project contains Kaitai Struct definitions for the Assetto Corsa Competizione (ACC) Broadcasting UDP Protocol. This allows us to generate java classes representing the messages so we can easily consume them.

> Configuring ACC:
> In `C:\Users\<username>\Documents\Assetto Corsa Competizione\Config\broadcasting.json` you can find the broadcasting configuration - you'll need the port and connection password from here:
> ```json
> {                                                                                                                         
>   "updListenerPort": 9000,
>   "connectionPassword": "asd",
>   "commandPassword": ""
> }
> ```
> For information on ACC broadcasting and the ACC Broadcasting Test Client see https://github.com/prule/acc-messages/blob/main/docs/AccBroadcastingExample/ReadMe.md


## Prerequisites

1. Install [Kaitai Struct Compiler](https://kaitai.io):
   ```shell
   brew install kaitai-struct-compiler
   ```

## Usage

Generate the Java classes manually:

```shell
kaitai-struct-compiler --outdir src/main/java --java-package io.github.prule.acc.messages -t java ksy/acc_broadcasting_inbound.ksy ksy/acc_broadcasting_outbound.ksy
```

Install the jar locally so other projects can use it:

```shell
./gradlew clean publishToMavenLocal
```

## Formatting

https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config


## Notes

The REALTIME_UPDATE (type 2) packet contains a focusedCarIndex field — ACC always sets this to your car in single-player/practice. 

A broadcast event is sent when a lap completes
```json
{
   "msgType": "BROADCASTING_EVENT",
   "body": {
      "type": 5,
      "msg": {
         "length": 9,
         "data": "01:36.745"
      },
      "timeMs": 1204467,
      "carId": 6
   }
}
```

The server admin handbook can be found in 
```
SteamLibrary\steamapps\common\Assetto Corsa Competizione Dedicated Server\server\ServerAdminHandbook.pdf
```



