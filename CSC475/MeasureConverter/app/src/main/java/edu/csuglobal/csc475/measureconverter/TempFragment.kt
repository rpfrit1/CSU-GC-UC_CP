package edu.csuglobal.csc475.measureconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TempFragment : Fragment() {
    private val converter = Converter()
    private lateinit var tempView: View
    private lateinit var txtInitial: EditText
    private lateinit var grpConvertFrom: RadioGroup
    private lateinit var grpConvertTo: RadioGroup
    private lateinit var txtResult: TextView
    private lateinit var btnClear: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {//initialize views
        tempView = inflater.inflate(R.layout.fragment_temp, container, false)
        txtInitial = tempView.findViewById<View>(R.id.txtInitialTemp) as EditText
        grpConvertFrom = tempView.findViewById<View>(R.id.grpConvertFromTemp) as RadioGroup
        grpConvertTo = tempView.findViewById<View>(R.id.grpConvertToTemp) as RadioGroup
        btnClear = tempView.findViewById<View>(R.id.btnClearTemp) as Button
        txtResult = tempView.findViewById<View>(R.id.txtResultTemp) as TextView

        //create a listener for the radio group
        grpConvertFrom.setOnCheckedChangeListener { group, checkedId ->
            val radio = tempView.findViewById<View>(checkedId) as RadioButton

            //if txtInitial is empty, bold lblInitial and put a Toast saying we need a value entered in the "Value to Convert" field
            if (txtInitial.text.toString().isEmpty()) {
                txtInitial.textSize = 20f
                txtInitial.setHint("Enter a value to convert")
            }//end if

            //Otherwise, convert the value based on which radio button was selected
            else {
                //make grpConvertTo and btnClear visible
                grpConvertTo.visibility = View.VISIBLE
                btnClear.visibility = View.VISIBLE

                when (radio.id) {
                    R.id.btnFromCelsius -> {
                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = tempView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToFahrenheit -> {
                                    txtResult.text =
                                        converter.celsiusToFahrenheit(
                                            txtInitial.text.toString().toDouble()
                                        ).toString()
                                }//end btnToFahrenheit
                                R.id.btnToKelvin -> {
                                    txtResult.text =
                                        converter.celsiusToKelvin(
                                            txtInitial.text.toString().toDouble()
                                        )
                                            .toString()
                                }//end btnToKelvin
                                R.id.btnToRankine -> {
                                    txtResult.text = converter.fahrenheitToRankine(
                                        converter.celsiusToFahrenheit(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToRankine
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnMeter
                    R.id.btnFromFahrenheit -> {
                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = tempView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToCelsius -> {
                                    txtResult.text =
                                        converter.fahrenheitToCelsius(
                                            txtInitial.text.toString().toDouble()
                                        ).toString()
                                }//end btnToCelsius
                                R.id.btnToKelvin -> {
                                    txtResult.text =
                                        converter.celsiusToKelvin(
                                            converter.fahrenheitToCelsius(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                            .toString()
                                }//end btnToKelvin
                                R.id.btnToRankine -> {
                                    txtResult.text = converter.fahrenheitToRankine(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToRankine
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnFahrenheit
                    R.id.btnFromKelvin -> {
                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = tempView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToCelsius -> {
                                    txtResult.text =
                                        converter.kelvinToCelsius(
                                            txtInitial.text.toString().toDouble()
                                        ).toString()
                                }//end btnToCelsius
                                R.id.btnToFahrenheit -> {
                                    txtResult.text =
                                        converter.celsiusToFahrenheit(
                                            converter.kelvinToCelsius(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        ).toString()
                                }//end btnToFahrenheit
                                R.id.btnToRankine -> {
                                    txtResult.text = converter.fahrenheitToRankine(
                                        converter.celsiusToFahrenheit(
                                            converter.kelvinToCelsius(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToRankine
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnKelvin
                    R.id.btnFromRankine -> {
                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = tempView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToCelsius -> {
                                    txtResult.text =
                                        converter.fahrenheitToCelsius(
                                            converter.rankineToFahrenheit(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        ).toString()
                                }//end btnToCelsius
                                R.id.btnToFahrenheit -> {
                                    txtResult.text =
                                        converter.rankineToFahrenheit(
                                            txtInitial.text.toString().toDouble()
                                        ).toString()
                                }//end btnToFahrenheit
                                R.id.btnToKelvin -> {
                                    txtResult.text =
                                        converter.celsiusToKelvin(
                                            converter.fahrenheitToCelsius(
                                                converter.rankineToFahrenheit(
                                                    txtInitial.text.toString().toDouble()
                                                )
                                            )
                                        ).toString()
                                }//end btnToKelvin
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnFromRankine
                }//end when
                //create a listener for btnClear that clears txtResult, txtInitial, and both RadioGroups
                btnClear.setOnClickListener {
                    txtResult.text = ""
                    txtInitial.setText("")
                    grpConvertFrom.clearCheck()
                    grpConvertTo.clearCheck()
                    btnClear.visibility = View.INVISIBLE

                    //reset the text size and color
                    txtInitial.textSize = 16f
                    txtInitial.setTextColor(resources.getColor(R.color.black))
                    txtInitial.setHintTextColor(resources.getColor(R.color.black))
                    txtInitial.setHint("Enter a value to convert")
                }//end setOnClickListener function

                //create a listener for btnClear that clears txtResult, txtInitial, and set all radio buttons to unselected
                btnClear.setOnClickListener {
                    txtResult.text = "0.0"
                    txtInitial.setText("0.0")
                    tempView.findViewById<RadioButton>(R.id.btnFromKelvin).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnFromCelsius).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnFromFahrenheit).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnFromRankine).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnToKelvin).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnToCelsius).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnToFahrenheit).isChecked = false
                    tempView.findViewById<RadioButton>(R.id.btnToRankine).isChecked = false

                    btnClear.visibility = View.INVISIBLE
                    grpConvertTo.visibility = View.INVISIBLE

                    //reset the text size
                    txtInitial.textSize = 16f
                }//end setOnClickListener function
            }//end else

        }//end setOnCheckedChangeListener function
        return tempView
    }//end onCreateView function


    override fun onResume() {
        super.onResume()
        //unselect all radiobuttons
        tempView.findViewById<RadioButton>(R.id.btnFromKelvin).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnFromCelsius).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnFromFahrenheit).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnFromRankine).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnToKelvin).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnToCelsius).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnToFahrenheit).isChecked = false
        tempView.findViewById<RadioButton>(R.id.btnToRankine).isChecked = false

        //set the text size to 16
        txtInitial.textSize = 16f
        //set the text size to 16
        txtResult.textSize = 16f
        //set the visibility of grpConvertTo to invisible
        grpConvertTo.visibility = View.INVISIBLE
        //set the visibility of btnClear to invisible
        btnClear.visibility = View.INVISIBLE
        //set the text of txtResult to 0.0
        txtResult.text = "0.0"
        //set the text of txtInitial to 0.0
        txtInitial.setText("0.0")
    }//end onResume function
}//end TempFragment class
