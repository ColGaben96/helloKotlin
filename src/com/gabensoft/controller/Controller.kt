package com.gabensoft.controller

import com.gabensoft.model.entities.PersonDAO
import java.util.*

class Controller {
    private val sc: Scanner = Scanner(System.`in`)
    private val personas: PersonDAO = PersonDAO()
    fun console() {
        var active = true
        while (active) {
            try {
                println("Hay "+personas.arraySize()+" personas")
                print("Ingresa una opcion: ")
                when (sc.nextLine()) {
                    "0" -> active = false
                    "1" -> help()
                    "2" -> create()
                    "3" -> searchNombre()
                    "4" -> searchApellido()
                    "5" -> update()
                    "6" -> delete()
                    else -> println("Ingresa una opción válida")
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun help() {
        println("""
            Opción  Descripcion
            0       Salir
            1       Muestra esta ayuda
            2       Crear una persona
            3       Buscar x nombre
            4       Buscar x apellido
            5       Actualizar
            6       Eliminar
        """.trimIndent())
    }

    fun create() {
        print("Ingresa los nombres: ")
        val nombre = sc.nextLine()
        print("Ingresa los apellidos: ")
        val apellido = sc.nextLine()
        print("Ingresa el correo electrónico: ")
        val email = sc.nextLine()
        if (personas.create(personas.arraySize(), nombre, apellido, email) == -1) {
            println("Error creando la persona")
        }
    }

    fun searchNombre() {
        print("Ingresa el o los nombres a buscar: ")
        val nombre = sc.nextLine()
        val result = personas.searchByNombre(nombre)
        println("ID Nombre Completo Email")
        result.forEach {
            println("""
                ${it.id}    ${it.nombre} ${it.apellido} ${it.email}
            """.trimIndent())
        }
    }

    fun searchApellido() {
        print("Ingresa el o los apellidos a buscar: ")
        val apellido = sc.nextLine()
        val result = personas.searchByApellido(apellido)
        println("ID Nombre Completo Email")
        result.forEach {
            println("""
                ${it.id}    ${it.nombre} ${it.apellido} ${it.email}
            """.trimIndent())
        }
    }

    fun update() {
        print("Ingresa el ID de la persona a buscar: ")
        val id = sc.nextInt()
        personas.read(id)?.let {
            println("Vas a modificar a ${it.nombre} ${it.apellido}")
            print("Ingresa los nuevos nombres: ")
            sc.nextLine()
            var nombre = sc.nextLine()
            print("Ingresa los nuevos apellidos: ")
            var apellido = sc.nextLine()
            print("Ingresa el nuevo correo electrónico: ")
            var email = sc.nextLine()
            if (nombre.isEmpty()) {
                nombre = it.nombre
            }
            if (apellido.isEmpty()) {
                apellido = it.apellido
            }
            if (email.isEmpty()) {
                email = it.email
            }
            personas.update(it, nombre, apellido, email)
            sc.nextLine()
        } ?: run {
            println("No se encontró a esa persona por ese ID")
            sc.nextLine()
        }
    }

    fun delete() {
        print("Ingresa el ID de la persona a buscar: ")
        val id = sc.nextInt()
        personas.read(id)?.let {
            personas.delete(it)
            sc.nextLine()
        } ?: run {
            println("No se encontró a esa persona por ese ID")
            sc.nextLine()
        }
    }
}

fun main() {
    Controller().console()
}