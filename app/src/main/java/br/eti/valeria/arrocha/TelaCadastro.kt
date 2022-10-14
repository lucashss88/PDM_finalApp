package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TelaCadastro : AppCompatActivity() {

    private lateinit var etFormNome: EditText
    private lateinit var btnFormSalvar: Button
    private lateinit var dao: UsuarioDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        this.btnFormSalvar = findViewById(R.id.btFormSalvar)
        this.etFormNome = findViewById(R.id.etFormNome)
        this.btnFormSalvar.setOnClickListener{salvar()}
        this.dao = UsuarioDAO(this)
    }

    private fun salvar(){
        val nome = etFormNome.text.toString()
        val usuario = Usuario(nome)
        this.dao.insert(usuario)
        val intent = Intent(this, TelaFinal::class.java)
        startActivity(intent)
    }
}