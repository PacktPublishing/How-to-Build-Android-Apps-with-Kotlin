package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.math.BigDecimal

class OutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        val amount = intent?.getStringExtra("amount")?.toBigDecimal() ?: BigDecimal.ZERO
        val percent = intent?.getStringExtra("percent")?.toBigDecimal() ?: BigDecimal.ZERO
        val tip = amount * (percent.divide("100".toBigDecimal()))

        val tipText: TextView = findViewById(R.id.tip_text)
        tipText.text = "The tip is $tip"
    }
}