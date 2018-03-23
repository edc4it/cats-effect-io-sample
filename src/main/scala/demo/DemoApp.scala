package demo

import better.files.File

object DemoApp extends App {

    import FileConverter._

    convert(File.currentWorkingDirectory / "demofile", File.currentWorkingDirectory / "result")

}
