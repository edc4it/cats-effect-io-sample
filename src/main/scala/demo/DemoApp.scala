package demo

import better.files.File

object DemoApp extends App {

    import FileConverter._

    convert(File.temp / "demofile", File.temp / "result")

}
