package demo

sealed trait Error
case class IOError(reason: String) extends Error