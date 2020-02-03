package demo

import better.files.File
import cats.Monad
import cats.effect.Sync
import cats.implicits._


object FileConverter {

  def convert[F[_] : Sync](in: File, out: File): F[Unit] =
    for {
      s <- read(in)
      c <- Monad[F].pure(transform(s))
      _ <- write(out, c)
    } yield ()

  private def transform(s: String): String =
    s.toUpperCase()

  private def write[F[_] : Sync](file: File, s: String): F[Unit] =
    Sync[F].delay(file.writeText(s))

  private def read[F[_] : Sync](file: File): F[String] =
    Sync[F].delay(file.loadBytes)
      .map(b => new String(b))
}


