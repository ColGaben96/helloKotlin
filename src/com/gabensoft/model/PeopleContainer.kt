package com.gabensoft.model

import com.gabensoft.model.dto.PersonDTO
import com.gabensoft.model.entities.PersonDAO
import com.gabensoft.model.persistence.FileProcessor

class PeopleContainer() {
    private val persons: PersonDAO = PersonDAO()
    private val files: FileProcessor = FileProcessor()

    init {
        try {
            restore()
        } catch (e: Exception) {

        }
    }

    fun restore(): Int {
        val restored = files.read()
        persons.restoreArray(restored as ArrayList<PersonDTO>)
        persons.getArray()?.let {
            return persons.arraySize()
        } ?: run {
            return -1
        }
    }

    fun create(id: Int, nombre: String, apellido: String, email: String?) {
        persons.create(id, nombre, apellido, email)
        persons.getArray()?.let { files.write(it) } ?: run {
            println("Error")
        }
    }

    fun read(id: Int): PersonDTO? {
        return persons.read(id)
    }

    fun searchByNombre(nombre: String): List<PersonDTO> {
        return persons.searchByNombre(nombre)
    }

    fun searchByApellido(apellido: String): List<PersonDTO> {
        return persons.searchByApellido(apellido)
    }

    fun update(id: Int, nombre: String, apellido: String, email: String?) {
        persons.read(id)?.let { persons.update(it, nombre, apellido, email) }
        persons.getArray()?.let { files.write(it) }
    }

    fun delete(id: Int) {
        persons.read(id)?.let { persons.delete(it) }
        persons.getArray()?.let { files.write(it) }
    }

    fun getArraySize(): Int {
        return persons.arraySize()
    }
}