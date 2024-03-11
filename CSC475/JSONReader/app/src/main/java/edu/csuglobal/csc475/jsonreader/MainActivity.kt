package edu.csuglobal.csc475.jsonreader

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
    lateinit var layMain: LinearLayout
    lateinit var btnUrl: Button
    lateinit var txtUrl: EditText
    lateinit var textView: TextView
    lateinit var jsonArray: JSONArray
    lateinit var jsonObject: JSONObject
    lateinit var name: String
    lateinit var description: String
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialize the views
        layMain = findViewById(R.id.layMain)
        btnUrl = findViewById(R.id.btnUrl)
        txtUrl = findViewById(R.id.txturl)

        //create a button click listener
        btnUrl.setOnClickListener {
            clearLayout()
            val url = "[{" +
                    "    \"userId\": 1," +
                    "    \"id\": 1," +
                    "    \"title\": \"delectus aut autem\"," +
                    "    \"completed\": false" +
                    "  }]"
            pullFile(url)
        }//end button click listener
    }//end onCreate function

    //create a function to read the file at the url
    <<<<<<< HEAD
    private fun readFile(url: String): String {
        ====== =
        private fun readFile(url: String): String {
            >>>>>>> origin/main
            try {
                //create a file
                val file = File(url)
                //create a file input stream
                val fileInputStream = FileInputStream(file)
                //create an input stream reader
                val inputStreamReader = InputStreamReader(fileInputStream, Charset.forName("UTF-8"))
                //create a buffered reader
                val bufferedReader = BufferedReader(inputStreamReader)
                <<<<<<< HEAD
                //create a string builder
                val stringBuilder = StringBuilder()
                ====== =
                //create a string builder
                val stringBuilder = StringBuilder()
                >>>>>>> origin/main
                //loop through the buffered reader
                for (line in bufferedReader.lineSequence()) {
                    //append the line to the string builder
                    stringBuilder.append(line)
                }//end loop through the buffered reader
                return stringBuilder.toString()//return the string builder as a string
            }//end try
        catch (e: Exception) {
            return findViewById<TextView>(R.id.testText).toString()
        }//end catch
    }//end readFile function

    //create a function to parse the json file passed
    private fun pullFile(jsonFile: String) {
        <<<<<<< HEAD
        try {
            //create a json object
            val jsonObject = JSONObject(jsonFile)
            //log the current state of the json object
            Log.d("jsonObject", jsonObject.toString())
            //create a json array
            val jsonArray = jsonObject.getJSONArray("data")
            ====== =
            try {
                //create a json object
                val jsonObject = JSONObject(jsonFile)
                //log the current state of the json object
                Log.d("jsonObject", jsonObject.toString())
                //create a json array
                val jsonArray = jsonObject.getJSONArray("data")
                >>>>>>> origin/main
                //log the current state of the json array
                Log.d("jsonArray", jsonArray.toString())
                //loop through the json array
                for (i in 0 until jsonArray.length()) {
                    //create a json object
                    val jsonObject = jsonArray.getJSONObject(i)
                    //log the current state of the json object
                    Log.d("jsonObject", jsonObject.toString())
                    //create a json object
                    val name = jsonObject.getString("name")
                //create a json object
                val description = jsonObject.getString("description")
                    //create a text view
                    val textView = TextView(this)
                    //set the text view text
                    textView.text = "$name - $description"
                    //add the text view to the layout
                    layMain.addView(textView)
                }//end loop through the json array
            }//end try

            //catch JSON Exceptions
            <<<<<<< HEAD
            catch(j: JSONException) {
                ====== =
                catch(j:JSONException) {
                    >>>>>>> origin/main
                    //put a message on the layout stating unable to pull the file
                    val textView = TextView(this)
                    textView.text = "Unable to parse the JSON file"
                    layMain.addView(textView)
                }//end catch
                <<<<<<< HEAD
                catch(e: Exception) {
                    ====== =
                    catch(e:Exception) {
                        >>>>>>> origin/main
                        //put a message on the layout stating unable to pull the file
                        val textView = TextView(this)
                        textView.text = "Unable to pull file"
                        layMain.addView(textView)
                    }//end catch
                }//end pullFile function

                //create a function to parse the JSON string
                private fun parseJson(jsonString: String) {
                    try {
                        //create a json object
                        val jsonObject = JSONObject(jsonString)
                        //log the current state of the json object
                        Log.d("jsonObject", jsonObject.toString())
                        //create a json array
                        val jsonArray = jsonObject.getJSONArray("data")
                        //log the current state of the json array
                        Log.d("jsonArray", jsonArray.toString())
                        //loop through the json array
                        for (i in 0 until jsonArray.length()) {
                            //create a json object
                            val jsonObject = jsonArray.getJSONObject(i)
                            //log the current state of the json object
                            Log.d("jsonObject", jsonObject.toString())
                            //create a json object
                            val name = jsonObject.getString("name")
                            //create a json object
                            val description = jsonObject.getString("description")
                            //create a text view
                            val textView = TextView(this)
                            //set the text view text
                            textView.text = "$name - $description"
                            //add the text view to the layout
                            layMain.addView(textView)
                        }//end loop through the json array
                    }//end try
                    catch (j: JSONException) {
                        //put a message on the layout stating unable to pull the file
                        val textView = TextView(this)
                        textView.text = "Unable to parse the JSON file"
                        layMain.addView(textView)
                        //display the string that was passed to the function
                        textView.text = jsonString
                        //add the text view to the layout
                        layMain.addView(textView)
                    }//end catch
                }//end parseJson function

                //create a function to clear the layout
                private fun clearLayout() {
                    //clear the layout
                    layMain.removeAllViews()
                }//end clearLayout function
}//end MainActivity class