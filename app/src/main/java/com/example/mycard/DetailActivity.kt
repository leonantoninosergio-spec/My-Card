package com.example.mycard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycard.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myCardDetail.apply {
            setTitle("Encabezado o título")
            setSubtitle("Subtítulo")
            setDescription("Esta es una breve descripción sobre la Card.")

            setOnPrimaryActionClickListener {
                Toast.makeText(this@DetailActivity, "Acción pulsada", Toast.LENGTH_SHORT).show()
            }

            setOnSecondaryActionClickListener {
                Toast.makeText(this@DetailActivity, "Acción secundaria pulsada", Toast.LENGTH_SHORT).show()
            }

            setOnMoreClickListener {
                Toast.makeText(this@DetailActivity, "Opciones de la tarjeta", Toast.LENGTH_SHORT).show()
            }
        }
    }
}