// You will be creating an app that converts from American Dollars to the currency of your choice,
// and from that currency to American Dollars.
//
// You will create a launch icon, do the conversion, and create a toast message for values worth
// over $10,000.00

package com.example.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // variables for conversion rates
        val usdRate = 1.0809
        val euroRate = 0.91825

        // variables for interface views
        val convertAmt: EditText = findViewById(R.id.idCurrencyInput)
        val rbUSDtoEuro: RadioButton = findViewById(R.id.idRBusd2euro)
        val rbEuroToUSD: RadioButton = findViewById(R.id.idRBeuro2usd)
        val convertBtn: Button = findViewById(R.id.idBtnConvert)
        val result: TextView = findViewById(R.id.idResult)

        convertBtn.setOnClickListener {
            val dblConvertAmt = convertAmt.text.toString().toDouble()
            val outputFormat = DecimalFormat("###,###.00")

            // toast messages for values worth over $10,000.00 (no bourgeoisie)
            // usd to euro won't allow the user to enter over 10,000
            if (rbUSDtoEuro.isChecked) {
                val conversionResult = dblConvertAmt * euroRate

                if (dblConvertAmt < 10000.0){
                    val resultText = "Your conversion from USD is €${outputFormat.format(conversionResult)}"
                    result.text = resultText
                } else {
                    Toast.makeText(this@MainActivity, "Amount you entered must be less than $10,000.00",Toast.LENGTH_LONG).show()
                }
            }

            // euro to usd won't allow the conversion result to be over 10,000
            if (rbEuroToUSD.isChecked) {
                val conversionResult = dblConvertAmt * usdRate

                if (conversionResult < 10000.0){
                    val resultText = "Your conversion from Euro is $${outputFormat.format(conversionResult)}"
                    result.text = resultText
                } else {
                    Toast.makeText(this@MainActivity, "Amount must be less than $10,000.00",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}