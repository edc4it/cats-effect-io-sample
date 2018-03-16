package demo

import better.files.File
import cats.effect.IO

object DemoApp extends App {

    import FileConverter._

    val program: IO[Either[Error, Unit]] = convert(File.temp / "demofile", File.temp / "result")
    program.flatMap(e => handle(e)).unsafeRunSync()

    def handle(e: Either[Error, Unit]) = e match {
        case Left(error) => IO { println(s"error: $error") }
        case Right(_) => IO { println("successfully written") }
    }

}

