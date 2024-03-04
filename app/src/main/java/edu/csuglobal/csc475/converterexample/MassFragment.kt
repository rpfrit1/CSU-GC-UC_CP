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

class MassFragment : Fragment() {

    private val converter = Converter()
    private lateinit var txtInitial: EditText
    private lateinit var grpConvertFrom: RadioGroup
    private lateinit var grpConvertTo: RadioGroup
    private lateinit var btnClear: Button
    private lateinit var txtResult: TextView
    private lateinit var massView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize views
        massView = inflater.inflate(R.layout.fragment_mass, container, false)
        txtInitial = massView.findViewById<View>(R.id.txtInitialMass) as EditText
        grpConvertFrom = massView.findViewById<View>(R.id.grpConvertFromMass) as RadioGroup
        grpConvertTo = massView.findViewById<View>(R.id.grpConvertToMass) as RadioGroup
        btnClear = massView.findViewById<View>(R.id.btnClearMass) as Button
        txtResult = massView.findViewById<View>(R.id.txtResultMass) as TextView

        //set grpConvertTo and btnClear invisible
        grpConvertTo.visibility = View.INVISIBLE
        btnClear.visibility = View.INVISIBLE

        //create a listener for the radio group
        grpConvertFrom.setOnCheckedChangeListener { group, checkedId ->
            val radio = massView.findViewById<View>(checkedId) as RadioButton

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
                    R.id.btnFromKG -> {
                        //set grpConvertTo visible, set R.id.btnToKG to invisible
                        grpConvertTo.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = massView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when(radio2.id) {
                                R.id.btnToGram -> {
                                    txtResult.text = converter.kilogramsToGrams(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToGram
                                R.id.btnToLB -> {
                                    txtResult.text = converter.kilogramsToPounds(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToLB
                                R.id.btnToOnce -> {
                                    txtResult.text = converter.poundsToOunces(
                                        converter.kilogramsToPounds(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToOnce
                                R.id.btnToTon -> {
                                    txtResult.text = converter.poundsToTons(
                                        converter.kilogramsToPounds(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToTon
                            }//end when
                    }//end setOnCheckedChangeListener function
                    }//end btnKG
                    R.id.btnGram -> {
                        //set grpConvertTo visible, set R.id.btnToGram to invisible
                        grpConvertTo.visibility = View.VISIBLE
                        val btnToGram = massView.findViewById<View>(R.id.btnToGram) as RadioButton

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = massView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToKG -> {
                                    txtResult.text = converter.gramsToKilograms(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToKG
                                R.id.btnToLB -> {
                                    txtResult.text = converter.kilogramsToPounds(
                                        converter.gramsToKilograms(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToLB
                                R.id.btnToOnce -> {
                                    txtResult.text = converter.poundsToOunces(
                                        converter.kilogramsToPounds(
                                            converter.gramsToKilograms(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToOnce
                                R.id.btnToTon -> {
                                    txtResult.text = converter.poundsToTons(
                                        converter.kilogramsToPounds(
                                            converter.gramsToKilograms(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToTon
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnGram
                    R.id.btnLB -> {
                        //set grpConvertTo visible, set R.id.btnToLB to invisible
                        grpConvertTo.visibility = View.VISIBLE
                        val btnToLB = massView.findViewById<View>(R.id.btnToLB) as RadioButton

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = massView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToKG -> {
                                    txtResult.text = converter.poundsToKilograms(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToKG
                                R.id.btnToGram -> {
                                    txtResult.text = converter.kilogramsToGrams(
                                        converter.poundsToKilograms(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToGram
                                R.id.btnToOnce -> {
                                    txtResult.text = converter.poundsToOunces(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToOnce
                                R.id.btnToTon -> {
                                    txtResult.text = converter.poundsToTons(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToTon
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnLB
                    R.id.btnOnce -> {
                        //set grpConvertTo visible, set R.id.btnToOnce to invisible
                        grpConvertTo.visibility = View.VISIBLE
                        val btnToOnce = massView.findViewById<View>(R.id.btnToOnce) as RadioButton

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = massView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToKG -> {
                                    txtResult.text = converter.poundsToKilograms(
                                        converter.ouncesToPounds(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToKG
                                R.id.btnToGram -> {
                                    txtResult.text = converter.kilogramsToGrams(
                                        converter.poundsToKilograms(
                                            converter.ouncesToPounds(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToGram
                                R.id.btnToLB -> {
                                    txtResult.text = converter.ouncesToPounds(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToLB
                                R.id.btnToTon -> {
                                    txtResult.text = converter.poundsToTons(
                                        converter.ouncesToPounds(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToTon
                            }//end when
                    }//end setOnCheckedChangeListener function
                    }//end btnOnce
                    R.id.btnTon -> {
                        //set grpConvertTo visible, set R.id.btnToTon to invisible
                        grpConvertTo.visibility = View.VISIBLE
                        val btnToTon = massView.findViewById<View>(R.id.btnToTon) as RadioButton

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = massView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToKG -> {
                                    txtResult.text = converter.poundsToKilograms(
                                        converter.tonsToPounds(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToKG
                                R.id.btnToGram -> {
                                    txtResult.text = converter.kilogramsToGrams(
                                        converter.poundsToKilograms(
                                            converter.tonsToPounds(
                                                txtInitial.text.toString().toDouble()))).toString()
                    }//end btnToGram
                                R.id.btnToLB -> {
                                    txtResult.text = converter.tonsToPounds(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToLB
                                R.id.btnToOnce -> {
                                    txtResult.text = converter.poundsToOunces(
                                        converter.tonsToPounds(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToOnce
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnTon
                }//end when

                        //create a listener for btnClear that clears txtResult, txtInitial, and set all radio buttons to unselected
                        btnClear.setOnClickListener {
                            txtResult.text = "0.0"
                            txtInitial.setText("0.0")
                            massView.findViewById<RadioButton>(R.id.btnFromKG).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnGram).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnLB).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnOnce).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnTon).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnToKG).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnToGram).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnToLB).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnToOnce).isChecked = false
                            massView.findViewById<RadioButton>(R.id.btnToTon).isChecked = false

                            btnClear.visibility = View.INVISIBLE
                            grpConvertTo.visibility = View.INVISIBLE

                            //reset the text size
                            txtInitial.textSize = 16f
            }//end setOnClickListener function

        }//end else
        }//end setOnClickListener function

        //return the view
        return massView
    }//end onCreateView function

    override fun onResume() {
        super.onResume()
        //unselect all radiobuttons
        massView.findViewById<RadioButton>(R.id.btnFromKG).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnGram).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnLB).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnOnce).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnTon).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToKG).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToGram).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToGram).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToLB).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToOnce).isChecked = false
        massView.findViewById<RadioButton>(R.id.btnToTon).isChecked = false
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
}//end MassFragment class
