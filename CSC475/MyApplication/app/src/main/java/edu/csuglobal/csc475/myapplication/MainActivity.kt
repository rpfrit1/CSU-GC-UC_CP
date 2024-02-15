package edu.csuglobal.csc475.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    //get the body of the response
    lateinit var body: ResponseBody
    //get the content of the body
    lateinit var content:String
    private lateinit var txtResult: TextView
    private val url  = "https://jsonplaceholder.typicode.com/todos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the text view
        txtResult = findViewById(R.id.txtResult)
        content = txtResult.toString()
    //create a thread and call dowloadFile function
        Thread(Runnable {
            downloadFile()
        }//end Runnable
        ).start()//end start function
        //wait for the thread to finish
        runBlocking {
            Thread.sleep(1000)
        }//end runBlocking function
        //log the content of the body
        Log.d("MainActivity", content.toString())

        //update the text view with the content of the body
        txtResult.text = content.toString()


    }//end onCreate

    //createa a thread to download the file
    private fun downloadFile(){
        //create a client to make the request
        val client = OkHttpClient()
        //create a request to the website
        val request = Request.Builder()
            .url(url)
            .build()
        //make the request and get the response
        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //log the failure, display a message to the user, and cancel the coroutine
                content = "Failed to download file"
            }//end onFailure
            override fun onResponse(call: Call, response: Response) {
                //if response is successful, log the response, add the body to content
                if (response.isSuccessful) {
                    content = response.body!!.string()

                    //run on the UI thread to update the text view with the content of the body
                    runOnUiThread(Runnable(){
                        fun run() {
                            txtResult.text = content.toString()
                        }//end run function
                    })//end runOnUiThread function
                }//end if
                else {
                    txtResult.text = "Failed to download file"
                }//end else
            }//end onResponse function
        })//end enqueue function
    }
}//end MainActivity class