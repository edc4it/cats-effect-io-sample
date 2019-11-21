package demo

import better.files.File
import scala.util.Try
import cats.effect.IO
import cats.implicits._
import cats.data.EitherT


object FileConverter {

  def convert(in: File, out: File): IO[Either[Error, Unit]] = {
    val r = for {
      str <- EitherT(read(in))
      _ <- EitherT(write(out, str.toUpperCase()))
    } yield ()
    r.value
  }


  private def write(file: File, s: String): IO[Either[IOError, Unit]] =  IO {
      val bytes = s.getBytes.iterator
      Try(file.writeBytes(bytes))
        .map(_ => ())
        .toEither
        .leftMap(t => IOError(t.getMessage))
    }

  private def read(file: File): IO[Either[IOError, String]] = IO {
      Try(file.loadBytes)
        .map(bytes => new String(bytes))
        .toEither
        .leftMap(t => IOError(t.getMessage))
    }
}


