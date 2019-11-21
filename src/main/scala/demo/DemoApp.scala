package demo

import better.files.File
import cats.effect.IO

object DemoApp extends App {

    import FileConverter._

  val program: IO[Either[Error, Unit]] =
    convert(File.temp / "demofile", File.temp / "result")

  program.unsafeRunSync() match {
    case Left(error) => println(s"error: $error")
    case Right(_) =>  println("successfully written")
  }

}
