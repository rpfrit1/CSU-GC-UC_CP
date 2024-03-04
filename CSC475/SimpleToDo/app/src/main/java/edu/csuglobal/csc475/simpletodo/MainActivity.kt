package edu.csuglobal.csc475.simpletodo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.csuglobal.csc475.simpletodo.ui.theme.SimpleToDoTheme

class DataManager(context: Context) {
    //database variable
    private lateinit var db: SQLiteDatabase
    //cursor variable
    private lateinit var cursor: Cursor
    //context variable
    private val context = context

    //Taken from the textbook in order to encapsulate this portion with a supposed good helper class
    private inner class CustomSQLiteOpenHelper
        (context: Context):
        SQLiteOpenHelper(
            context, DB_NAME,
            null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            //create a new table named ToDo with an auto increment key, String Description, and a boolean completion
            val newTableQueryString = ("create table"
                    + TABLE_TODO + " ("
                    + TABLE_ROW_ID + " integer primary key autoincrement not null, "
                    + TABLE_ROW_DESC + " text not null, "
                    + TABLE_ROW_COMP + " integer not null);")
            db?.execSQL(newTableQueryString)
        }//end onCreate function

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            //drop the table if it exists
            db.execSQL("drop table if exists $TABLE_TODO")

            //create a new table
            onCreate(db)

            //log the event
            Log.i("onUpgrade() = ", "DROPPED TABLE $TABLE_TODO AND CREATED A NEW ONE.")
        }//end onUpgrade function
    }//end CustomSQLiteOpenHelper class

    init {
        //create and open database as writable
        val helper = CustomSQLiteOpenHelper(context)
        db = helper.writableDatabase
        //log the event
        Log.i("DataManager() = ", "DATABASE CREATED AND OPENED.")

    }//end init

    companion object {
        //table elements
        const val TABLE_ROW_ID = "_id"
        const val TABLE_ROW_DESC = "Description"
        const val TABLE_ROW_COMP = "Complete"

        //database table info
        const val DB_NAME = "ToDo_db"
        const val DB_VERSION = 1
        const val TABLE_TODO = "todo_list"
    }//end companion obj

    fun update(toDoId: Int, desc: String, comp: Int) {
        val values = ContentValues()
        values.put(TABLE_ROW_ID, toDoId)
        values.put(TABLE_ROW_DESC, desc)
        values.put(TABLE_ROW_COMP, comp)

        this.db.update(TABLE_TODO, values, "_id = ",arrayOf(toDoId.toString()))

        Log.i("Update() = ","UPDATE " + TABLE_TODO + "SET " + TABLE_ROW_DESC + " = " + desc
                + ", " + TABLE_ROW_COMP + " = " + comp + " FROM " + TABLE_TODO + " WHERE" +
                TABLE_ROW_ID + " = " + toDoId + ";")
    }//end complete function

    fun insert(desc:String, comp:Int = 0) {
        //all all inputted values in record
        db.insert(TABLE_TODO,null,
            ContentValues().apply { put(TABLE_ROW_DESC,desc)
                put(TABLE_ROW_COMP, comp)
            } //end apply function
        )

        //log the event
        Log.i("Insert() = ", "INSERT INTO " + TABLE_TODO + " (" +
                TABLE_ROW_DESC + ", " + TABLE_ROW_COMP + ") VALUES (" +
                "'" + desc + "', " + comp + "');")
    }//end insert function

    fun delete(toDoId:Int) {
        //delete if record exists
        db.delete(TABLE_TODO, TABLE_ROW_ID, arrayOf(toDoId.toString()))

        //log event
        Log.i("Delete() = ", "DELETE FROM " + TABLE_TODO +
                " WHERE " + TABLE_ROW_ID + " = " + toDoId + ";")
    }//end delete function

    fun find(desc:String): Cursor {
        return db.rawQuery("SELECT * from " + TABLE_TODO +
                " WHERE" + TABLE_ROW_DESC + " = '" + desc + "'", null)
    }//end find function

    fun selectAll(): Cursor {
        return db.rawQuery("SELECT * from " + TABLE_TODO, null)
    }//end selectAll function

    fun close() {
        db.close()
    }//end close function

    fun clear() {
        db.execSQL("DELETE FROM " + TABLE_TODO)
    }//end clear function

    fun count(): Int {
        val cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_TODO, null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }//end count function

    fun countCompleted(): Int {
        val cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_COMP + " = 1", null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }//end countCompleted function

    fun countIncomplete(): Int {
        val cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_COMP + " = 0", null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }//end countIncomplete function

    fun selectCompleted(): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_COMP + " = 1", null)
    }//end selectCompleted function

    fun selectIncomplete(): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_COMP + " = 0", null)
    }//end selectIncomplete function

    fun select(toDoId:Int, desc:String): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_ID + " = " + toDoId + " AND " +
                TABLE_ROW_DESC + " = '" + desc + "'", null)
    }//end select function

    fun select(desc:String, comp:Int): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_DESC + " = '" + desc + "' AND " +
                TABLE_ROW_COMP + " = " + comp, null)
    }//end select function

    fun select(desc:String): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_DESC + " = '" + desc + "'", null)
    }//end select function

    fun select(comp:Int): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + TABLE_ROW_COMP + " = " + comp, null)
    }//end select function
}//end DataManager class

class MainActivity : ComponentActivity() {

    //create an list of ToDoItems
    private val todoItems = mutableListOf<ToDoItem>()

    //create an instance of the DataManager
    private val dm = DataManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }//end Surface function
            }//end SimpleToDoTheme function
        }//end setContent function
    }//end onCreate function

    //create a function that loads all incomplete
    //ToDoItems from the database
    private fun loadIncompleteItems() {
        todoItems.clear()
        todoItems.addAll(convertToList( dm.selectIncomplete()))
    }//end loadIncompleteItems function
}//end MainActivity class

//create a function that takes a cusor and converts it to a list of ToDoItems
    fun convertToList(cursor: Cursor): List<ToDoItem> {
        val todoItems = mutableListOf<ToDoItem>()

    while (cursor.moveToNext()) {
        todoItems.add(ToDoItem(
            id = cursor.getInt(0),
            desc = cursor.getString(2),
            comp = cursor.getInt(3) == 1
        ))
    }//end while loop
    return todoItems
}//end convertCursorToList function

@Composable
fun SimpleToDoApp() {
    Greeting("Android")
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleToDoTheme {
        Greeting("Android")
    }
}