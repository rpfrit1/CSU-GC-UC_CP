package edu.csuglobal.csc475.todoagain

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.graphics.Paint
import androidx.compose.ui.tooling.preview.Preview
import edu.csuglobal.csc475.todoagain.ui.theme.ToDoAgainTheme

class MainActivity : ComponentActivity() {

    lateinit var showButton: Button
    lateinit var addButton: Button
    lateinit var hideButton: Button
    lateinit var listSet: LinearLayout
    val dataManager = DataManager(this)

    //create a onDestroy function to close the DataManager
    override fun onDestroy() {
        super.onDestroy()
        dataManager.close()
    }//end onDestroy function

    init {
        //set permissions to allow file access
        val permission = "android.permission.WRITE_EXTERNAL_STORAGE"
        val requestCode = 1
        requestPermissions(arrayOf(permission), requestCode)
    }

    //create a onCreate function to set the content of the screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set screen_layout as the default layout
        setContentView(R.layout.main_layout)
        showButton = findViewById<Button>(R.id.showButton)
        hideButton = findViewById<Button>(R.id.hideButton)
        addButton = findViewById<Button>(R.id.addButton)
        listSet = findViewById<LinearLayout>(R.id.listSet)

        //set the content of the screen
        setContent {
            //create a DataManager object
            val dataManager = DataManager(this)

            //query dataManager for all ToDoItems with done set to false
            val items = dataManager.getItemsWithStatus(status = false)

            //add a ToDoItem for each item in items to listSet
            for (item in items) {
                //add item description to listSet
                val todo = TextView(this)
                todo.id = item.getId()
                todo.text = item.getDescription()

                //create an event listener for the todo item
                todo.setOnClickListener {
                    //set the item's done property to true
                    item.toggleStatus()
                    //update the item in the database
                    dataManager.updateItem(item)
                    //remove the todo item from the screen
                    listSet.removeView(todo)
                }//end setOnClickListener function
                listSet.addView(todo)
            }//end for loop
        }//end setContent function


        //set the onClickListener for the hideButton
        hideButton.setOnClickListener {
            //make hideButton invisible
            hideButton.visibility = Button.INVISIBLE
            //make showButton visible
            showButton.visibility = Button.VISIBLE
            //make addButton visible
            addButton.visibility = Button.VISIBLE
        }//end setOnClickListener function

        //set the onClickListener for the showButton
        showButton.setOnClickListener {
            //query dataManager for all ToDoItems
            val items = dataManager.getItems()
            //clear the listSet LinearLayout
            listSet.removeAllViews()
            for (item in items) {
                //add item description to listSet
                val todo = TextView(this)
                todo.id = item.getId()
                todo.text = item.getDescription()
                //if the item is done, set the text to strike through
                if (item.getStatus()) {
                    todo.paintFlags = todo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }//end if statement
                //create an event listener for the todo item
                todo.setOnClickListener {
                    //set the item's done property to true
                    item.toggleStatus()
                    //update the item in the database
                    dataManager.updateItem(item)
                    //remove the todo item from the screen
                    listSet.removeView(todo)
                }//end setOnClickListener function
                listSet.addView(todo)
            }//end for loop
            //make hideButton visible
            hideButton.visibility = Button.VISIBLE
            //make showButton invisible
            showButton.visibility = Button.INVISIBLE
            //make addButton visible
            addButton.visibility = Button.VISIBLE
        }//end setOnClickListener function
    }//end onCreate function
}//end MainActivity class


@Composable
fun ToDoItem(description: String, completion: Boolean) {
    //create a Text composable that displays the description of the ToDoItem
    Text(
        text = description
    )
}//end ToDoItem function

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}//end Greeting function

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAgainTheme {
        Greeting("Android")
    }//end ToDoAgainTheme function
}//end GreetingPreview function

