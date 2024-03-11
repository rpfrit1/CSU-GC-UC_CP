package edu.csuglobal.csc475.initialsitevisit

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(tableName = "site_visit")
data class SiteVisit(
    @PrimaryKey(autoGenerate = true) val visitId: Int = 0,
    @ColumnInfo(name = "site_name") var siteName: String = "",
    @ColumnInfo(name = "visit_date") var visitDate: String = "",
    @ColumnInfo(name = "visit_start_time") var visitStartTime: String = "00:00",
    @ColumnInfo(name = "visit_end_time") var visitEndTime: String = "00:00") {
    fun toString(visitId: Int): String =
        "Site: $siteName\nDate: $visitDate\nStart Time: $visitStartTime\nEnd Time: $visitEndTime\n"
}//end SiteVisit class

@Entity(tableName = "checklist_item")
data class CheckListItem(
    @PrimaryKey(autoGenerate = true) val checkListId: Int = 0,
    @ColumnInfo(name = "visitId") val visitId: Int,
    @ColumnInfo(name = "checkListItem") val checkListItem: Int,
    @ColumnInfo(name = "check_list_item_state") var checkListItemState: String = "Not Started",
    @ColumnInfo(name = "time_spent") var timespent: String = "00:00",
    @ColumnInfo(name = "check_list_comments") var checkListComments: String = "",
    @ColumnInfo(name = "start_time") var startTime: String = "") {
    //create a toString that pulls the ChecklistItem title from ChecklistTitle by id and shows details of it

    override fun toString(): String =
        "\n\tState: $checkListItemState  \n\tTime Spent: $timespent  \n\tComments: $checkListComments"
    fun toString(context: Context): String =
        "\tChecklist Item: " + "${SiteVisitDatabase.getInstance(context).checklistTitleDao().getChecklistTitle(checkListItem)?.title}\n\tState: $checkListItemState\n\tTime Spent: $timespent\n\tComments: $checkListComments"
}//end CheckListItem class

@Entity(tableName = "checklist_title")
data class ChecklistTitle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "titleId") val titleId: Int = 0,
    @ColumnInfo(name = "checklist_title") var title: String = "") {
    //create a toString that pulls the ChecklistItem title from ChecklistTitle by id and shows details of it
    override fun toString(): String = "\tTitle: $title"
}//end CheckListTitle class

//create Dao's for each class
@Dao
interface SiteVisitDao {
        @Query("SELECT * FROM site_visit")
        fun getAllSiteVisits(): List<SiteVisit>

        @Insert
        fun insertSiteVisit(siteVisit: SiteVisit)

        @Update
        fun updateSiteVisit(siteVisit: SiteVisit)

        @Delete
        fun deleteSiteVisit(siteVisit: SiteVisit)

        @Query("SELECT * FROM site_visit WHERE visitId = :visitId")
        fun getSiteVisit(visitId: Int): SiteVisit?

        @Query("SELECT * FROM site_visit WHERE visitId = :site_name")
        fun getSiteVisit(site_name: String): SiteVisit?

        @Insert
        fun insert(siteVisit: SiteVisit)

        @Query("SELECT * FROM site_visit WHERE site_name = :name AND visit_date = :date")
        fun getSiteVisit(name: String, date: String): SiteVisit?

    }//end SiteVisitDao class

@Dao
interface ChecklistTitleDao {

    @Query("SELECT * FROM checklist_title")
    fun getAllChecklistTitles(): List<ChecklistTitle>

    @Insert
    fun insertChecklistTitle(checklistTitle: ChecklistTitle)

    @Update
    fun updateChecklistTitle(checklistTitle: ChecklistTitle)

    @Delete
    fun deleteChecklistTitle(checklistTitle: ChecklistTitle)

    @Query("SELECT * FROM checklist_title WHERE titleId = :titleId")
    fun getChecklistTitle(titleId: Int): ChecklistTitle?

    @Insert
    fun insert(checklistTitle: ChecklistTitle)

    @Query("SELECT * FROM checklist_title WHERE checklist_title = :title")
    fun getChecklistTitle(title: String): ChecklistTitle?
}//end ChecklistTitleDao class

@Dao
interface CheckListItemDao {
            @Query("SELECT * FROM checklist_item WHERE visitId = :visitId")
            fun getChecklistItems(visitId: Int): List<CheckListItem>

            @Query("SELECT * FROM checklist_item")
            fun getAllChecklistItems(): List<CheckListItem>


            @Query("SELECT * FROM checklist_item WHERE visitId = :visitId AND checkListId = :checkListItem")
            fun getChecklistItem(visitId: Int, checkListItem: Int): CheckListItem?

            @Query("SELECT * FROM checklist_item WHERE checklistId = :checkListId")
            fun getChecklistItem(checkListId: Int): CheckListItem?

            @Insert
            fun insertCheckListItem(checkListItem: CheckListItem)

            @Update
            fun updateCheckListItem(checkListItem: CheckListItem)

            @Delete
            fun deleteCheckListItem(checkListItem: CheckListItem)

            @Insert
            fun insert(checkListItem: CheckListItem)

        }//end ChecklistItemDao class

//create a Database class named SiteVisitDatabase
@Database(entities = [SiteVisit::class, CheckListItem::class, ChecklistTitle::class], version = 1)
abstract class SiteVisitDatabase : RoomDatabase() {
    abstract fun siteVisitDao(): SiteVisitDao
    abstract fun checklistTitleDao(): ChecklistTitleDao
    abstract fun checklistItemDao(): CheckListItemDao

    companion object {
        @Volatile
        private var INSTANCE: SiteVisitDatabase? = null

        fun getInstance(context: Context): SiteVisitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SiteVisitDatabase::class.java,
                    "site_visit_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }//end synchronized function
        }//end getInstance function

        fun destroyInstance() {
            INSTANCE = null
        }//end destroyInstance function

        fun getDatabase(context: Context): SiteVisitDatabase {
            return getInstance(context)
        }//end getDatabase function
    }//end companion object
}//end SiteVisitDatabase class