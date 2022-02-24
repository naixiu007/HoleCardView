package com.ctop007.holeCardView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ctop007.holeCardView.databinding.ActivityMainBinding

/**
 * @author topc
 * @date 2022/2/23 11:40 下午
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnSimpleEffect.setOnClickListener {
            startActivity(Intent(this, SimpleEffectActivity::class.java))
        }

        binding.btnSimpleMovie.setOnClickListener {
            startActivity(Intent(this, SimpleMovieActivity::class.java))
        }

        binding.btnSimpleTicket.setOnClickListener {
            startActivity(Intent(this, SimpleTicketActivity::class.java))
        }
    }
}