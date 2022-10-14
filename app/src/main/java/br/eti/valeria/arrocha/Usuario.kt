package br.eti.valeria.arrocha

import java.io.Serializable

class Usuario: Serializable {
    var id: Int
    var nome: String

    constructor(nome: String){
        this.id = -1
        this.nome = nome
    }

    constructor(id: Int, nome: String){
        this.id = id
        this.nome = nome
    }
    override fun toString(): String {
        return "${nome}"
    }
}