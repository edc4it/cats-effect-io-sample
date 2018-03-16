package demo

import better.files.File


object FileConverter {

    def convert(in: File, out: File): Unit = {
        val str = read(in)
        write(out, str.toUpperCase())
    }


    private def write(file: File, s: String): Unit = {
        val bytes = s.getBytes.iterator
        file.writeBytes(bytes)
    }

    private def read(file: File): String = {
        val bytes = file.loadBytes
        new String(bytes)
    }
}


