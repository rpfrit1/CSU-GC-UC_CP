package edu.csuglobal.csc475.simpletodo

class ToDoItem(
    <<<<<<< HEAD
private val id: Int, desc: String, comp: Boolean
) {
    private var desc: String = desc
    private var comp: Boolean = comp
    ====== =
    private val id: Int, desc:String, comp:Boolean) {
        private var desc: String = desc
        private var comp: Boolean = comp
        >>>>>>> origin/main

        //create class functions
        fun toggleComplete() {
            comp = !comp
        }//end toggleComplete function

        //create setters and getters for id and desc
        <<<<<<< HEAD
        fun getId(): Int {
            ====== =
            fun getId(): Int {
                >>>>>>> origin/main
                return id
            }//end getId function


            <<<<<<< HEAD
            fun getDesc(): String {
                return desc
            }//end getDesc function

            fun setDesc(desc: String) {
                ====== =
                fun getDesc(): String {
                    return desc
                }//end getDesc function

                fun setDesc(desc: String) {
                    >>>>>>> origin/main
                    this.desc = desc
                }//end setDesc function

                //create equals function
                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (javaClass != other?.javaClass) return false

                    other as ToDoItem

                    if (id != other.id) return false
                    if (desc != other.desc) return false
                    return comp == other.comp
                }//end equals function

                override fun hashCode(): Int {
                    var result = id
                    result = 31 * result + desc.hashCode()
                    result = 31 * result + comp.hashCode()
                    return result
                }//end hashCode function

                //create compareTo function
                fun compareTo(other: ToDoItem): Int {
                    return this.desc.compareTo(other.desc)
                }//end compareTo function
}//end ToDoItem class