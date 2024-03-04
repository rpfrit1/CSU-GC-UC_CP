package edu.csuglobal.csc475.todoagain

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.compose.runtime.Composable

//create a DataManager class for a SQLite database for a todo list
class DataManager(context: Context) {
    //create a SQLite database for a todo list
    private var db: SQLiteDatabase

    private inner class CustomSQLiteOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            //create a new table named ToDo with an auto increment key, String Description, and a boolean completion
            val newTableQueryString =
                ("create table $TABLE_TODO IF NOT EXISTS($TABLE_TODO_ROW_ID integer primary key autoincrement not null, $TABLE_TODO_ROW_DESC text not null, $TABLE_TODO_ROW_DONE integer not null);")
            db.execSQL(newTableQueryString)
            //log the event
            Log.i("onCreate() = ", "TABLE CREATED.")

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

    //create init function
    init {
        db = CustomSQLiteOpenHelper(context).writableDatabase
        //log the event
        Log.i("DataManager() = ", "DATABASE CREATED AND OPENED.")
    }//end init function

    companion object {
        //table elements
        const val TABLE_TODO_ROW_ID = "_id"
        const val TABLE_TODO_ROW_DESC = "Description"
        const val TABLE_TODO_ROW_DONE = "Done"

        //database table info
        const val DB_NAME = "ToDo_db"
        const val DB_VERSION = 1
        const val TABLE_TODO = "todo_list"
    }//end companion object

    //create a function to add a todo item to a todo list
    fun addItem(item: ToDoItem) {
        //create a ContentValues variable
        val values = ContentValues()
        //put values from item into values
        values.put(TABLE_TODO_ROW_DESC, item.getDescription())
        values.put(TABLE_TODO_ROW_DONE, item.getStatus())
        //add the item to the database using the insert function
        db.insert(TABLE_TODO, null, values)

        //log the event
        Log.i("addItem() = ", "ITEM ADDED TO DATABASE.")
    }//end addItem function

    //create a function to remove a todo item from a todo list
    fun removeItem(item: ToDoItem) {
        //remove the item from the database using the delete function
        db.delete(TABLE_TODO, TABLE_TODO_ROW_ID + " = " + item.getId().toString(), null)
        //log event
        Log.i("removeItem() = ", "ITEM REMOVED FROM DATABASE.")
    }//end removeItem function

    //create a function to update a todo item in a todo list
    fun updateItem(item: ToDoItem) {
        //create a ContentValues variable and add description and done into it
        val values = ContentValues()
        values.put(TABLE_TODO_ROW_DESC, item.getDescription())
        values.put(TABLE_TODO_ROW_DONE, item.getStatus())
        //update the item in the database using the update function
        db.update(TABLE_TODO, values, TABLE_TODO_ROW_ID + " = " + item.getId().toString(), null)

        //log event
        Log.i("updateItem() = ", "ITEM UPDATED IN DATABASE.")
    }//end updateItem function

    //create a function to get a todo item from a todo list
    @SuppressLint("Range")
    fun getItem(id: Int): ToDoItem {
        //get the item from the database using the query function
        val cursor = db.query(TABLE_TODO, null, "$TABLE_TODO_ROW_ID = $id", null, null, null, null)
        //get the item from the cursor
        return cursor.use {
            //move to the first row
            it.moveToFirst()
            //create a new item
            return ToDoItem(
                it.getString(it.getColumnIndex(TABLE_TODO_ROW_DESC)),
                it.getInt(it.getColumnIndex(TABLE_TODO_ROW_ID)),
                it.getInt(it.getColumnIndex(TABLE_TODO_ROW_DONE)) == 1
            )
        }//end cursor function
    }//end getItem function

    //create a function to get all todo items from a todo list
    @SuppressLint("Range")
    fun getItems(): ToDoList<ToDoItem> {
        //get the items from the database using the query function
        val cursor = db.query(TABLE_TODO, null, null, null, null, null, null)
        //get the items from the cursor
        return cursor.use {
            //create a new todo list
            val list = ToDoList<ToDoItem>()
            //move to the first row
            it.moveToFirst()
            //loop through the rows
            while (!it.isAfterLast) {
                //create a new item
                val item = ToDoItem(
                    it.getString(it.getColumnIndex(TABLE_TODO_ROW_DESC)),
                    it.getInt(it.getColumnIndex(TABLE_TODO_ROW_ID)),
                    it.getInt(it.getColumnIndex(TABLE_TODO_ROW_DONE)) == 1
                )
                //add the item to the list
                list.add(item)
                //move to the next row
                it.moveToNext()
            }//end while

            //return the list
            return list
        }//end cursor function
    }//end getItems function


    //create a function to get all todo items from a todo list with a specific status
    @SuppressLint("Range")
    @Composable
    fun getItemsWithStatus(status: Boolean): ToDoList<ToDoItem> {
        //get the items from the database using the query function
        val cursor = db.query(
            TABLE_TODO,
            null,
            TABLE_TODO_ROW_DONE + " = " + (if (status) 1 else 0).toString(),
            null,
            null,
            null,
            null
        )
        //get the items from the cursor
        return cursor.use {
            //create a new todo list
            val list = ToDoList<ToDoItem>()
            //move to the first row
            it.moveToFirst()
            //loop through the rows
            while (!it.isAfterLast) {
                //create a new item
                val item = ToDoItem(
                    it.getString(it.getColumnIndex(TABLE_TODO_ROW_DESC)),
                    it.getInt(it.getColumnIndex(TABLE_TODO_ROW_ID)),
                    it.getInt(it.getColumnIndex(TABLE_TODO_ROW_DONE)) == 1
                )
                //add the item to the list
                list.add(item)
                //move to the next row
                it.moveToNext()
            }//end while
            //return the list
            return list
        }//end cursor function
    }//end nd getItemsWithStatus function

    //create a function to close the database
    fun close() {
        //close the database
        db.close()
        //log the event
        Log.i("close() = ", "DATABASE CLOSED.")
    }//end close function


}//end DataManager class