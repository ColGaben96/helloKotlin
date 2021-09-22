package com.gabensoft.model.persistence

import java.io.Serializable

data class PersonDTO (var id: Int, var nombre: String, var apellido: String, var email: String?) : Serializable