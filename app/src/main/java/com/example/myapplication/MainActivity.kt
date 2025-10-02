package com.example.fuelcalculator

import com.example.myapplication.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
class MainActivity : AppCompatActivity() {

    private lateinit var Hinput: TextInputEditText
    private lateinit var Cinput: TextInputEditText
    private lateinit var Sinput: TextInputEditText
    private lateinit var Ninput: TextInputEditText
    private lateinit var Oinput: TextInputEditText
    private lateinit var Winput: TextInputEditText
    private lateinit var Ainput: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var HinputOil: TextInputEditText
    private lateinit var CinputOil: TextInputEditText
    private lateinit var SinputOil: TextInputEditText
    private lateinit var OinputOil: TextInputEditText
    private lateinit var VinputOil: TextInputEditText
    private lateinit var WinputOil: TextInputEditText
    private lateinit var AinputOil: TextInputEditText
    private lateinit var QinputOil: TextInputEditText
    private lateinit var saveButtonOil: Button
    private lateinit var clearButtonOil: Button

    private lateinit var outputFuel: TextView
    private lateinit var resultsCard: MaterialCardView
    private lateinit var coalCalculatorCard: MaterialCardView
    private lateinit var oilCalculatorCard: MaterialCardView
    private lateinit var btnCoalCalculator: Button
    private lateinit var btnOilCalculator: Button

