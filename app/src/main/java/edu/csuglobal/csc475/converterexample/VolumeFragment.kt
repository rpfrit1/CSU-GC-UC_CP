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

class VolumeFragment : Fragment() {
    private val converter = Converter()
    private lateinit var volumeView: View
    private lateinit var txtInitial: EditText
    private lateinit var grpConvertFrom: RadioGroup
    private lateinit var grpConvertTo: RadioGroup
    private lateinit var txtResult: TextView
    private lateinit var btnClear: Button
    private lateinit var lblConvertFrom:TextView
    private lateinit var lblConvertTo:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize views
        volumeView = inflater.inflate(R.layout.fragment_volume, container, false)
        txtInitial = volumeView.findViewById<View>(R.id.txtInitialVolume) as EditText
        grpConvertFrom = volumeView.findViewById<View>(R.id.grpConvertFromVolume) as RadioGroup
        grpConvertTo = volumeView.findViewById<View>(R.id.grpConvertToVolume) as RadioGroup
        txtResult = volumeView.findViewById<View>(R.id.txtResultVolume) as TextView
        btnClear = volumeView.findViewById<View>(R.id.btnClearVolume) as Button
        lblConvertFrom = volumeView.findViewById(R.id.lblConvertFromArea) as TextView
        lblConvertTo = volumeView.findViewById<View>(R.id.lblConvertToArea) as TextView

