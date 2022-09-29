package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.NumberFormatException

class Nivel2 : AppCompatActivity() {
    private lateinit var etNumero: EditText
    private lateinit var btArrocha: Button
    private lateinit var arrocha: Arrocha
    private lateinit var fabAjustes: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // criar novo jogo
        this.arrocha = Arrocha()

        this.etNumero = findViewById(R.id.etNivel2Numero)
        this.btArrocha = findViewById(R.id.btNivel2Arrocha)
        this.fabAjustes = findViewById(R.id.fabAjustes)
        this.progressBar = findViewById(R.id.progressBar2)

        this.btArrocha.setOnClickListener(OnClickBotao())

        this.fabAjustes.setOnClickListener{
            val intent = Intent(this, Ajustes::class.java)
            startActivity(intent)
        }
    }

    inner class OnClickBotao: View.OnClickListener{
        override fun onClick(p0: View?) {
            var numero = this@Nivel2.etNumero.text.toString().toInt()
            var msg = this@Nivel2.arrocha.jogar(numero)
            try {
                if (this@Nivel2.arrocha.getStatus() == Status.EXECUTANDO) {
                    Toast.makeText(this@Nivel2, msg, Toast.LENGTH_SHORT).show()
                    this@Nivel2.progressBar.setProgress(progressBar.progress+20)
                    if (this@Nivel2.progressBar.progress == 100) {
                        this@Nivel2.arrocha.setStatus(Status.PERDEU)
                    }
                } else {
                    // navegar para tela resultado
                    val intent = Intent(this@Nivel2, ResultadoActivity::class.java)
                    intent.putExtra("JOGO", this@Nivel2.arrocha)
                    startActivity(intent)
                    this@Nivel2.arrocha = Arrocha()
                    this@Nivel2.progressBar.setProgress(0)
                }

                this@Nivel2.etNumero.setText("")
            }
            catch (e: NumberFormatException) {
                Toast.makeText(this@Nivel2, "Informe um número!", Toast.LENGTH_SHORT).show()
            }
            //var msg = "Seu número é ${numero}"
            //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

}
