package br.eti.valeria.arrocha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class TelaFinal : AppCompatActivity() {

    private lateinit var lvTF: ListView
    private lateinit var cadastro: Cadastro
    private lateinit var dao: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_final)

        this.cadastro = Cadastro()
        this.lvTF = findViewById(R.id.lvTF)
        this.dao = UsuarioDAO(this)
        this.atualiza()
        this.lvTF.setOnItemLongClickListener(OnItemLongClick())
    }

    fun atualiza(){
        val layout = android.R.layout.simple_list_item_1
        this.lvTF.adapter = ArrayAdapter<Usuario>(this, layout,this.dao.read())
    }

    inner class OnItemLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long): Boolean {
            val usuario = adapter?.getItemAtPosition(index) as Usuario
            this@TelaFinal.dao.delete(usuario)
            val msg = "${usuario.nome} removido com sucesso!"
            Toast.makeText(this@TelaFinal, msg, Toast.LENGTH_SHORT).show()
            this@TelaFinal.atualiza()
            return true
        }
    }
}