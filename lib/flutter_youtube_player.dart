import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlutterYoutubePlayer extends StatefulWidget {
  final String videoId;
  final String apiKey;
  final bool autoPlay;
  const FlutterYoutubePlayer({
    Key key,
    @required this.apiKey,
    @required this.videoId,
    this.autoPlay = true
  }) : super(key: key);

//
//  static Future<String> get platformVersion async {
//    final String version = await _channel.invokeMethod('getPlatformVersion');
//    return version;
//  }

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new _FlutterYoutubePlayerState();
  }
}

class _FlutterYoutubePlayerState extends State<FlutterYoutubePlayer> {
  //static const MethodChannel _channel = const MethodChannel('flutter_youtube_player');

  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      //print(widget.apiKey);
      //Uint8List encoded = const Utf8Codec().encoder.convert(widget.apiKey);

      Map <String, dynamic> settings = {
        "apiKey" : widget.apiKey,
        "videoId" : widget.videoId,
        "autoPlay" : widget.autoPlay,
      };

      return AspectRatio(
        aspectRatio: 16 / 9,
        child: AndroidView(
          viewType: 'com.sureshb.flutteryoutubeplugin',
          onPlatformViewCreated: _onPlatformViewCreated,
          creationParams: settings,
          creationParamsCodec: StandardMessageCodec(),
        ),
      );
    }
    return Text('$defaultTargetPlatform is not yet supported by the text_view plugin');
  }

  void _onPlatformViewCreated(int id) {
    print(id);
    //FlutterYoutubePlayerController._(id);

    //_channel.invokeMethod('setText', "hello");
  }
}
//class FlutterYoutubePlayerController {
//  FlutterYoutubePlayerController._(int id)
//      : _channel = new MethodChannel('flutter_youtube_player/textview_$id');
//
//  final MethodChannel _channel;
//
//  Future<void> play(String text) async {
//    assert(text != null);
//    return _channel.invokeMethod('setText', text);
//  }
//}
