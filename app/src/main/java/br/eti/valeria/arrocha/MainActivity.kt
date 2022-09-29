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

class MainActivity : AppCompatActivity() {
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

        this.etNumero = findViewById(R.id.etMainNumero)
        this.btArrocha = findViewById(R.id.btMainArrocha)
        this.fabAjustes = findViewById(R.id.fabAjustes)
        this.progressBar = findViewById(R.id.progressBar1)

        this.btArrocha.setOnClickListener(OnClickBotao())

        this.fabAjustes.setOnClickListener{
            val intent = Intent(this, Ajustes::class.java)
            startActivity(intent)
        }
    }

    inner class OnClickBotao: View.OnClickListener{
        override fun onClick(p0: View?) {
            var numero = this@MainActivity.etNumero.text.toString().toInt()
            var msg = this@MainActivity.arrocha.jogar(numero)
            try {
                if (this@MainActivity.arrocha.getStatus() == Status.EXECUTANDO) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    this@MainActivity.progressBar.setProgress(progressBar.progress+5)
                    if (this@MainActivity.progressBar.progress == 100) {
                        this@MainActivity.arrocha.setStatus(Status.PERDEU)
                    }
                } else {
                    // navegar para tela resultado
                    val intent = Intent(this@MainActivity, ResultadoActivity::class.java)
                    intent.putExtra("JOGO", this@MainActivity.arrocha)
                    startActivity(intent)
                    this@MainActivity.arrocha = Arrocha()
                    this@MainActivity.progressBar.setProgress(0)
                }

                this@MainActivity.etNumero.setText("")
            }
            catch (e: NumberFormatException) {
                Toast.makeText(this@MainActivity, "Informe um número!", Toast.LENGTH_SHORT).show()
            }
            //var msg = "Seu número é ${numero}"
            //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

}

