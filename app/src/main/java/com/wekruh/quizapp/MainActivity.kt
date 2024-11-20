package com.wekruh.quizapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wekruh.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        sharedPref = getSharedPreferences("com.wekruh.quizapp", MODE_PRIVATE)
        val lastTry = sharedPref.getInt("lastTry", 0)
        val highScore = sharedPref.getInt("highScore", 0)
        binding.highScore.text = "High Score: ${highScore-1}"
        binding.lastTryText.text = "Last Round: ${lastTry - 1}"
        isHighScore(lastTry)

        super.onStart()
    }

    fun isHighScore(score : Int) {
        val highScore = sharedPref.getInt("highScore",0)
        if (score > highScore) {
            sharedPref.edit().putInt("highScore", score).apply()
            binding.highScore.text = "High Score: ${score - 1}"
        }
    }

    fun playGame(view: View) {
        val intent = Intent(this, QuestionScreen::class.java)
        startActivity(intent)
    }

    fun resetScore(view: View) {
        sharedPref.edit().remove("highScore").apply()
        sharedPref.edit().remove("lastTry").apply()
        binding.highScore.text = "High Score: 0"
        binding.lastTryText.text = "Last Round: 0"
    }
}
