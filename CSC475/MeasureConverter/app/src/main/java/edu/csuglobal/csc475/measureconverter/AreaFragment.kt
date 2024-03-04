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

class AreaFragment : Fragment() {

    private val converter = Converter()
    private lateinit var areaView: View
    private lateinit var txtInitial: EditText
    private lateinit var grpConvertFrom: RadioGroup
    private lateinit var grpConvertTo: RadioGroup
    private lateinit var btnClear: Button
    private lateinit var txtResult: TextView
    private lateinit var lblConvertFrom: TextView
    private lateinit var lblConvertTo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize views
        areaView = inflater.inflate(R.layout.fragment_area, container, false)
        txtInitial = areaView.findViewById<View>(R.id.txtInitialArea) as EditText
        grpConvertFrom = areaView.findViewById<View>(R.id.grpConvertFromArea) as RadioGroup
        grpConvertTo = areaView.findViewById<View>(R.id.grpConvertToArea) as RadioGroup
        btnClear = areaView.findViewById<View>(R.id.btnClearArea) as Button
        txtResult = areaView.findViewById<View>(R.id.txtResultArea) as TextView
        lblConvertFrom = areaView.findViewById<View>(R.id.lblConvertFromArea) as TextView
        lblConvertTo = areaView.findViewById<View>(R.id.lblConvertToArea) as TextView

        //set grpConvertTo and btnClear invisible
        grpConvertTo.visibility = View.INVISIBLE
        btnClear.visibility = View.INVISIBLE

