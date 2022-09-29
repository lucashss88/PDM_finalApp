package br.eti.valeria.arrocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class Home : AppCompatActivity() {
    private lateinit var btnHome: Button
    private lateinit var tvHome: TextView
    private lateinit var LLhome: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.btnHome = findViewById(R.id.btnHome)
        this.tvHome = findViewById(R.id.tvHome)
        this.LLhome = findViewById(R.id.LLhome)
        this.btnHome.setOnClickListener(OnClick())
    }
    inner class OnClick: View.OnClickListener{
        override fun onClick(p0: View?) {
            val intent = Intent(this@Home, MainActivity::class.java)
            startActivity(intent)
        }
    }
}