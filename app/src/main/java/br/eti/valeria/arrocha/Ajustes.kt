package br.eti.valeria.arrocha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.ToggleButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Ajustes : AppCompatActivity() {

    private lateinit var fabBack: FloatingActionButton
    private lateinit var toggleTempo: ToggleButton
    private lateinit var switchTema: Switch
    private lateinit var switchSom: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)
        this.switchTema = findViewById(R.id.switchTema)
        this.toggleTempo = findViewById(R.id.toggleTempo)
        this.switchSom = findViewById(R.id.switchSom)
        this.fabBack = findViewById(R.id.fabBack)
        this.fabBack.setOnClickListener {
            finish()
        }
        this.toggleTempo.setOnCheckedChangeListener(OnChange())
    }

    inner class OnChange: CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        }

    }
}