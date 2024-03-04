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

@Entity (tableName = "site_visit")
data class SiteVisit(
    @PrimaryKey(autoGenerate = true)val visitId: Int = 0,
    @ColumnInfo(name = "site_name") var siteName: String = "",
    @ColumnInfo(name = "visit_date") var visitDate: String = "",
    @ColumnInfo(name = "visit_start_time") var visitStartTime: String = "00:00",
    @ColumnInfo(name = "visit_end_time") var visitEndTime: String = "00:00"
)
{
    fun toString(visitId: Int):String = "Site: $siteName\nDate: $visitDate\nStart Time: $visitStartTime\nEnd Time: $visitEndTime\n"

}//end SiteVisit class

@Entity (tableName = "checklist_item")
data class CheckListItem(
    @PrimaryKey(autoGenerate = true) val checkListId: Int = 0,
    @ColumnInfo(name = "visitId") val visitId: Int,
    @ColumnInfo(name = "checkListItem") val checkListItem: Int,
    @ColumnInfo(name = "check_list_item_state") var checkListItemState: String = "Not Started",
    @ColumnInfo(name = "time_spent") var timespent: String = "00:00",
    @ColumnInfo(name = "check_list_comments") var checkListComments: String = ""
)
{
    //create a toString that pulls the ChecklistItem title from ChecklistTitle by id and shows details of it
    fun toString(visitId: Int, checkListItem: Int, context:Context): String = "\tChecklist Item: " +
            "${SiteVisitDatabase.getInstance(context).checklistTitleDao().
            getChecklistTitle(checkListItem)?.title}\n\tState: $checkListItemState\n\tTime Spent: " +
            "$timespent\n\tComments: $checkListComments"
}//end CheckListItem class

@Entity (tableName = "checklist_title")
data class ChecklistTitle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "titleId")val titleId: Int = 0,
    @ColumnInfo(name = "title") val title: String = ""
) {
    override fun toString(): String {
        return title
    }//end toString()
}//end ChecklistTitle class
//create a data class that holds the relationship between ChecklistItems and ChecklistItemTitles

//create Dao's for each class
@Dao
interface SiteVisitDao {//end SiteVisitDao class
    //end SiteVisitDao class
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

}

@Dao
interface ChecklistTitleDao {
    //end ChecklistTitleDao class
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

    @Query("SELECT * FROM checklist_title WHERE title = :title")
    fun getChecklistTitle(title: String): ChecklistTitle?
}//end ChecklistTitleDao class
@Dao
interface CheckListItemDao {//end CheckListItemDao class
    @Query("SELECT * FROM checklist_item WHERE visitId = :visitId")
    fun getChecklistItems(visitId: Int): List<CheckListItem>

    @Query("SELECT * FROM checklist_item")
    fun getAllChecklistItems(): List<CheckListItem>

    @Query("SELECT * FROM checklist_item WHERE visitId = :toString")
    fun getChecklistItems(toString: String): List<CheckListItem>

    @Query("SELECT * FROM checklist_item WHERE visitId = :visitId AND checkListId = :checkListId")
    fun getChecklistItem(visitId: Int, checkListId: Int): CheckListItem?

    @Query("SELECT * FROM checklist_item WHERE visitId = :toString AND checkListId = :checkListId")
    fun getChecklistItem(toString: String, checkListId: Int): CheckListItem?

    @Query("SELECT * FROM checklist_item WHERE visitId = :visitId AND checkListItem = :checkListItem")
    fun getChecklistItem(visitId: Int, checkListItem: String): CheckListItem?

    @Query("SELECT * FROM checklist_item WHERE visitId = :toString AND checkListItem = :checkListItem")
    fun getChecklistItem(toString: String, checkListItem: String): CheckListItem?

    @Insert
    fun insertCheckListItem(checkListItem: CheckListItem)

    @Update
    fun updateCheckListItem(checkListItem: CheckListItem)

    @Delete
    fun deleteCheckListItem(checkListItem: CheckListItem)

    @Insert
    fun insert(checkListItem: CheckListItem)

}//end ChecklistItemDao class

@Database(entities = [SiteVisit::class, ChecklistTitle::class, CheckListItem::class], version = 1, exportSchema = false)
abstract class SiteVisitDatabase : RoomDatabase() {//end SiteVisitDatabase class
    abstract fun siteVisitDao(): SiteVisitDao
    abstract fun checklistTitleDao(): ChecklistTitleDao
    abstract fun checklistItemDao(): CheckListItemDao

    companion object {
        @Volatile
        private var instance: SiteVisitDatabase? = null

        fun getInstance(context: Context): SiteVisitDatabase {
            //if the instance is not null, return it, otherwise create the database and tables
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }//end syncronized
        }//end getInstance function

        private fun buildDatabase(context: Context): SiteVisitDatabase {
            if(instance != null) return instance!!
            //create the database and tables
            instance = Room.databaseBuilder(context, SiteVisitDatabase::class.java,
                "site_visit_database.db").allowMainThreadQueries().build()
            return instance as SiteVisitDatabase
        }//end buildDatabase function
    }//end companion object

}