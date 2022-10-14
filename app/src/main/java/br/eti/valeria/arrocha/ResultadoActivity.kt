package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class ResultadoActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var llresultado: LinearLayout
    private lateinit var tvIntervalo: TextView
    private lateinit var tvSorteado: TextView
    private lateinit var tvNivelResultado: TextView
    private lateinit var btnRestart: Button
    private lateinit var btnProximo: Button
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSalvarR: Button
    private lateinit var btnReiniciar: Button
    private lateinit var cadastro: Cadastro
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        this.btnRestart = findViewById(R.id.btnRestart)
        this.tvResultado = findViewById(R.id.tvResultadoFinal)
        this.tvIntervalo = findViewById(R.id.tvIntervalo)
        this.tvSorteado = findViewById(R.id.tvSorteado)
        this.tvNivelResultado = findViewById(R.id.tvNivel)
        this.llresultado = findViewById(R.id.LLresultado)
        this.btnProximo = findViewById(R.id.btnProxNivel2)
        this.ratingBar = findViewById(R.id.ratingBar)
        this.btnSalvarR = findViewById(R.id.btnSalvarResultado)
        this.btnReiniciar = findViewById(R.id.btnReiniciar)

        this.cadastro = Cadastro()

        this.btnRestart.setOnClickListener(OnClickBotao())
        this.btnProximo.setOnClickListener(OnClickBotaoProximo())
        this.btnReiniciar.setOnClickListener(OnClickReiniciar())

        if (intent.hasExtra("MAIN")){
            val jogo = intent.getSerializableExtra("MAIN") as Arrocha
            Log.i("APP",jogo.toString())
            val intervalo = jogo.getIntervalo()
            val sorteado = jogo.getSecreto()
            if (jogo.getStatus()==Status.GANHOU){
                this.tvResultado.setText("GANHOU!")
                this.tvIntervalo.setText(intervalo).toString()
                this.tvSorteado.setText(sorteado).toString()
//                this.tvPontos.setText(pontos).toString()
                this.llresultado.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                this.btnProximo.visibility = View.VISIBLE
            }
            else if (jogo.getStatus()==Status.PERDEU){
                this.tvResultado.setText("PERDEU!")
                this.tvIntervalo.setText(intervalo).toString()
                this.tvSorteado.setText(sorteado).toString()
//                this.tvPontos.setText(pontos).toString()
                this.llresultado.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                this.btnRestart.visibility = View.VISIBLE
//                this.tvNivel.setText(nivel).toString()
            }
            Toast.makeText(this,jogo.getStatus().toString(), Toast.LENGTH_SHORT).show()
        }
        if (intent.hasExtra("NIVEL")) {
            val n = intent.getSerializableExtra("NIVEL") as Int
            if (n==1) {
                this.ratingBar.visibility = View.VISIBLE
            }
            if (n==2) {
                this.ratingBar.visibility = View.VISIBLE
                this.ratingBar.rating = ratingBar.rating+1
            }
            if (n==3) {
                this.ratingBar.visibility = View.VISIBLE
                this.ratingBar.rating = ratingBar.rating+2
            }
            if (n==4) {
                this.ratingBar.visibility = View.VISIBLE
                this.ratingBar.rating = ratingBar.rating+3
                this.btnSalvarR.visibility = View.VISIBLE
                this.btnProximo.visibility = View.INVISIBLE
                this.btnReiniciar.visibility = View.VISIBLE
            }
            val msg = "NÍVEL: $n"
            this.tvNivelResultado.text = msg
        }



        this.btnSalvarR.setOnClickListener{
            val intent = Intent(this@ResultadoActivity, TelaCadastro::class.java)
            startActivity(intent)
        }
    }

    inner class OnClickReiniciar: View.OnClickListener {
        override fun onClick(p0: View?) {
        }
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(p0: View?) {
            finish()
        }
    }
    inner class OnClickBotaoProximo: View.OnClickListener {
        override fun onClick(p0: View?) {
            finish()
        }
    }


//    fun onClickProximo(): View.OnClickListener {
//    }
}