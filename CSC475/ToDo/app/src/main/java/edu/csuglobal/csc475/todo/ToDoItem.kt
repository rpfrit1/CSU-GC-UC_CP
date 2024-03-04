package edu.csuglobal.csc475.todo

class ToDoItem {
    var id: Int = 0
    var description: String = ""
    var isComplete: Boolean = false

    constructor(id: Int, description: String, isComplete: Boolean) {
        this.id = id
        this.description = description
        this.isComplete = isComplete
    }//end ToDoItem constructor

    constructor(description: String, isComplete: Boolean) {
        this.description = description
        this.isComplete = isComplete
    }//end ToDoItem constructor

    override fun toString(): String {
        return "$id: $description"
    }//end toString function

    fun toFormattedString(): String {
        return "$id: $description - $isComplete"
    }//end toFormattedString function

    fun toCSV(): String {
        return "$id,$description,$isComplete"
    }//end toCSV function

    fun fromFormattedString(formattedString: String) {
        val values = formattedString.split(" - ")
        id = values[0].toInt()
        description = values[1]
        isComplete = values[2].toBoolean()
    }//end fromFormattedString function

    fun fromCSV(csv: String) {
        val values = csv.split(",")
        id = values[0].toInt()
        description = values[2]
        isComplete = values[3].toBoolean()
    }//end fromCSV function

    fun toggleComplete() {
        isComplete = !isComplete
    }//end toggleComplete function
}//end ToDoItem class