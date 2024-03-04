package edu.csuglobal.csc475.measureconverter

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ConversionUnitTest {

    private val converter = Converter()
    //create tests for functions that convert between metric length and imperial length
    @Test
    fun convertMetersToFeet() {
        assertEquals(3.280839895, converter.metersToFeet(1.0), 0.01)
    }//end convertMetersToFeet

    @Test
    fun convertFeetToMeters() {
        assertEquals(0.3048, converter.feetToMeters(1.0), 0.01)
    }//end convertFeetToMeters

    @Test
    fun convertKilometersToMiles() {
        assertEquals(0.621371192, converter.kilometersToMiles(1.0), 0.01)
    }//end convertKilometersToMiles

    @Test
    fun convertMilesToKilometers() {
        assertEquals(1.609344, converter.milesToKilometers(1.0), 0.01)
    }//end convertMilesToKilometers

    //create tests for functions that convert between metric weight and imperial weight
    @Test
    fun convertKilogramsToPounds() {
        assertEquals(2.20462, converter.kilogramsToPounds(1.0), 0.01)
    }//end convertKilogramsToPounds

    @Test
    fun convertPoundsToKilograms() {
        assertEquals(0.453592, converter.poundsToKilograms(1.0), 0.01)
    }//end convertPoundsToKilograms

    //create tests for functions that convert between metric temperature and imperial temperature
    @Test
    fun convertCelsiusToFahrenheit() {
        assertEquals(33.8, converter.celsiusToFahrenheit(1.0), 0.01)
    }//end convertCelsiusToFahrenheit

    @Test
    fun convertFahrenheitToCelsius() {
        assertEquals(-17.2222, converter.fahrenheitToCelsius(1.0), 0.01)
    }//end convertFahrenheitToCelsius

    //create tests for functions that convert between metric volume and imperial volume
    @Test
    fun convertLitersToGallons() {
        assertEquals(0.264172, converter.litersToGallons(1.0), 0.01)
    }//end convertLitersToGallons

    @Test
    fun convertGallonsToLiters() {
        assertEquals(3.78541, converter.gallonsToLiters(1.0), 0.01)
    }//end convertGallonsToLiters

    //create tests for functions that convert between metric and imperial area
    @Test
    fun convertSquareMetersToSquareFeet() {
        assertEquals(10.7639, converter.squareMetersToSquareFeet(1.0), 0.01)
    }//end convertSquareMetersToSquareFeet

    @Test
    fun convertSquareFeetToSquareMeters() {
        assertEquals(0.09290304, converter.squareFeetToSquareMeters(1.0), 0.01)
    }//end convertSquareFeetToSquareMeters

    //create tests for functions that convert between metric and imperial volume
    @Test
    fun convertCubicMetersToCubicFeet() {
        assertEquals(35.3147, converter.cubicMetersToCubicFeet(1.0), 0.01)
    }//end convertCubicMetersToCubicFeet

    @Test
    fun convertCubicFeetToCubicMeters() {
        assertEquals(0.0283168466, converter.cubicFeetToCubicMeters(1.0), 0.01)
    }//end convertCubicFeetToCubicMeters

    //create tests for functions that convert between imperial length units
    @Test
    fun convertInchesToFeet() {
        assertEquals(0.083333, converter.inchesToFeet(1.0), 0.01)
    }//end convertInchesToFeet

    @Test
    fun convertYardsToFeet() {
        assertEquals(3.0, converter.yardsToFeet(1.0), 0.01)
    }//end convertYardsToFeet

    @Test
    fun convertMilesToFeet() {
        assertEquals(5280.0, converter.milesToFeet(1.0), 0.01)
    }//end convertMilesToFeet

    @Test
    fun convertFeetToMiles() {
        assertEquals(0.00018939393939393939, converter.feetToMiles(1.0), 0.01)
    }//end convertFeetToMiles

    @Test
    fun convertFeetToYards() {
        assertEquals(0.3333333333333333, converter.feetToYards(1.0), 0.01)
    }//end convertFeetToYards

    @Test
    fun convertFeetToInches() {
        assertEquals(12.0, converter.feetToInches(1.0), 0.01)
    }//end convertFeetToInches

    //create tests for functions that convert between imperial weight units
    @Test
    fun convertOuncesToPounds() {
        assertEquals(0.0625, converter.ouncesToPounds(1.0), 0.01)
    }//end convertOuncesToPounds

    @Test
    fun convertPoundsToOunces() {
        assertEquals(16.0, converter.poundsToOunces(1.0), 0.01)
    }//end convertPoundsToOunces

    @Test
    fun convertStonesToPounds() {
        assertEquals(14.0, converter.stonesToPounds(1.0), 0.01)
    }//end convertStonesToPounds

    @Test
    fun convertPoundsToStones() {
        assertEquals(0.07142857142857142, converter.poundsToStones(1.0), 0.01)
    }//end convertPoundsToStones

    @Test
    fun convertPoundstoTons() {
        assertEquals(0.0005, converter.poundsToTons(1.0), 0.01)
    }//end convertPoundstoTons

    @Test
    fun convertTonstoPounds() {
        assertEquals(2000.0, converter.tonsToPounds(1.0), 0.01)
    }//end convertTonstoPounds

    //create tests for functions that convert between imperial volume units
    @Test
    fun convertQuartsToGallons() {
        assertEquals(0.25, converter.quartsToGallons(1.0), 0.01)
    }//end convertQuartsToGallons

    @Test
    fun convertGallonsToQuarts() {
        assertEquals(4.0, converter.gallonsToQuarts(1.0), 0.01)
    }//end convertGallonsToQuarts

    @Test
    fun convertPintsToQuarts() {
        assertEquals(0.5, converter.pintsToQuarts(1.0), 0.01)
    }//end convertPintsToQuarts

    @Test
    fun convertQuartsToPints() {
        assertEquals(2.0, converter.quartsToPints(1.0), 0.01)
    }//end convertQuartsToPints

    @Test
    fun convertCupsToPints() {
        assertEquals(2.0, converter.cupsToPints(1.0), 0.01)
    }//end convertCupsToPints

    @Test
    fun convertPintsToCups() {
        assertEquals(0.5, converter.pintsToCups(1.0), 0.01)
    }//end convertPintsToCups

    @Test
    fun convertCupsToOunces() {
        assertEquals(8.0, converter.cupsToOunces(1.0), 0.01)
    }//end convertCupsToOunces

    @Test
    fun convertOuncesToCups() {
        assertEquals(0.125, converter.ouncesToCups(1.0), 0.01)
    }//end convertOuncesToCups

    @Test
    fun convertCupsToTablespoons() {
        assertEquals(16.0, converter.cupsToTablespoons(1.0), 0.01)
    }//end convertCupsToTablespoons

    @Test
    fun convertTablespoonsToCups() {
        assertEquals(0.0625, converter.tablespoonsToCups(1.0), 0.01)
    }//end convertTablespoonsToCups

    @Test
    fun convertTablespoonsToTeaspoons() {
        assertEquals(3.0, converter.tablespoonsToTeaspoons(1.0), 0.01)
    }//end convertTablespoonsToTeaspoons

    @Test
    fun convertTeaspoonsToTablespoons() {
        assertEquals(0.333333, converter.teaspoonsToTablespoons(1.0), 0.01)
    }//end convertTeaspoonsToTablespoons

    @Test
    fun convertFarenheitToRankine() {
        assertEquals(460.67, converter.fahrenheitToRankine(1.0), 0.01)
    }//end convertFarenheitToRankine

    @Test
    fun convertRankineToFarenheit() {
        assertEquals(-458.67, converter.rankineToFahrenheit(1.0), 0.01)
    }//end convertRankineToFarenheit

    //create tests for functions that convert between metric Temperatures
    @Test
    fun convertCelsiusToKelvin() {
        assertEquals(274.15, converter.celsiusToKelvin(1.0), 0.01)
    }//end convertCelsiusToKelvin

    @Test
    fun convertKelvinToCelsius() {
        assertEquals(-272.15, converter.kelvinToCelsius(1.0), 0.01)
    }//end convertKelvinToCelsius

    //create tests for functions that convert between metric units
    @Test
    fun convertMetersToKilometers() {
        assertEquals(0.001, converter.metersToKilometers(1.0), 0.01)
    }//end convertMetersToKilometers

    @Test
    fun convertKilometersToMeters() {
        assertEquals(1000.0, converter.kilometersToMeters(1.0), 0.01)
    }//end convertKilometersToMeters

    @Test
    fun convertMetersToCentimeters() {
        assertEquals(100.0, converter.metersToCentimeters(1.0), 0.01)
    }//end convertMetersToCentimeters

    @Test
    fun convertCentimetersToMeters() {
        assertEquals(0.01, converter.centimetersToMeters(1.0), 0.01)
    }//end convertCentimetersToMeters

    //create tests for functions that convert between metric volume
    @Test
    fun convertCubicMetersToCubicCentimeters() {
        assertEquals(1000000.0, converter.cubicMetersToCubicCentimeters(1.0), 0.01)
    }//end convertCubicMetersToCubicCentimeters

    @Test
    fun convertCubicCentimetersToCubicMeters() {
        assertEquals(0.000001, converter.cubicCentimetersToCubicMeters(1.0), 0.01)
    }//end convertCubicCentimetersToCubicMeters

    @Test
    fun convertCubicCentimetersToLiters() {
        assertEquals(0.001, converter.cubicCentimetersToLiters(1.0), 0.01)
    }//end convertCubicCentimetersToLiters

    @Test
    fun convertLitersToCubicCentimeters() {
        assertEquals(1000.0, converter.litersToCubicCentimeters(1.0), 0.01)
    }//end convertLitersToCubicCentimeters

    //create tests for functions that convert between metric weight
    @Test
    fun convertKilogramsToGrams() {
        assertEquals(1000.0, converter.kilogramsToGrams(1.0), 0.01)
    }//end convertKilogramsToGrams

    @Test
    fun convertGramsToKilograms() {
        assertEquals(0.001, converter.gramsToKilograms(1.0), 0.01)
    }//end convertGramsToKilograms

    //create tests for functions that convert between metric area
    @Test
    fun convertSquareMetersToSquareCentimeters() {
        assertEquals(10000.0, converter.squareMetersToSquareCentimeters(1.0), 0.01)
    }//end convertSquareMetersToSquareCentimeters

    @Test
    fun convertSquareCentimetersToSquareMeters() {
        assertEquals(0.0001, converter.squareCentimetersToSquareMeters(1.0), 0.01)
    }//end convertSquareCentimetersToSquareMeters

    @Test
    fun convertSquareCentimetersToSquareKilometers() {
        assertEquals(0.000001, converter.squareCentimetersToSquareKilometers(1.0), 0.01)
    }//end convertSquareCentimetersToSquareKilometers

    @Test
    fun convertSquareKilometersToSquareCentimeters() {
        assertEquals(10000000000.0, converter.squareKilometersToSquareCentimeters(1.0), 0.01)
    }//end convertSquareKilometersToSquareCentimeters

}//end ConversionUnitTest class