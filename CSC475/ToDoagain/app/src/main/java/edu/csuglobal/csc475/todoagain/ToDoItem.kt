package edu.csuglobal.csc475.todoagain

//create a TodoItem class for a todo item
<<<<<<< HEAD
class ToDoItem(
    private var desc: String,
    private var id: Int = 0,
    private var done: Boolean = false
) {
    =======
    class ToDoItem(
        private var desc: String,
        private var id: Int = 0,
        private var done: Boolean = false
    ) {
        >>>>>>> origin/main

        //create a function to toggle  the status of a todo item
        fun toggleStatus() {
            //toggle the status of the todo item
            done = !done
        }//end toggleStatus function

        //create a function to get the status of a todo item
        <<<<<<< HEAD
        fun getStatus(): Boolean {
            ====== =
            fun getStatus(): Boolean {
                >>>>>>> origin/main
                //get the status of the todo item
                return done
            }//end getStatus function

            //create a function to get the description of a todo item
            <<<<<<< HEAD
            fun getDescription(): String {
                ====== =
                fun getDescription(): String {
                    >>>>>>> origin/main
                    //get the description of the todo item
                    return desc
                }//end getDescription function

                //create a function to get the id of a todo item
                <<<<<<< HEAD
                fun getId(): Int {
                    ====== =
                    fun getId(): Int {
                        >>>>>>> origin/main
                        //get the id of the todo item
                        return id
                    }//end getId function

                    //create a function to change the description of a todo item
                    <<<<<<< HEAD
                    fun changeDescription(newDesc: String) {
                        ====== =
                        fun changeDescription(newDesc: String) {
                            >>>>>>> origin/main
                            //change the description of the todo item
                            desc = newDesc
                        }//end changeDescription function
                    }//end ToDoItem class