    // Navigation buttons
    private lateinit var btnPractical1_1: Button
    private lateinit var btnPractical1_2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()
        setupNavigationListeners()
        showCoalCalculator() // Default calculator
    }

    private fun initializeViews() {
        try {
            //Перегляд калькулятора вугілля
            Hinput = findViewById(R.id.Hinput)
            Cinput = findViewById(R.id.Cinput)
            Sinput = findViewById(R.id.Sinput)
            Ninput = findViewById(R.id.Ninput)
            Oinput = findViewById(R.id.Oinput)
            Winput = findViewById(R.id.Winput)
            Ainput = findViewById(R.id.Ainput)
            saveButton = findViewById(R.id.saveButton)
            clearButton = findViewById(R.id.clearButton)

            //Перегляд калькулятора мазути
            HinputOil = findViewById(R.id.Hinput_oil)
            CinputOil = findViewById(R.id.Cinput_oil)
            SinputOil = findViewById(R.id.Sinput_oil)
            OinputOil = findViewById(R.id.Oinput_oil)
            VinputOil = findViewById(R.id.Vinput_oil)
            WinputOil = findViewById(R.id.Winput_oil)
            AinputOil = findViewById(R.id.Ainput_oil)
            QinputOil = findViewById(R.id.Qinput_oil)
            saveButtonOil = findViewById(R.id.saveButton_oil)
            clearButtonOil = findViewById(R.id.clearButton_oil)

            outputFuel = findViewById(R.id.outputFuel)
            resultsCard = findViewById(R.id.resultsCard)
            coalCalculatorCard = findViewById(R.id.coalCalculatorCard)
            oilCalculatorCard = findViewById(R.id.oilCalculatorCard)
            btnCoalCalculator = findViewById(R.id.btnCoalCalculator)
            btnOilCalculator = findViewById(R.id.btnOilCalculator)

            //Кнопки навігації
            btnPractical1_1 = findViewById(R.id.btnCoalCalculator)
            btnPractical1_2 = findViewById(R.id.btnOilCalculator)

        } catch (e: Exception) {
            android.widget.Toast.makeText(this, "Error initializing views: ${e.message}", android.widget.Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun setupClickListeners() {
        //Кнопки калькулятору палива
        saveButton.setOnClickListener {
            calculateAndDisplayCoalResults()
        }
        clearButton.setOnClickListener {
            clearCoalInputs()
        }

        //Кнопки калькулятору мазути
        saveButtonOil.setOnClickListener {
            calculateAndDisplayOilResults()
        }
        clearButtonOil.setOnClickListener {
            clearOilInputs()
        }

        // Calculator type switcher
        btnCoalCalculator.setOnClickListener {
            showCoalCalculator()
        }
        btnOilCalculator.setOnClickListener {
            showOilCalculator()
        }
    }

    private fun setupNavigationListeners() {
        btnPractical1_1.setOnClickListener {
            showCoalCalculator()
        }
        btnPractical1_2.setOnClickListener {
            showOilCalculator()
        }
    }

    private fun showCoalCalculator() {
        coalCalculatorCard.visibility = View.VISIBLE
        oilCalculatorCard.visibility = View.GONE
        btnCoalCalculator.setBackgroundResource(R.color.black)
        btnOilCalculator.setBackgroundResource(android.R.color.transparent)
        clearAllInputs()
    }

    private fun showOilCalculator() {
        coalCalculatorCard.visibility = View.GONE
        oilCalculatorCard.visibility = View.VISIBLE
        btnOilCalculator.setBackgroundResource(R.color.black)
        btnCoalCalculator.setBackgroundResource(android.R.color.transparent)
        clearAllInputs()
    }

    //Функції калькулятору палива
    private fun calculateAndDisplayCoalResults() {
        val fuelData = getCoalDataFromInputs()
        if (isValidCoalInput(fuelData)) {
            val results = fuelData.calculateResults()
            displayCoalResults(results)
            resultsCard.visibility = View.VISIBLE
        } else {
            outputFuel.text = "Будь ласка, заповніть всі поля коректними значеннями"
            resultsCard.visibility = View.VISIBLE
        }
    }

    private fun getCoalDataFromInputs(): FuelData {
        return FuelData(
            H = Hinput.text.toString().toDoubleOrNull() ?: 0.0,
            C = Cinput.text.toString().toDoubleOrNull() ?: 0.0,
            S = Sinput.text.toString().toDoubleOrNull() ?: 0.0,
            N = Ninput.text.toString().toDoubleOrNull() ?: 0.0,
            O = Oinput.text.toString().toDoubleOrNull() ?: 0.0,
            W = Winput.text.toString().toDoubleOrNull() ?: 0.0,
            A = Ainput.text.toString().toDoubleOrNull() ?: 0.0
        )
    }

    private fun isValidCoalInput(fuelData: FuelData): Boolean {
        return listOf(
            Hinput.text.toString(),
            Cinput.text.toString(),
            Sinput.text.toString(),
            Ninput.text.toString(),
            Oinput.text.toString(),
            Winput.text.toString(),
            Ainput.text.toString()
        ).all { it.isNotEmpty() }
    }

    private fun displayCoalResults(results: FuelResults) {
        val resultText = """
            Коефіцієнт переходу від робочої до сухої маси: ${"%.2f".format(results.Kpc)}
            
            Коефіцієнт переходу від робочої до горючої маси: ${"%.2f".format(results.Kpg)}
            
            Hᶜ = ${"%.2f".format(results.Hc)}%
            Cᶜ = ${"%.2f".format(results.Cc)}%
            Sᶜ = ${"%.2f".format(results.Sc)}%
            Nᶜ = ${"%.2f".format(results.Nc)}%
            Oᶜ = ${"%.2f".format(results.Oc)}%
            Aᶜ = ${"%.2f".format(results.Ac)}%
            
            Hᶢ = ${"%.2f".format(results.Hg)}%
            Cᶢ = ${"%.2f".format(results.Cg)}%
            Sᶢ = ${"%.2f".format(results.Sg)}%
            Nᶢ = ${"%.2f".format(results.Ng)}%
            Oᶢ = ${"%.2f".format(results.Og)}%
            
            Qph = ${"%.2f".format(results.Qph)} МДж/кг
            Qch = ${"%.2f".format(results.Qch)} МДж/кг
            Qgh = ${"%.2f".format(results.Qgh)} МДж/кг
        """.trimIndent()
        outputFuel.text = resultText
    }

    private fun clearCoalInputs() {
        Hinput.text?.clear()
        Cinput.text?.clear()
        Sinput.text?.clear()
        Ninput.text?.clear()
        Oinput.text?.clear()
        Winput.text?.clear()
        Ainput.text?.clear()
        resultsCard.visibility = View.GONE
    }

    //Функції калькулятору мазути
    private fun calculateAndDisplayOilResults() {
        val fuelOilData = getOilDataFromInputs()
        if (isValidOilInput(fuelOilData)) {
            val results = fuelOilData.calculateResults()
            displayOilResults(results)
            resultsCard.visibility = View.VISIBLE
        } else {
            outputFuel.text = "Будь ласка, заповніть всі поля коректними значеннями"
            resultsCard.visibility = View.VISIBLE
        }
    }

    private fun getOilDataFromInputs(): FuelOilData {
        return FuelOilData(
            H = HinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            C = CinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            S = SinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            O = OinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            V = VinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            W = WinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            A = AinputOil.text.toString().toDoubleOrNull() ?: 0.0,
            Q = QinputOil.text.toString().toDoubleOrNull() ?: 0.0
        )
    }

    private fun isValidOilInput(fuelOilData: FuelOilData): Boolean {
        return listOf(
            HinputOil.text.toString(),
            CinputOil.text.toString(),
            SinputOil.text.toString(),
            OinputOil.text.toString(),
            VinputOil.text.toString(),
            WinputOil.text.toString(),
            AinputOil.text.toString(),
            QinputOil.text.toString()
        ).all { it.isNotEmpty() }
    }

    private fun displayOilResults(results: FuelOilResults) {
        val resultText = """
            Cᵖ = ${"%.2f".format(results.Hp)}%
            Hᵖ = ${"%.2f".format(results.Cp)}%
            Sᵖ = ${"%.2f".format(results.Sp)}%
            Vᵖ = ${"%.2f".format(results.Vp)} мг/кг
            Oᵖ = ${"%.2f".format(results.Op)}%
            Aᵖ = ${"%.2f".format(results.Ap)}%
            Wᵖ = ${"%.2f".format(results.Wp)}%
            
            Qp = ${"%.2f".format(results.Qp)} МДж/кг
        """.trimIndent()
        outputFuel.text = resultText
    }

    private fun clearOilInputs() {
        HinputOil.text?.clear()
        CinputOil.text?.clear()
        SinputOil.text?.clear()
        OinputOil.text?.clear()
        VinputOil.text?.clear()
        WinputOil.text?.clear()
        AinputOil.text?.clear()
        QinputOil.text?.clear()
        resultsCard.visibility = View.GONE
    }

    private fun clearAllInputs() {
        clearCoalInputs()
        clearOilInputs()
    }
}