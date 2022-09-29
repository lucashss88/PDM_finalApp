package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class ResultadoActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var llresultado: LinearLayout
    private lateinit var tvIntervalo: TextView
    private lateinit var tvSorteado: TextView
    private lateinit var btnRestart: Button
    private lateinit var btnProximo: Button
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        this.btnRestart = findViewById(R.id.btnRestart)
        this.tvResultado = findViewById(R.id.tvResultadoFinal)
        this.tvIntervalo = findViewById(R.id.tvIntervalo)
        this.tvSorteado = findViewById(R.id.tvSorteado)
        this.llresultado = findViewById(R.id.LLresultado)
        this.btnProximo = findViewById(R.id.btnProxNivel2)
        this.ratingBar = findViewById(R.id.ratingBar)

        this.btnRestart.setOnClickListener(OnClickBotao())
        this.btnProximo.setOnClickListener(OnClickBotaoProximo())

        if (intent.hasExtra("JOGO")){
            val jogo = intent.getSerializableExtra("JOGO") as Arrocha
            val intervalo = jogo.getIntervalo()
            val sorteado = jogo.getSecreto()
            if (jogo.getStatus()==Status.GANHOU){
                this.tvResultado.setText("GANHOU!")
                this.tvIntervalo.setText(intervalo).toString()
                this.tvSorteado.setText(sorteado).toString()
                this.llresultado.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                this.ratingBar.visibility = View.VISIBLE
                this.btnProximo.visibility = View.VISIBLE

            }
            else if (jogo.getStatus()==Status.PERDEU){
                this.tvResultado.setText("PERDEU!")
                this.tvIntervalo.setText(intervalo).toString()
                this.tvSorteado.setText(sorteado).toString()
                this.llresultado.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                this.btnRestart.visibility = View.VISIBLE
            }
            Toast.makeText(this,jogo.getStatus().toString(), Toast.LENGTH_SHORT).show()
        }
    }
    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(p0: View?) {
            finish()
        }
    }
    inner class OnClickBotaoProximo: View.OnClickListener {
        override fun onClick(p0: View?) {
            val intent = Intent(this@ResultadoActivity,Nivel2::class.java)
            startActivity(intent)
        }
    }
//    fun onClickProximo(): View.OnClickListener {
//    }
}