import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main(){
  runApp(
      new MyApp()
  );
}

class MyApp extends StatelessWidget {

  String hi = "Hello World! Bla bla ";

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        home: new Container(
          decoration: new BoxDecoration(color: Colors.blue),
          child: new Center(
              child: new Text(hi)
          ),
        ),
      );
  }
}