package edu.csuglobal.csc475.todo

class ToDoAdapter {
    private var toDoList = mutableListOf<ToDoItem>()

    fun addItem(item: ToDoItem) {
        toDoList.add(item)
    }//end addItem method

    fun getItem(index: Int): ToDoItem {
        return toDoList[index]
    }//end getItem method

    fun removeItem(index: Int) {
        toDoList.removeAt(index)
    }//end removeItem method

    fun getNumberOfItems(): Int {
        return toDoList.size
    }//end getNumberOfItems method

    fun getToDoList(): List<ToDoItem> {
        return toDoList
    }//end getToDoList method

    fun updateItem(index: Int, item: ToDoItem) {
        toDoList[index] = item
    }//end updateItem method

    fun clearList() {
        toDoList.clear()
    }//end clearList method

    fun sortList() {
        toDoList.sortBy { it.description }
    }//end sortList method

    fun searchList(searchTerm: String): List<ToDoItem> {
        return toDoList.filter { it.description.contains(searchTerm, ignoreCase = true) }
    }//end searchList method
}//end ToDoAdapter class
