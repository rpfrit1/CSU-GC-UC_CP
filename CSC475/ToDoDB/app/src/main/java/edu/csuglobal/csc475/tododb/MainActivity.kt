package edu.csuglobal.csc475.tododb

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainActivity : AppCompatActivity() {
    //create variables for the add button, show button, and hide button
    private lateinit var addButton:Button
    private lateinit var showButton:Button
    private lateinit var hideButton:Button

    //create a variable for taskTable
    private lateinit var taskTable:TableLayout

    //create a TaskManager object to manage the SQLite database
    private lateinit var taskManager: TaskManager

    //create a list of tasks
    private lateinit var tasks: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set activity_main as layout
        setContentView(R.layout.activity_main)
        addTitle()
        addButton = findViewById(R.id.addButton)
        showButton = findViewById(R.id.showButton)
        hideButton = findViewById(R.id.hideButton)
        taskTable = findViewById<TableLayout>(R.id.taskTable)
        //create a TaskManager object to manage the SQLite database

        taskManager = TaskManager(this)

        //set addButton showButton to visible and set hideButton to invisible
        addButton.visibility = Button.VISIBLE
        showButton.visibility = Button.VISIBLE
        hideButton.visibility = Button.INVISIBLE

        //set activity_main as layout
        setContentView(R.layout.activity_main)
        addTitle()

        //query database to get list of tasks
        val cursor = taskManager.getTasksNotDone()

        //create a list of tasks from cursor
        tasks = TaskList(cursor)

        addTasks()

        //create a listener for the show button that runs the showTasks function
        showButton.setOnClickListener {
            //clear taskTable
            taskTable.removeAllViews()
            addTitle()

            showTasks()
        }//end show button listener

        //create a listener for the hide button that runs the showTasksNotDone function
        hideButton.setOnClickListener {
            //clear taskTable
            taskTable.removeAllViews()
            addTitle()

            showTasksNotDone()
        }//end hide button listener

        //create a listener for the add button that runs the addTask function with an empty EditText
        addButton.setOnClickListener {
            addTask(EditText(this).toString())
        }//end add button listener

    }//end onCreate function

    //create a function to toggle a checkbox based on the tag property
    private fun toggleCheckbox(tag: String) {
        //if tag equals R.id.selectAll, go through the table and set checkboxes isChecked to same as selectAll
        if (tag == "selectAll") {
            //go through the table and set checkboxes isChecked to same as selectAll
            for (i in 0 until taskTable.childCount) {
                //get the checkbox with the tag property
                val chk = taskTable.getChildAt(i).findViewWithTag<CheckBox>(tag)

                //if chk is not null and chkSelectAll is checked, set chk.isChecked to true otherwise set chk.isChecked to false
                //end if
                chk?.isChecked = chk != null && !chk.isChecked//end else
            }//end for
        }//end if
        else {
            //get the checkbox with the tag property
            val chk = taskTable.findViewWithTag<CheckBox>(tag)

            //if chk is not null and chkSelectAll is checked, set chk.isChecked to true otherwise set chk.isChecked to false
            //end if
            chk?.isChecked = chk != null && !chk.isChecked
        }//end else
    }//end toggleCheckbox function

    //create a function to add title information to the table
    private fun addTitle() {
        //add row to taskTable and set visibility to true
        val row = TableRow(this)
        row.visibility = TableRow.VISIBLE

        //create a CheckBox with id of selectAll and isChecked is false and add to row and set visibility to true
        val chkSelectAll = CheckBox(this)
        chkSelectAll.id = R.id.selectAll
        chkSelectAll.tag = R.id.selectAll
        chkSelectAll.isChecked = false
        row.addView(chkSelectAll)
        chkSelectAll.visibility = TableRow.VISIBLE

        //create an TextView with id of lblDescription and a text of "Task Description" and add to row and set visibility to true
        val lblDescription = TextView(this)
        lblDescription.id = R.id.lblDescription
        lblDescription.text = R.string.task_description.toString()
        row.addView(lblDescription)
        lblDescription.visibility = TableRow.VISIBLE

        //add tableRow to taskTable and set table visibility to true
        taskTable.addView(row)
        taskTable.visibility = TableLayout.VISIBLE

        //log event
        Log.d("MainActivity", "addTitle function called")
    }//end addTitle function

    //create a function to show all tasks and set the show button to invisible and the hide button to visible
    @SuppressLint("Range")
    private fun showTasks() {
        //clear the table and load the title
        taskTable.removeAllViews()
        addTitle()

        //query database to get list of tasks
        val cursor = taskManager.getAllTasks()

        //create a list of tasks from cursor
        tasks = TaskList(cursor)

        //add tasks to the table
        addTasks()

        //set addButton showButton to visible and set hideButton to invisible
        addButton.visibility = Button.VISIBLE
        showButton.visibility = Button.VISIBLE
        hideButton.visibility = Button.INVISIBLE
    }//end showTasks function

    //create a function to add a new task to the table and database
    private fun addTask(
        taskDescription: String
    ) {
        //insert the task into the database setting taskDescription to TASK_COLUMN_DESCRIPTION and taskDone to TASK_COLUMN_STATUS
        taskManager.insertTask(taskDescription, false)

        //query database to get list of tasks
        val cursor = taskManager.getAllTasks()

        //create a list of tasks from cursor
        tasks = TaskList(cursor)

        //clear taskTable
        taskTable.removeAllViews()
        addTitle()

        //if show button is visible, run showTasks function otherwise run showTasksNotDone
        if (showButton.visibility == Button.VISIBLE) {
            showTasks()
        }//end if
        else {
            showTasksNotDone()
        }//end else
    }//end addTask function

    //create a function to show all tasks not completed and set the show button to visible and the hide button to invisible
    @SuppressLint("Range")
    private fun showTasksNotDone() {
        //query database to get list of tasks
        val cursor = taskManager.getTasksNotDone()

        //create a list of tasks from cursor
        tasks = TaskList(cursor)

        //add tasks to the table
        addTasks()

        //set addButton showButton to visible and set hideButton to invisible
        addButton.visibility = Button.VISIBLE
        showButton.visibility = Button.VISIBLE
        hideButton.visibility = Button.INVISIBLE
    }//end showTasksNotDone function

    private fun addTasks() {
        //add tasks to the table
        for (task in tasks) {
            //create a TableRow and set tag to task id
            val tableRow = TableRow(this)
            tableRow.tag = task.taskId

            //create a CheckBox, set checkedState to taskDone and add to tableRow
            val chkTaskDone = CheckBox(this)
            chkTaskDone.isChecked = task.taskDone
            tableRow.addView(chkTaskDone)

            //create an EditText, set the text to taskDescription and add to tableRow
            val txtTaskDescription = EditText(this)
            txtTaskDescription.setText(task.taskDescription)
            tableRow.addView(txtTaskDescription)

            //add tableRow to taskTable
            taskTable.addView(tableRow)

            //create a listener for the checkbox that calls toggleCheckBox and updates the database
            chkTaskDone.setOnClickListener {
                //get the task id from the tag property
                val taskId = tableRow.tag as Int

                //get the text from the EditText
                val taskDescription = txtTaskDescription.text.toString()

                //call toggleCheckbox
                toggleCheckbox(taskId.toString())

                //update the database
                taskManager.updateTask(taskId, taskDescription, chkTaskDone.isChecked)

                taskTable.refreshDrawableState()
            }//end listener

            //create a listener for when the EditText changes that calls updateTask
            txtTaskDescription.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    //get the task id from the tag property
                    val taskId = tableRow.tag as Int

                    //get the text from the EditText
                    val taskDescription = txtTaskDescription.text.toString()

                    //call updateTask
                    taskManager.updateTask(taskId, taskDescription, chkTaskDone.isChecked)
                }//end if
            }//end

        }//end for loop
    }//end addTasks function

    //create a function to add a new task to the database
    fun addTask() {
        //create a new TableRow
        val tableRow = TableRow(this)

        //create a new CheckBox and add to row
        val chkTaskDone = CheckBox(this)
        chkTaskDone.isChecked = false
        tableRow.addView(chkTaskDone)
        chkTaskDone.visibility = TableRow.VISIBLE

        //create a new EditText and add to row
        val txtTaskDescription = EditText(this)
        txtTaskDescription.setText("")
        tableRow.addView(txtTaskDescription)
        txtTaskDescription.visibility = TableRow.VISIBLE
        txtTaskDescription.requestFocus()
        txtTaskDescription.selectAll()
        txtTaskDescription.setSingleLine(false)
        txtTaskDescription.setLines(1)
        txtTaskDescription.setHorizontallyScrolling(false)
        txtTaskDescription.setPadding(6, 6, 6, 6)
        txtTaskDescription.setHint("Enter task description")
        txtTaskDescription.setHintTextColor(Color.parseColor("#000000"))
        txtTaskDescription.setImeOptions(EditorInfo.IME_ACTION_DONE)
        txtTaskDescription.setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE)

        //create a listener for txtTaskDescription for when it is updated
        txtTaskDescription.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                //get the text from the EditText
                val taskDescription = txtTaskDescription.text.toString()

                //call addTask
                addTask(taskDescription)
            }//end if
        }//end listener

        //create a listener for txtTaskDescription for when it is done
        txtTaskDescription.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //get the text from the EditText
                val taskDescription = txtTaskDescription.text.toString()

                //call addTask
                addTask(taskDescription)
            }//end if
            false

            //add task to the database

        }//end listener

        //add tableRow to taskTable
        taskTable.addView(tableRow)

        //log event
        Log.d("MainActivity", "addTask function called")
    }//end addTask function
}//end MainActivity class

