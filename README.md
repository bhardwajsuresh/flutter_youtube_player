# flutter_youtube_player

A new Flutter plugin for playing inline youtube videos. It is available only for Android
and uses official android Youtube Player API. This plugin provides a FlutterYoutubePlayer widget
which can be embedded in app like any other widget.

Supported

- Android

## How to Use

```yaml
dependencies:
  flutter_youtube_player: ^1.0.0
```

## Imports

```dart
import 'package:flutter_youtube_player/flutter_youtube_player.dart';
```

## Code

```dart

FlutterYoutubePlayer(
  apiKey: "<API_KEY>",
  videoId: "<Youtube Video ID>",
  autoPlay: false, //default true
)
```


| Key        | Value              | Default |
| ---------- | ------------------ | ------- |
| apiKey     | String (Not Null)  |
| videoId    | String (Not Null)  |
| autoPlay   | Boolean (Optional) | true    |


## Example

[Click here for complete example](https://pub.dev/packages/flutter_youtube_player#-example-tab- "flutter_youtube_player example")