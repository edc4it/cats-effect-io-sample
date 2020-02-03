package demo

import better.files.File
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object DemoApp extends IOApp {

  import FileConverter._

  override def run(args: List[String]): IO[ExitCode] =
    convert[IO](File.temp / "demofile", File.temp / "result")
      .redeemWith (
        e => IO(println(s"Oops [${e.getClass}] ${e.getMessage}")) >> IO(ExitCode.Error),
        _ => IO(println("Successfully wrote the file")) >> IO(ExitCode.Success)
      )
}
