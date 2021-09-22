package com.gabensoft.model.entities

import com.gabensoft.model.persistence.PersonDTO

//This is a comment
class PersonDAO {
    private var persons: ArrayList<PersonDTO> = ArrayList()

    fun create(id: Int, nombre: String, apellido: String, email: String?) : Int {
        persons.firstOrNull { it.id == id }?.let {
            return PERSON_OPERATION_ERROR
        } ?: run {
            persons.add(PersonDTO(id, nombre, apellido, email))
            return PERSON_OPERATION_SUCCESS
        }
    }

    fun read(id: Int) : PersonDTO? = persons.find { it.id == id }

    fun searchByNombre(nombre: String) = persons.filter { it.nombre.contains(nombre) }

    fun searchByApellido(apellido: String) = persons.filter { it.apellido.contains(apellido) }

    fun update(oldPerson: PersonDTO, nombre: String, apellido: String, email: String?): Int {
        persons.firstOrNull { it == oldPerson }?.let {
            it.nombre = nombre
            it.apellido = apellido
            it.email = email
            return PERSON_OPERATION_SUCCESS
        } ?: run { return PERSON_OPERATION_ERROR }
    }

    fun delete(person: PersonDTO) : Int {
        return if (persons.remove(person)) {
            PERSON_OPERATION_SUCCESS
        } else {
            PERSON_OPERATION_ERROR
        }
    }

    fun arraySize(): Int {
        return persons.size
    }

    companion object {
        const val PERSON_OPERATION_SUCCESS = 0
        const val PERSON_OPERATION_ERROR = -1
    }

}
