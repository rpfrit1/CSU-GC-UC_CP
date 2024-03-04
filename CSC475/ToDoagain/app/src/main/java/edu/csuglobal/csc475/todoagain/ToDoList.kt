package edu.csuglobal.csc475.todoagain

<<<<<<< HEAD
class ToDoList<ToDoItem> : ArrayList<ToDoItem>() {
=======
class ToDoList<ToDoItem>: ArrayList<ToDoItem>() {
>>>>>>> origin/main
    //create a list of items
    private val items = mutableListOf<ToDoItem>()

    //create a function to get the list of items
    fun get(): List<ToDoItem> = items

    //create a function to remove the item at the specified index
    fun remove(index: Int) {
        //remove the item at the specified index
        items.removeAt(index)
    }//end remove function

}//end ToDoList class