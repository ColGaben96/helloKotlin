package com.gabensoft.model.dto

import java.io.Serializable

data class PersonDTO (var id: Int, var nombre: String, var apellido: String, var email: String?) : Serializable, Comparator<PersonDTO> {
    override fun compare(o1: PersonDTO?, o2: PersonDTO?): Int {
        return o1!!.apellido.compareTo(o2!!.apellido)
    }
}