        //set grpConvertTo and btnClear invisible
        grpConvertTo.visibility = View.INVISIBLE
        btnClear.visibility = View.INVISIBLE
        //create a listener for the radio group
        grpConvertFrom.setOnCheckedChangeListener { group, checkedId ->
            val radio = volumeView.findViewById<View>(checkedId) as RadioButton

            //if txtInitial is empty, bold lblInitial and put a Toast saying we need a value entered in the "Value to Convert" field
            if (txtInitial.text.toString().isEmpty()) {
                txtInitial.textSize = 20f
                txtInitial.setHint("Enter a value to convert")
            }//end if

            //Otherwise, convert the value based on which radio button was selected
            else {
                when (radio.id) {
                    R.id.btnFromLiter -> {//Set lblConvertFrom to say "Convert from Liters" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Liters"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToGal -> {
                                    //Set lblConvertTo to say "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Gallon in txtResult
                                    txtResult.text = converter.litersToGallons(txtInitial.text.toString().toDouble()).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //Set lblConvertTo to say "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Pint in txtResult
                                    txtResult.text = converter.quartsToPints(converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToPint
                                R.id.btnToQuart -> {
                                    //Set lblConvertTo to say "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Quart in txtResult
                                    txtResult.text = converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble())).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //Set lblConvertTo to say "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(converter.pintsToCups(converter.quartsToPints(converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //Set lblConvertTo to say "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(converter.quartsToPints(converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //Set lblConvertTo to say "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(txtInitial.text.toString().toDouble()).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //Set lblConvertTo to say "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(txtInitial.text.toString().toDouble()).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //Set lblConvertTo to say "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Teaspoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(converter.cupsToTablespoons(converter.pintsToCups(converter.quartsToPints(converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //Set lblConvertTo to say "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Tablespoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(converter.pintsToCups(converter.quartsToPints(converter.gallonsToQuarts(converter.litersToGallons(txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToTable
                                R.id.btnToLiter -> {
                                    //Set lblConvertTo to say "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Liter to Liter in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToLiter
                            }//end when
                        }//end setOnCheckedChangeListener
                            //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                        }//end btnFromLiter
                    R.id.btnFromGal -> {
                        //Set lblConvertFrom to say "Convert from Gallons" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Gallons"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //Set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(
                                        txtInitial.text.toString().toDouble()
                                    ).toString()
                                }//end btnToLiter
                                R.id.btnToPint -> {
                                    //Set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Pint in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                        converter.gallonsToQuarts(
                                            txtInitial.text.toString().toDouble()
                                        )
                                    ).toString()
                                }//end btnToPint
                                R.id.btnToQuart -> {
                                    //Set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Quart in txtResult
                                    txtResult.text = converter.gallonsToQuarts(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //Set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                        converter.pintsToCups(
                                            converter.quartsToPints(
                                                converter.gallonsToQuarts(
                                                    txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //Set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(
                                        converter.quartsToPints(
                                            converter.gallonsToQuarts(
                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //Set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //Set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(
                                        converter.gallonsToLiters(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //Set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Teaspoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                        converter.cupsToTablespoons(
                                            converter.pintsToCups(
                                                converter.quartsToPints(
                                                    converter.gallonsToQuarts(
                                                        txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //Set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Tablespoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                        converter.pintsToCups(
                                            converter.quartsToPints(
                                                converter.gallonsToQuarts(
                                                    txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToTable
                                R.id.btnToGal -> {
                                    //Set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Gallon to Gallon in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToGal
                            }//end when
                        }//end setOnCheckedChangeListener
                            //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromGal
                    R.id.btnFromPint -> {//Set lblConvertFrom to say "Convert from Pints" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Pints"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set gprConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(converter.quartsToGallons(converter.pintsToQuarts(
                                        txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(converter.pintsToQuarts(
                                        txtInitial.text.toString().toDouble())).toString()
                                }//end btnToGal
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Quart in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                        converter.pintsToCups(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(
                                            converter.quartsToGallons(converter.pintsToQuarts(
                                                txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(
                                        converter.gallonsToLiters(
                                            converter.quartsToGallons(converter.pintsToQuarts(
                                                txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(converter.cupsToTablespoons(
                                        converter.pintsToCups(
                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                        converter.pintsToCups(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToTable
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Pint to Pint in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToPint
                            }//end when
                        }//end setOnCheckedChangeListener
                            //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromPint
                    R.id.btnFromQuart -> {//Set lblConvertFrom to say "Convert from Quarts" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Quarts"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(converter.quartsToGallons(
                                        txtInitial.text.toString().toDouble())).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Pint in txtResult
                                    txtResult.text = converter.quartsToPints(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToPint
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                        converter.pintsToCups(converter.quartsToPints(
                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(converter.quartsToPints(
                                        txtInitial.text.toString().toDouble())).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(converter.quartsToGallons(
                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(
                                        converter.gallonsToLiters(converter.quartsToGallons(
                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(converter.cupsToTablespoons(
                                        converter.pintsToCups(
                                            converter.quartsToPints(
                                                txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(converter.pintsToCups(
                                        converter.quartsToPints(
                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToTable
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Quart to Quart in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToQuart
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromQuart
                    R.id.btnFromFlOz -> {//Set lblConvertFrom to say "Convert from Fluid Ounces" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Fluid Ounces"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(
                                        converter.quartsToGallons(converter.pintsToQuarts(
                                            converter.cupsToPints(
                                                converter.ouncesToCups(
                                                    txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(
                                        converter.pintsToQuarts(converter.cupsToPints(
                                            converter.ouncesToCups(
                                                txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Pint in txtResult
                                    txtResult.text = converter.cupsToPints(converter.ouncesToCups(
                                        txtInitial.text.toString().toDouble())).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Quart in txtResult
                                    txtResult.text = converter.pintsToQuarts(converter.cupsToPints(
                                        converter.ouncesToCups(txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToQuart
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(converter.quartsToPints(
                                        converter.ouncesToCups(txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(converter.quartsToGallons(
                                            converter.pintsToQuarts(converter.cupsToPints(
                                                converter.ouncesToCups(
                                                    txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(
                                        converter.gallonsToLiters(converter.quartsToGallons(
                                            converter.pintsToQuarts(converter.cupsToPints(
                                                converter.ouncesToCups(
                                                    txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                        converter.cupsToTablespoons(converter.pintsToCups(
                                            converter.quartsToPints(converter.ouncesToCups(
                                                txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Fluid Ounce to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                        converter.pintsToCups(converter.quartsToPints(
                                            converter.ouncesToCups(
                                                txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToTable
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the3 conversion from Fluid Ounce to Fluid Ounce in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToFlOz
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromFlOz
                    R.id.btnFromCup -> {
                        //Set lblConvertFrom to say "Convert from Cups" and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Cups"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(converter.quartsToGallons(
                                                converter.pintsToQuarts(converter.cupsToPints(
                                                        txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(
                                                    converter.pintsToQuarts(converter.cupsToPints(
                                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Pint in txtResult
                                    txtResult.text = converter.cupsToPints(
                                                txtInitial.text.toString().toDouble()).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Quart in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                            converter.cupsToPints(txtInitial.text.toString().toDouble())).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                                txtInitial.text.toString().toDouble()).toString()
                                }//end btnToFlOz
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                                converter.gallonsToLiters(converter.quartsToGallons(
                                                                converter.pintsToQuarts(converter.cupsToPints(
                                                                                    txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Cubic Centimeter in txtResult
                                    txtResult.text = converter.litersToCubicCentimeters(
                                            converter.gallonsToLiters(converter.quartsToGallons(
                                                            converter.pintsToQuarts(converter.cupsToPints(
                                                                                txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                        converter.cupsToTablespoons(
                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                        txtInitial.text.toString().toDouble()).toString()
                                }//end btnToTable
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cup to Cup in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToCup
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromCup
                    R.id.btnFromML -> {
                        //set lblConvertFrom to say "Convert from Milliliters", set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Milliliters"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Liter in txtResult
                                    txtResult.text = converter.millilitersToLiters(
                                                txtInitial.text.toString().toDouble()).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Gallon in txtResult
                                    txtResult.text = converter.litersToGallons(
                                                    converter.millilitersToLiters(
                                                                txtInitial.text.toString().toDouble())).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Pint in txtResult
                                    txtResult.text = converter.pintsToQuarts(converter.quartsToGallons(
                                                converter.litersToGallons(
                                                        converter.millilitersToLiters(
                                                                    txtInitial.text.toString().toDouble())))).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Quart in txtResult
                                    txtResult.text = converter.gallonsToQuarts(
                                                    converter.litersToGallons(
                                                            converter.millilitersToLiters(
                                                                        txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                        converter.pintsToCups(converter.quartsToPints(
                                            converter.gallonsToQuarts(converter.litersToGallons(converter.millilitersToLiters(
                                                txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(
                                            converter.quartsToPints(converter.gallonsToQuarts(
                                                    converter.litersToGallons(converter.millilitersToLiters(
                                                                    txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToCup
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Cubic Centimeter in txtResult
                                    txtResult.text = converter.cubicMetersToCubicCentimeters(
                                                converter.litersToCubicMeters(
                                                                    converter.millilitersToLiters(
                                                                                    txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                            converter.cupsToTablespoons(
                                                    converter.pintsToCups(converter.quartsToPints(
                                                                converter.gallonsToQuarts(converter.litersToGallons(converter.millilitersToLiters(
                                                                            txtInitial.text.toString().toDouble()))))))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                        converter.pintsToCups(converter.quartsToPints(
                                            converter.gallonsToQuarts(converter.litersToGallons(converter.millilitersToLiters(txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToTable
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Milliliter to Milliliter in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToML
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE

                    }//end btnFromML
                    R.id.btnFromCC -> {
                        //set lblConvertFrom to say "Convert from Cubic Centimeters", and set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Cubic Centimeters"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Liter in txtResult
                                    txtResult.text = converter.cubicCentimetersToLiters(
                                                                            txtInitial.text.toString().toDouble()).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Gallon in txtResult
                                    txtResult.text = converter.litersToGallons(
                                                                                converter.cubicCentimetersToLiters(
                                                                                                                        txtInitial.text.toString().toDouble())).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Pint in txtResult
                                    txtResult.text = converter.quartsToPints(
                                        converter.gallonsToQuarts(
                                            converter.litersToGallons(
                                                converter.cubicCentimetersToLiters(
                                                    txtInitial.text.toString().toDouble())))).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Quart in txtResult
                                    txtResult.text = converter.gallonsToQuarts(
                                            converter.litersToGallons(
                                                    converter.cubicCentimetersToLiters(
                                                                txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                            converter.pintsToCups(
                                                    converter.quartsToPints(
                                                                converter.gallonsToQuarts(
                                                                            converter.litersToGallons(
                                                                                                    converter.cubicCentimetersToLiters(
                                                                                                                                    txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Cup in txtResult
                                    txtResult.text = converter.pintsToCups(
                                                converter.quartsToPints(
                                                        converter.gallonsToQuarts(
                                                                    converter.litersToGallons(
                                                                                converter.cubicCentimetersToLiters(
                                                                                                                            txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                                                                converter.cubicCentimetersToLiters(
                                                                                                                            txtInitial.text.toString().toDouble())).toString()
                                }//end btnToML
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Teaspoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Teaspoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                        converter.cupsToTablespoons(
                                            converter.pintsToCups(
                                                converter.quartsToPints(
                                                    converter.gallonsToQuarts(
                                                        converter.litersToGallons(
                                                            converter.cubicCentimetersToLiters(
                                                                        txtInitial.text.toString().toDouble()))))))).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Tablespoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tablespoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Table Spoon in txtResult
                                    txtResult.text = converter.cupsToTablespoons(
                                            converter.pintsToCups(
                                                    converter.quartsToPints(
                                                                converter.gallonsToQuarts(
                                                                                converter.litersToGallons(
                                                                                                            converter.cubicCentimetersToLiters(
                                                                                                                                        txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToTable
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Cubic Centimeter to Cubic Centimeter in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToCC
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromCC
                    R.id.btnFromTea -> {
                        //set lblConvertFrom to "Convert from Teaspoons", set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Teaspoons"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(
                                        converter.quartsToGallons(
                                            converter.pintsToQuarts(
                                                converter.cupsToPints(
                                                    converter.tablespoonsToCups(
                                                        converter.teaspoonsToTablespoons(
                                                            txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(
                                            converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                                converter.tablespoonsToCups(
                                                                        converter.teaspoonsToTablespoons(
                                                                                txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Pint in txtResult
                                    txtResult.text = converter.cupsToPints(
                                                converter.tablespoonsToCups(
                                                            converter.teaspoonsToTablespoons(
                                                                        txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Quart in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                                    converter.tablespoonsToCups(
                                                                                converter.teaspoonsToTablespoons(
                                                                                            txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                                        converter.tablespoonsToCups(
                                                                        converter.teaspoonsToTablespoons(
                                                                                        txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Cup in txtResult
                                    txtResult.text = converter.tablespoonsToCups(
                                                            converter.teaspoonsToTablespoons(
                                                                                    txtInitial.text.toString().toDouble())).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(
                                            converter.quartsToGallons(
                                                converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                        converter.tablespoonsToCups(
                                                            converter.teaspoonsToTablespoons(
                                                                txtInitial.text.toString().toDouble()))))))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Cubic Centimeter in txtResult
                                    txtResult.text = converter.cubicMetersToCubicCentimeters(
                                        converter.litersToCubicMeters(
                                            converter.gallonsToLiters(
                                                converter.quartsToGallons(
                                                    converter.pintsToQuarts(
                                                        converter.cupsToPints(
                                                            converter.tablespoonsToCups(
                                                                converter.teaspoonsToTablespoons(
                                                                    txtInitial.text.toString().toDouble())))))))).toString()
                                }//end btnToCC
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Table Spoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Table Spoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Table Spoon in txtResult
                                    txtResult.text = converter.teaspoonsToTablespoons(
                                            txtInitial.text.toString().toDouble()).toString()
                                }//end btnToTable
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Tea Spoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tea Spoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Tea Spoon to Tea Spoon in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToTea
                            }//end when
                        }//end setOnCheckedChangeListener
                    }//end btnFromTea
                    R.id.btnFromTable -> {
                        //set lblConvertFrom to "Convert from Tablespoons", set grpConvertFrom to gone, set grpConvertTo and btnClear to visible
                        lblConvertFrom.text = "Convert from Tablespoons"
                        grpConvertFrom.visibility = View.GONE
                        grpConvertTo.visibility = View.VISIBLE
                        btnClear.visibility = View.VISIBLE

                        //create a listener for grpConvertTo
                        grpConvertTo.setOnCheckedChangeListener { group2, checkedId2 ->
                            val radio2 = volumeView.findViewById<View>(checkedId2) as RadioButton
                            when (radio2.id) {
                                R.id.btnToLiter -> {
                                    //set lblConvertTo to "Convert to Liters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Liters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Liter in txtResult
                                    txtResult.text = converter.gallonsToLiters(
                                        converter.quartsToGallons(
                                            converter.pintsToQuarts(
                                                converter.cupsToPints(
                                                    converter.tablespoonsToCups(
                                                        txtInitial.text.toString().toDouble()))))).toString()
                                }//end btnToLiter
                                R.id.btnToGal -> {
                                    //set lblConvertTo to "Convert to Gallons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Gallons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Gallon in txtResult
                                    txtResult.text = converter.quartsToGallons(
                                            converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                                converter.tablespoonsToCups(
                                                                        txtInitial.text.toString().toDouble())))).toString()
                                }//end btnToGal
                                R.id.btnToPint -> {
                                    //set lblConvertTo to "Convert to Pints" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Pints"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Pint in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                                converter.cupsToPints(
                                                                converter.tablespoonsToCups(
                                                                            txtInitial.text.toString().toDouble()))).toString()
                                }//end R.id.btnToPint
                                R.id.btnToQuart -> {
                                    //set lblConvertTo to "Convert to Quarts" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Quarts"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Quart in txtResult
                                    txtResult.text = converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                                    converter.tablespoonsToCups(
                                                                                    txtInitial.text.toString().toDouble()))).toString()
                                }//end btnToQuart
                                R.id.btnToFlOz -> {
                                    //set lblConvertTo to "Convert to Fluid Ounces" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Fluid Ounces"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Fluid Ounce in txtResult
                                    txtResult.text = converter.cupsToOunces(
                                                        converter.tablespoonsToCups(
                                                                        txtInitial.text.toString().toDouble())).toString()
                                }//end btnToFlOz
                                R.id.btnToCup -> {
                                    //set lblConvertTo to "Convert to Cups" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cups"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Cup in txtResult
                                    txtResult.text = converter.tablespoonsToCups(
                                                            txtInitial.text.toString().toDouble()).toString()
                                }//end btnToCup
                                R.id.btnToML -> {
                                    //set lblConvertTo to "Convert to Milliliters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Milliliters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Milliliter in txtResult
                                    txtResult.text = converter.litersToMilliliters(
                                        converter.gallonsToLiters(
                                            converter.quartsToGallons(
                                                converter.pintsToQuarts(
                                                    converter.cupsToPints(
                                                        converter.tablespoonsToCups(
                                                            txtInitial.text.toString().toDouble())))))).toString()
                                }//end btnToML
                                R.id.btnToCC -> {
                                    //set lblConvertTo to "Convert to Cubic Centimeters" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Cubic Centimeters"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Cubic Centimeter in txtResult
                                    txtResult.text = converter.cubicMetersToCubicCentimeters(
                                        converter.litersToCubicMeters(
                                            converter.gallonsToLiters(
                                                converter.quartsToGallons(
                                                    converter.pintsToQuarts(
                                                        converter.cupsToPints(
                                                            converter.tablespoonsToCups(
                                                                txtInitial.text.toString().toDouble()))))))).toString()
                                }//end btnToCC
                                R.id.btnToTea -> {
                                    //set lblConvertTo to "Convert to Tea Spoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Tea Spoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Tea Spoon in txtResult
                                    txtResult.text = converter.tablespoonsToTeaspoons(
                                            txtInitial.text.toString().toDouble()).toString()
                                }//end btnToTea
                                R.id.btnToTable -> {
                                    //set lblConvertTo to "Convert to Table Spoons" and set grpConvertTo to gone
                                    lblConvertTo.text = "Convert to Table Spoons"
                                    grpConvertTo.visibility = View.GONE

                                    //put the conversion from Table Spoon to Table Spoon in txtResult
                                    txtResult.text = txtInitial.text.toString()
                                }//end btnToTable
                            }//end when
                        }//end setOnCheckedChangeListener
                        //set txtResult to
                        // set btnClear to visible
                        btnClear.visibility = View.VISIBLE
                    }//end btnFromTable
                }//end when
            }//end else

            //reset the text size and color
            txtInitial.textSize = 16f
            txtInitial.setTextColor(resources.getColor(R.color.black))
            txtInitial.setHintTextColor(resources.getColor(R.color.black))
            txtInitial.setHint("Enter a value to convert")
        }//end setOnCheckedChangeListener function

        //create a listener for btnClear that clears txtResult, txtInitial, and both RadioGroups
        btnClear.setOnClickListener {
            txtResult.text = "0.0"
            txtInitial.setText("0.0")
            //unselect all radiobuttons
            volumeView.findViewById<RadioButton>(R.id.btnFromCC).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnFromLiter).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnFromML).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnFromTea).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnFromTable).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnToCC).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnToLiter).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnToML).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnToTea).isChecked = false
            volumeView.findViewById<RadioButton>(R.id.btnToTable).isChecked = false
            btnClear.visibility = View.INVISIBLE
        }//end setOnClickListener function
        return volumeView
    }//end onCreateView function


    override fun onResume() {
        super.onResume()
        //unselect all radiobuttons
        volumeView.findViewById<RadioButton>(R.id.btnFromCC).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnFromLiter).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnFromML).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnFromTea).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnFromTable).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnToCC).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnToLiter).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnToML).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnToTea).isChecked = false
        volumeView.findViewById<RadioButton>(R.id.btnToTable).isChecked = false

        //set the text size to 16
        txtInitial.textSize = 16f
        //set the text size to 16
        txtResult.textSize = 16f
        //set the visibility of grpConvertTo to invisible
        grpConvertTo.visibility = View.INVISIBLE
        //set the visibility of btnClear to invisible
        btnClear.visibility = View.INVISIBLE
        //empty contents of txtResult and txtInitial
        txtResult.text = "0.0"
        //set the text of txtInitial to 0.0
        txtInitial.setText("0.0")
    }//end onResume function
}//end VolumeFragment class