//create a class to manage the SQLite database
class TaskManager(context: Context) {
    //create a SQLite database to store tasks
    private val db: SQLiteDatabase

    //create a companion object to create the SQLite database
    companion object {
        //establish the elements in the table
        const val TASK_TABLE_NAME = "tasks"
        const val TASK_COLUMN_ID = "id"
        const val TASK_COLUMN_DESCRIPTION = "description"
        const val TASK_COLUMN_STATUS = "status"

        //establish the Database values
        const val DATABASE_NAME = "task_database"
        const val DATABASE_VERSION = 1

    }//end companion object

    //create a SQLiteOpenHelper class
    private inner class TaskOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            //create the table to store tasks
            db.execSQL("CREATE TABLE $TASK_TABLE_NAME ($TASK_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $TASK_COLUMN_DESCRIPTION TEXT, $TASK_COLUMN_STATUS TEXT)")
        }//end onCreate function

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            //drop the table if it exists
            db.execSQL("DROP TABLE IF EXISTS $TASK_TABLE_NAME")
            //create a new table
            onCreate(db)
        }//end onUpgrade function

    }//end TaskOpenHelper class

    //create a constructor to initialize the SQLite database
    init {
        //create a TaskOpenHelper object
        val taskOpenHelper = TaskOpenHelper(context)
        //open the database
        db = taskOpenHelper.writableDatabase
    }//end init function

    //create a method to insert a new task into the database
    fun insertTask(taskDescription: String, taskDone: Boolean?) {
        //insert the task into the database setting taskDescription to TASK_COLUMN_DESCRIPTION and taskDone to TASK_COLUMN_STATUS
        db.execSQL("INSERT INTO $TASK_TABLE_NAME ($TASK_COLUMN_DESCRIPTION, $TASK_COLUMN_STATUS) VALUES ('$taskDescription', '$taskDone')")

        //log SQL insert statement
        Log.d(
            "SQL",
            "INSERT INTO $TASK_TABLE_NAME ($TASK_COLUMN_DESCRIPTION, $TASK_COLUMN_STATUS) VALUES ('$taskDescription', '$taskDone')"
        )
    }//end insertTask function

    //create a method to retrieve a single task from the database based on its ID
    fun getTaskById(taskId: Int): Cursor {
        //retrieve the task from the database based on taskId
        return db.rawQuery("SELECT * FROM $TASK_TABLE_NAME WHERE $TASK_COLUMN_ID = $taskId", null)
    }//end getTaskById function

    //create a function to update a task in the database
    fun updateTask(taskId: Int, taskDescription: String, taskDone: Boolean) {
        //update the task in the database
        db.execSQL("UPDATE $TASK_TABLE_NAME SET $TASK_COLUMN_DESCRIPTION = '$taskDescription', $TASK_COLUMN_STATUS = '$taskDone' WHERE $TASK_COLUMN_ID = $taskId")
    }//end updateTask function

    //create a method to delete an existing task from the database
    fun deleteTask(taskId: Int) {
        //delete the task from the database
        db.execSQL("DELETE FROM $TASK_TABLE_NAME WHERE $TASK_COLUMN_ID = $taskId")
    }//end deleteTask function

    //create a method to retrieve all tasks from the database
    fun getAllTasks(): Cursor {
        //retrieve all tasks from the database
        return db.rawQuery("SELECT * FROM $TASK_TABLE_NAME", null)
    }//end getAllTasks function

    //create a method to retrieve all tasks where taskDone is false
    fun getTasksNotDone(): Cursor {
        //retrieve all tasks where taskDone is false
        return db.rawQuery(
            "SELECT * FROM $TASK_TABLE_NAME WHERE $TASK_COLUMN_STATUS = 'false'",
            null
        )
    }//end getTasksNotDone function

    //create a method to retrieve all tasks where taskDone is true
    fun getTasksDone(): Cursor {
        //retrieve all tasks where taskDone is true
        return db.rawQuery(
            "SELECT * FROM $TASK_TABLE_NAME WHERE $TASK_COLUMN_STATUS = 'true'",
            null
        )
    }//end getTasksDone function

    //create a method to retrieve a single task from the database based on its description
    fun getTaskByDescription(taskDescription: String): Cursor {
        //retrieve the task from the database based on taskDescription
        return db.rawQuery(
            "SELECT * FROM $TASK_TABLE_NAME WHERE $TASK_COLUMN_DESCRIPTION = '$taskDescription'",
            null
        )
    }//end getTaskByDescription function

    //create a method to clear the database
    fun clearDatabase() {
        //clear the database
        db.execSQL("DELETE FROM $TASK_TABLE_NAME")
    }//end clearDatabase function

    //create a method to close the database
    fun closeDatabase() {
        //close the database
        db.close()
    }//end closeDatabase function
}//end TaskManager class

