package edu.csuglobal.csc475.photoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photo: ImageView = findViewById(R.id.imgPicture)
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(photo);
    }
}