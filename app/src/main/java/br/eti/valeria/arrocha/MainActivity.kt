package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var etNumero: EditText
    private lateinit var btArrocha: Button
    private lateinit var arrocha: Arrocha
    private lateinit var fabAjustes: FloatingActionButton
    private lateinit var progressBar1: ProgressBar
    private lateinit var tvNivel: TextView
    private var nivel: Int

    init {
        this.nivel = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // criar novo jogo
        this.arrocha = Arrocha()
        this.etNumero = findViewById(R.id.etMainNumero)
        this.btArrocha = findViewById(R.id.btMainArrocha)
        this.fabAjustes = findViewById(R.id.fabAjustes)
        this.progressBar1 = findViewById(R.id.progressBar1)
        this.tvNivel = findViewById(R.id.tvNivelMain)

        this.btArrocha.setOnClickListener(OnClickBotao())

        this.fabAjustes.setOnClickListener{
            val intent = Intent(this, Ajustes::class.java)
            startActivity(intent)
        }
    }
    inner class OnClickBotao: View.OnClickListener{
        override fun onClick(p0: View?) {
            val numero = this@MainActivity.etNumero.text.toString().toInt()
            val msg = this@MainActivity.arrocha.jogar(numero)
            try {
                if (this@MainActivity.arrocha.getStatus() == Status.EXECUTANDO) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    Log.w("APP_ARROCHA", "NÍVEL: (${nivel})")

                    if (this@MainActivity.nivel==1) {
                        this@MainActivity.progressBar1.setProgress(progressBar1.progress+5)
                    }
                    if (this@MainActivity.nivel==2) {
                        this@MainActivity.progressBar1.setProgress(progressBar1.progress+10)
                    }
                    if (this@MainActivity.nivel==3) {
                        this@MainActivity.progressBar1.setProgress(progressBar1.progress+15)
                    }
                    if (this@MainActivity.nivel==4) {
                        this@MainActivity.progressBar1.setProgress(progressBar1.progress+20)
                    }
                    if (this@MainActivity.nivel==5) {
                        val intent = Intent(this@MainActivity, TelaFinal::class.java)
                        startActivity(intent)
                    }
                    if (this@MainActivity.progressBar1.progress == 100) {
                        this@MainActivity.arrocha.setStatus(Status.PERDEU)
                    }
                } else {
                    if (this@MainActivity.arrocha.getStatus()== Status.GANHOU){
                        setNivel(nivel+1)
                    }
                    if (this@MainActivity.arrocha.getStatus()== Status.PERDEU){
                        setNivel(1)
                    }
                    // navegar para tela resultado
                    val intent = Intent(this@MainActivity, ResultadoActivity::class.java)
                    intent.putExtra("MAIN", this@MainActivity.arrocha)
                    intent.putExtra("NIVEL", this@MainActivity.getNivel())
                    startActivity(intent)


                    this@MainActivity.arrocha = Arrocha()
                    this@MainActivity.progressBar1.setProgress(0)
                }

                this@MainActivity.etNumero.setText("")
                this@MainActivity.tvNivel.setText(getNivelStr()).toString()
            }
            catch (e: NumberFormatException) {
                Toast.makeText(this@MainActivity, "Informe um número!", Toast.LENGTH_SHORT).show()
            }
            //var msg = "Seu número é ${numero}"
            //Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    public fun getNivel(): Int {
        return this.nivel
    }
    private fun setNivel(nivel: Int) {
        this.nivel = nivel
    }
    private fun getNivelStr(): String {
        return "NÍVEL: ${this.nivel}"
    }
}