//create a class to hold a task
class Task(val taskId: Int, val taskDescription: String, val taskDone: Boolean) {
    //create a method to convert a Task object to a string
    override fun toString(): String {
        //return the task description
        return taskDescription
    }//end toString function

    //create a constructor that takes a Cursor and inserts the information from the Cursor into a Task object
    @SuppressLint("Range")
    constructor(cursor: Cursor) : this(cursor.getInt(cursor.getColumnIndex(TaskManager.TASK_COLUMN_ID)), cursor.getString(cursor.getColumnIndex(TaskManager.TASK_COLUMN_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(TaskManager.TASK_COLUMN_STATUS)) == 1)
}//end Task class

//create a class to hold a list of tasks
class TaskList(val tasks: List<Task>) {

    //create a method named iterator to iterate through the list of tasks
    operator fun iterator(): Iterator<Task> {
        //return the iterator of tasks
        return tasks.iterator()
    }//end iterator function

    //create a method to convert a Tasks object to a string
    override fun toString(): String {
        //return the tasks
        return tasks.toString()
    }//end toString function

    //create a constructor that takes a Cursor and inserts the information from the Cursor into a TaskList object
    @SuppressLint("Range")
    constructor(cursor: Cursor) : this(
        cursor.moveToFirst()
            .let {
                val tasks = mutableListOf<Task>()
                while (cursor.moveToNext()) {
                    tasks.add(Task(cursor))
                }//end while loop
                tasks.toList()
            }//end let function
    ) {
        //log the tasks
        Log.d("Tasks", tasks.toString())
    }//end TaskList constructor
}//end Tasks class

//create a class based on RecyclerView.Adapter that can add tasks to the todo list using todo_item_row layout
class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {//end ViewHolder class

        //create a variable to hold the task description
        val taskDescription: EditText = view.findViewById(R.id.taskText)
        //create a variable to hold the task status
        val taskStatus: CheckBox = view.findViewById(R.id.checkbox)
        //create a variable to hold the task id
        val taskId: TextView = view.findViewById(R.id.taskId)
        val commitButton: Button = view.findViewById(R.id.commit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item_row, parent, false)
        //create a new ViewHolder object
        return ViewHolder(view)
    }//end onCreateViewHolder function

    //create a class to ho
    override fun getItemCount(): Int {
        //return the number of tasks
        return tasks.size
    }//end getItemCount function

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //get the task from the list
        val task = tasks[position]
        //set the task description
        holder.taskDescription.setText(task.taskDescription)
    }
}

//create a method to create a ViewHolder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item_row, parent, false)
        //create a new ViewHolder object
        return RecyclerView.ViewHolder(view)
    }//end onCreateViewHolder function

    //create a method to bind the ViewHolder object with the task information
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //get the task from the list
