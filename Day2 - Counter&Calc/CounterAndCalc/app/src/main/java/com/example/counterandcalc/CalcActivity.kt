package com.example.counterandcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calc.*

class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)


        var answer: Double
        add.setOnClickListener {
            if (checkInput()) {
                var value1: Double = num1.text.toString().toDouble()
                var value2: Double = num2.text.toString().toDouble()
                answer = (value1 + value2).toDouble()
                ans.text = "Answer \n$answer"
            }
        }
        sub.setOnClickListener {
            if (checkInput()) {
                var value1: Int = num1.text.toString().toInt()
                var value2: Int = num2.text.toString().toInt()
                answer = (value1 - value2).toDouble()
                ans.text = "Answer \n$answer"
            }
        }
        div.setOnClickListener {
            if (checkInput()) {
                var value1: Int = num1.text.toString().toInt()
                var value2: Int = num2.text.toString().toInt()
                answer = (value1 / value2).toDouble()
                ans.text = "Answer \n$answer"
            }
        }
        mul.setOnClickListener {
            if (checkInput()) {
                var value1: Int = num1.text.toString().toInt()
                var value2: Int = num2.text.toString().toInt()
                answer = (value1 * value2).toDouble()
                ans.text = "Answer \n$answer"
            }
        }
    }

    private fun checkInput(): Boolean {
        if (num1.text.toString().isEmpty() || num2.text.toString().isEmpty()) {
            Toast.makeText(this, "Empty Input", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