        //create a listener for the radio group
        grpConvertFrom.setOnCheckedChangeListener { group, checkedId ->
            val radio = areaView.findViewById<View>(checkedId) as RadioButton

            //if txtInitial is empty, bold lblInitial and put a Toast saying we need a value entered in the "Value to Convert" field
            if (txtInitial.text.toString().isEmpty() || txtInitial.text.toString() =="0.0") {
                txtInitial.textSize = 20f
                txtInitial.setHint("Enter a value to convert")
            }//end if

            //Otherwise, convert the value based on which radio button was selected
            else {
                when (radio.id) {
                    R.id.btnFromSqInch -> {
                        //set lblConvertFrom to "Convert from Square Inches", set grpConvertFrom to gone, set grpConvertTo and bntClear to visible
                        lblConvertFrom.text = "Convert from Square Inches"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.squareInchesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqYard
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareInchesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqMi
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareInchesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToAcre
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareInches(
                                            converter.squareInchesToSquareFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareInches(
                                            converter.squareInchesToSquareFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMeters(
                                        converter.squareInchesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareInchesToSquareFeet(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnSqInch
                    R.id.btnFromSqYard -> {
                        //set lblConvertFrom to "Convert from Square Yards", set grpConvertFrom to gone, set grpConvertTo and bntClear to visible
                        lblConvertFrom.text = "Convert from Square Yards"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToSqMi
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToAcre
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareYards(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareYards(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMeters(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareYardsToSquareFeet(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToSqFeet
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqMi
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToAcre
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareYards(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareYards(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqKM
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareYardsToSquareFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToSqM
                            }//end when
                        }//end setOnCheckedChangeListener function
                    }//end btnSqYard
                    R.id.btnFromSqMi -> {
                        //set lblConvertFrom to "Convert from Square Miles", set grpConvertFrom to gone, set grpConvertTo and bntClear to visible
                        lblConvertFrom.text = "Convert from Square Miles"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.squareMilesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.squareMilesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqYard
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToSqMi
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareMilesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToAcre
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareMiles(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareMeters(
                                            converter.squareMilesToSquareFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMeters(
                                        converter.squareMilesToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMilesToSquareFeet(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnSqMi
                    R.id.btnFromAcre -> {
                        //set lblConvertFrom to "Convert from Acres", set grpConvertFrom to gone, set grpConvertTo and btnClear tovisible
                        lblConvertFrom.text = "Convert from Acres"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.acresToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.acresToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnAcre
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToAcre
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.acresToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqMi
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareMeters(
                                            converter.acresToSquareFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareMeters(
                                            converter.acresToSquareFeet(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMeters(
                                        converter.acresToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.acresToSquareFeet(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnAcre
                    R.id.btnFromSqCM -> {
                        //set LblConvertFrom to "Convert from Square Centimeters", set grpConvertFrom to gone, set grpConvertTo and btnClear tovisible
                        lblConvertFrom.text = "Convert from Square Centimeters"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareCentimetersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareCentimetersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqYard
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareCentimetersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToAcre
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareCentimetersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqMi
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareCentimetersToSquareMeters(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareCentimetersToSquareMeters(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareFeet(
                                        converter.squareCentimetersToSquareMeters(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnSqCM
                    R.id.btnFromSqKM -> {
                        //set lblConvertFrom to "Convert from Square Kilometers", set grpConvertFrom to gone, set gprConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Square Kilometers"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareKilometersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareKilometersToSquareMeters(
                                                txtInitial.text.toString().toDouble()
                                            )
                                        )
                                    ).toString()
                                }//end btnToSqYard
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareKilometersToSquareMeters(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToAcre
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareMetersToSquareFeet(
                                            converter.squareKilometersToSquareMeters(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToSqMi
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareKilometersToSquareMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareKilometersToSquareMeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareFeet(
                                        converter.squareKilometersToSquareMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnSqKM
                    R.id.btnFromSqM -> {
                        //set lblConvertFrom to "Convert from Square Meters", set grpConvertFrom to gone, set gprConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Square Meters"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE


                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        converter.squareMetersToSquareFeet(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        converter.squareMetersToSquareFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToSqYard
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        converter.squareMetersToSquareFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToAcre
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        converter.squareMetersToSquareFeet(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToSqMi
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareFeet(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnSqM
                    R.id.btnFromSqFeet -> {
                        //set lblConvertFrom to "Convert from Square Feet", set grpConvertFrom to gone, set gprConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Square Feet"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE


                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = areaView.findViewById<View>(checkedId2) as RadioButton

                            //convert the value and display it in txtResult
                            when (radio2.id) {
                                R.id.btnToSqInch -> {
                                    //set lblConvertTo to "Convert to Square Inches" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Inches"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareInches(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToSqInch
                                R.id.btnToSqYard -> {
                                    //set lblConvertTo to "Convert to Square Yards" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Yards"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareYards(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToSqYard
                                R.id.btnToAcre -> {
                                    //set lblConvertTo to "Convert to Acres" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Acres"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToAcres(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToAcre
                                R.id.btnToSqMi -> {
                                    //set lblConvertTo to "Convert to Square Miles" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Miles"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMiles(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToSqMi
                                R.id.btnToSqCM -> {
                                    //set lblConvertTo to "Convert to Square Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareCentimeters(
                                        converter.squareFeetToSquareMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnSqCM
                                R.id.btnToSqKM -> {
                                    //set lblConvertTo to "Convert to Square Kilometers" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Kilometers"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareMetersToSquareKilometers(
                                        converter.squareFeetToSquareMeters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnSqKM
                                R.id.btnToSqM -> {
                                    //set lblConvertTo to "Convert to Square Meters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Meters"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = converter.squareFeetToSquareMeters(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnSqM
                                R.id.btnToSqFeet -> {
                                    //set lblConvertTo to "Convert to Square Feet" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Square Feet"
                                    grpConvertTo.visibility = View.GONE

                                    txtResult.text = txtInitial.text.toString()
                                }//end btnSqFeet
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnSqFeet
                }//end when
                //create a listener for btnClear that clears txtResult, txtInitial, and set all radio buttons to unselected
                btnClear.setOnClickListener {
                    txtResult.text = "0.0"
                    txtInitial.setText("0.0")
                    areaView.findViewById<RadioButton>(R.id.btnFromSqInch).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqYard).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqMi).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromAcre).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqCM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqKM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnFromSqFeet).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqInch).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqYard).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqMi).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToAcre).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqCM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqKM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqM).isChecked = false
                    areaView.findViewById<RadioButton>(R.id.btnToSqFeet).isChecked = false

                    btnClear.visibility = View.INVISIBLE
                    grpConvertTo.visibility = View.INVISIBLE
                    grpConvertFrom.visibility = View.VISIBLE
                    lblConvertFrom.text = "Convert From: "
                    lblConvertTo.text = "Convert To: "

                    //reset the text size and color
                    txtInitial.textSize = 16f
                    txtInitial.setTextColor(resources.getColor(R.color.black))
                    txtInitial.setHintTextColor(resources.getColor(R.color.black))
                    txtInitial.setHint("Enter a value to convert")
                }//end setOnClickListener function
            }//end else

        }//end setOnCheckedChangeListener function

        return areaView
    }//end onCreateView function

    override fun onResume() {
        super.onResume()
        //unselect all radiobuttons
        areaView.findViewById<RadioButton>(R.id.btnFromSqInch).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqYard).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqMi).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromAcre).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqCM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqKM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnFromSqFeet).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqInch).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqYard).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqMi).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToAcre).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqCM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqKM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqM).isChecked = false
        areaView.findViewById<RadioButton>(R.id.btnToSqFeet).isChecked = false

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
}//end AreaFragment class
