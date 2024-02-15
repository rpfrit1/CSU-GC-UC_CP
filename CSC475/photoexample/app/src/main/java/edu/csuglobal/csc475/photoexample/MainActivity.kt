package edu.csuglobal.csc475.photoexample

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_layout)
        val photoView = findViewById<ImageView>(R.id.photoView)
    }//end onCreate
}//end MainActivity class
