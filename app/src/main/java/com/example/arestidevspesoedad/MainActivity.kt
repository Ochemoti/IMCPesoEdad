package com.example.arestidevspesoedad

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.arestidevspesoedad.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false


    lateinit var binding: ActivityMainBinding

    private var weight: Int = 60
    private var age: Int = 30

    private var currentHeight:Int = 120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initUI()
        setWeight()
        setAge()


    }


    private fun initListeners() {
        binding.viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        binding.viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        binding.rsHeight.addOnChangeListener { _, value, _ ->

            //Tambien se puede :

            //val df = DecimalFormat("#.##")
            //val result = df.format(value)
            //binding.tvHeight.text="$value cm"
            currentHeight=value.toInt()
            binding.tvHeight.text = value.toInt().toString()
        }

        binding.btnPlusWeight.setOnClickListener {
            weight += 1
            setWeight()
        }
        binding.btnSubstractWeight.setOnClickListener {
            weight -= 1
            setWeight()
        }

        binding.btnPlusAge.setOnClickListener {
            age += 1
            setAge()
        }
        binding.btnSubstractAge.setOnClickListener {
            age -= 1
            setAge()
        }
        binding.btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val imc = weight.toDouble()/((currentHeight.toDouble()/100)*(currentHeight.toDouble()/100))
        binding.tvIMC.text= imc.toString()
        Log.i("chemo","el imc es $imc")
    }

    private fun setAge() {
        binding.tvAge.text = age.toString()
    }

    private fun setWeight() {
        binding.tvWeight.text = weight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    private fun setGenderColor() {


        binding.viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        binding.viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))


    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {


        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected

        } else {
            R.color.background_component

        }


        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
    }


}