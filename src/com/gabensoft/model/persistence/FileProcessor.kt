package com.gabensoft.model.persistence

import java.io.*;

class FileProcessor {

    fun write(content: Any) {
        val f: File = File("./people.dat")
        val fo: FileOutputStream = FileOutputStream(f)
        val oo: ObjectOutputStream = ObjectOutputStream(fo)
        oo.writeObject(content)
        fo.close()
    }

    fun read(): Any? {
        val f: File = File("./people.dat")
        val fi: FileInputStream = FileInputStream(f)
        val oi: ObjectInputStream = ObjectInputStream(fi)
        return oi.readObject()
    }
}