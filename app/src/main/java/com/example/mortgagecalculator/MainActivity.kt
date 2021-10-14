package com.example.mortgagecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference to views
        val btn_calculate = findViewById(R.id.button_calculate) as Button
        var input_principal =  findViewById(R.id.input_principal_amt) as EditText
        var input_interest =  findViewById(R.id.input_interest_rate) as EditText
        var input_months =  findViewById(R.id.input_num_loans) as EditText
        var text_output =  findViewById(R.id.text_output) as TextView


        //Calculates the monthly payment when given reference to each input box
        fun calculate_monthly_payment(p_text: EditText, i_text: EditText, m_text: EditText): BigDecimal {
            var p = p_text.text.toString().toInt()
            var i = i_text.text.toString().toDouble()/100/12
            var m = m_text.text.toString().toInt()


            //Equation for monthly payment
            return BigDecimal(p*
                    (i*((1+i).pow(m)))/
                    ((1+i).pow(m)-1)).setScale(2, RoundingMode.HALF_EVEN)
        }

        //
        btn_calculate.setOnClickListener {
            //Toast.makeText(this@MainActivity, "Calculated monthly payment: "+calculate_monthly_payment(input_principal, input_interest, input_months), Toast.LENGTH_SHORT).show()

            //Check for empty inputs and do not show the response if there is a missing input
            if(input_principal.text.toString().matches(Regex(""))){
                Toast.makeText(this@MainActivity, "Please enter principal amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(input_interest.text.toString().matches(Regex(""))){
                Toast.makeText(this@MainActivity, "Please enter interest rate", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(input_months.text.toString().matches(Regex(""))){
                Toast.makeText(this@MainActivity, "Please enter months to pay loan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Set the output text
            text_output.text = "Calculated monthly payment: $"+calculate_monthly_payment(input_principal, input_interest, input_months)
            text_output.visibility  = View.VISIBLE
        }

    }
}