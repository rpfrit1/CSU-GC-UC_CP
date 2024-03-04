package edu.csuglobal.csc475.converterexample


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

class LengthFragment : Fragment() {

    private val converter = Converter()
    private lateinit var lengthView: View
    private lateinit var txtInitial: EditText
    private lateinit var grpConvertFrom: RadioGroup
    private lateinit var grpConvertTo: RadioGroup
    private lateinit var btnClear: Button
    private lateinit var txtResult: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize views
        lengthView = inflater.inflate(R.layout.fragment_length, container, false)
        txtInitial = lengthView.findViewById(R.id.txtInitialLen)
        grpConvertFrom = lengthView.findViewById(R.id.grpConvertFromLen)
        grpConvertTo = lengthView.findViewById(R.id.grpConvertToLen)
        btnClear = lengthView.findViewById(R.id.btnClearLen)
        txtResult = lengthView.findViewById(R.id.txtResultLen)

        //set grpConvertTo and btnClear invisible
        grpConvertTo.visibility = View.INVISIBLE
        btnClear.visibility = View.INVISIBLE

        //create a listener for the radio group
        grpConvertFrom.setOnCheckedChangeListener { group, checkedId ->
            val radio = lengthView.findViewById<View>(checkedId!!) as RadioButton

            //if txtInitial is empty, bold lblInitial and put a Toast saying we need a value entered in the "Value to Convert" field
            if (txtInitial.text.toString().isEmpty()) {
                txtInitial.textSize = 20f
                txtInitial.setHint("Enter a value to convert")
            }//end if

            //Otherwise, convert the value based on which radio button was selected
            else {
                //set btnClear to visible
                btnClear.visibility = View.VISIBLE

                when (radio.id) {
                    R.id.btnFromMeter -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        converter.metersToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        converter.metersToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.metersToFeet(txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        converter.metersToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToInch
                                }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromMeter
                    R.id.btnFromCM -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.centimetersToMeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        converter.centimetersToMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        converter.metersToFeet(
                                            converter.centimetersToMeters(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        converter.metersToFeet(
                                            converter.centimetersToMeters(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.metersToFeet(
                                        converter.centimetersToMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        converter.metersToFeet(
                                            converter.centimetersToMeters(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToInch
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromCM
                    R.id.btnFromKM -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.kilometersToMeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        converter.metersToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        converter.metersToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.metersToFeet(
                                        converter.kilometersToMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        converter.metersToFeet(
                                            converter.kilometersToMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToInch
                }//end when

                //create a listener for btnClear that clears txtResult, txtInitial, and both RadioGroups
                btnClear.setOnClickListener {
                    txtResult.text = ""
                    txtInitial.setText("")
                    grpConvertFrom.clearCheck()
                    grpConvertTo.clearCheck()
                    btnClear.visibility = View.INVISIBLE
                }//end setOnClickListener function
            }//end else

            //reset the text size and color
            txtInitial.textSize = 16f
            txtInitial.setTextColor(resources.getColor(R.color.black))
            txtInitial.setHintTextColor(resources.getColor(R.color.black))
            txtInitial.setHint("Enter a value to convert")
            btnClear.visibility = View.VISIBLE
        }//end setOnCheckedChangeListener function
                    R.id.btnFromMi -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.feetToMeters(
                                        converter.milesToFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        converter.feetToMeters(
                                            converter.milesToFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        converter.feetToMeters(
                                            converter.milesToFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        converter.feetToMiles(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.feetToMiles(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        converter.feetToMiles(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToInch
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromMi
                    R.id.btnFromYard -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.feetToMeters(
                                        converter.yardsToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        converter.feetToMeters(
                                            converter.yardsToFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        converter.feetToMeters(
                                            converter.yardsToFeet(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        converter.yardsToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.yardsToFeet(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        converter.yardsToFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToInch
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromYard
                    R.id.btnFromFeet -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.feetToMeters(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        converter.feetToMeters(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        converter.feetToMeters(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = converter.feetToInches(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToInch
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromFeet
                    R.id.btnFromInch -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = lengthView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToMeter -> {
                                    txtResult.text = converter.feetToMeters(
                                        converter.inchesToFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToMeter
                                R.id.btnToCM -> {
                                    txtResult.text = converter.metersToCentimeters(
                                        converter.feetToMeters(
                                            converter.inchesToFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToCM
                                R.id.btnToKM -> {
                                    txtResult.text = converter.metersToKilometers(
                                        converter.feetToMeters(
                                            converter.inchesToFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end R.id.btnToKM
                                R.id.btnToMi -> {
                                    txtResult.text = converter.feetToMiles(
                                        converter.inchesToFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToMi
                                R.id.btnToYard -> {
                                    txtResult.text = converter.feetToYards(
                                        converter.inchesToFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end R.id.btnToYard
                                R.id.btnToFeet -> {
                                    txtResult.text = converter.inchesToFeet(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end R.id.btnToFeet
                                R.id.btnToInch -> {
                                    txtResult.text = txtInitial.text.toString()
                                }//end R.id.btnToInch
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnFromInch
                }//end when

                //create a listener for btnClear that clears txtResult, txtInitial, and set all radio buttons to unselected
                btnClear.setOnClickListener {
                    txtResult.text = "0.0"
                    txtInitial.setText("0.0")
                    lengthView.findViewById<RadioButton>(R.id.btnFromMeter).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromCM).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromKM).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromMi).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromYard).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromFeet).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnFromInch).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToMeter).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToCM).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToKM).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToMi).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToYard).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToFeet).isChecked = false
                    lengthView.findViewById<RadioButton>(R.id.btnToInch).isChecked = false

                    btnClear.visibility = View.INVISIBLE
                    grpConvertTo.visibility = View.INVISIBLE

                    //reset the text size
                    txtInitial.textSize = 16f
                }//end setOnClickListener function
            }//end else
        }//end setOn
        return lengthView
    }//end onCreateView function

    override fun onResume() {
        super.onResume()
        //unselect all radiobuttons
        lengthView.findViewById<RadioButton>(R.id.btnFromMeter).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromCM).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromKM).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromMi).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromYard).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromFeet).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnFromInch).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToMeter).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToCM).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToKM).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToMi).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToYard).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToFeet).isChecked = false
        lengthView.findViewById<RadioButton>(R.id.btnToInch).isChecked = false
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
}//end LengthFragment class
