package br.eti.valeria.arrocha

import android.content.ContentValues
import android.content.Context

class UsuarioDAO {
    val bancoHelper: BancoHelper

    constructor(context: Context){
        this.bancoHelper = BancoHelper(context)
    }

    fun insert(usuario: Usuario){
        val contentValues = ContentValues()
        contentValues.put("nome", usuario.nome)
        this.bancoHelper.writableDatabase.insert("usuarios", null, contentValues)
    }

    fun read(): ArrayList<Usuario>{
        val lista = arrayListOf<Usuario>()
        val colunas = arrayOf("id", "nome")
        val c = this.bancoHelper.readableDatabase.query("usuarios", colunas, null, null, null, null, "nome")
        c.moveToFirst()
        for (i in 1 .. c.count){
            val id = c.getInt(0)
            val nome = c.getString(1)
            val usuario = Usuario(id,nome)
            lista.add(usuario)
            c.moveToNext()
        }
        return lista
    }

    fun delete(id: Int){
        val where = arrayOf(id.toString())
        this.bancoHelper.writableDatabase.delete("usuarios", "id = ?", where)
    }

    fun delete(usuario: Usuario){
        this.delete(usuario.id)
    }
}