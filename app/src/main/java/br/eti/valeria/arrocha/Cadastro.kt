package br.eti.valeria.arrocha

class Cadastro {
    private var lista: ArrayList<Usuario>

    init {
        this.lista = ArrayList()
    }

    fun add(usuario: Usuario){
        this.lista.add(usuario)
    }

    fun get(): ArrayList<Usuario>{
        return this.lista
    }

    fun get(index: Int): Usuario{
        return this.lista[index]
    }

    fun remove(index: Int){
        this.lista.removeAt(index)
    }
}