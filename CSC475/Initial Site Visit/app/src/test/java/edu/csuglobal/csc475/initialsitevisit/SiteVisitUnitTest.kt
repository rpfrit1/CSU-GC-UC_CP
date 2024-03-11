package edu.csuglobal.csc475.initialsitevisit

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test

class SiteVisitUnitTest {

    private lateinit var db: SiteVisitDatabase
    private lateinit var siteVisit: SiteVisit
    private lateinit var siteVisitDao: SiteVisitDao
    private lateinit var siteVisitList: List<SiteVisit>
    private lateinit var checkListItem: CheckListItem
    private lateinit var checkListItemDao: CheckListItemDao
    private lateinit var checkListItemList: List<CheckListItem>
    private lateinit var checklistTitle: ChecklistTitle
    private lateinit var checklistTitleDao: ChecklistTitleDao
    private lateinit var checklistTitleList: List<ChecklistTitle>

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SiteVisitDatabase::class.java).build()
        siteVisitDao = db.siteVisitDao()
        checkListItemDao = db.checklistItemDao()
        checklistTitleDao = db.checklistTitleDao()
        siteVisit = siteVisitList[0]
        checkListItem = checkListItemList[0]
        checklistTitle = checklistTitleList[0]
    }

    @Test
    fun testSiteVisit() {
        //create a sitevisit with a four character name, random start date, and random start time. Then upload to the database
        siteVisit = SiteVisit(
            siteName = "abcd",
            visitDate = "01/01/2022",
            visitStartTime = "12:00",
            visitEndTime = "")

        siteVisitDao.insert(siteVisit)

        //verify that the sitevisit is in the database and matches initial value
        val siteVisitFromDb = siteVisitDao.getSiteVisit(siteVisit.siteName,  siteVisit.visitDate)
        assert(siteVisitFromDb != null)
        assert(siteVisitFromDb!!.siteName == siteVisit.siteName)
        assert(siteVisitFromDb.visitDate == siteVisit.visitDate)
        assert(siteVisitFromDb.visitStartTime == siteVisit.visitStartTime)
        assert(siteVisitFromDb.visitEndTime == siteVisit.visitEndTime)
        assert(siteVisitFromDb.visitId != 0)

        //update the end time and then update database
        siteVisit = siteVisitFromDb
        siteVisit.visitEndTime = "13:00"
        siteVisitDao.updateSiteVisit(siteVisit)

        //verify that the sitevisit matches updated value
        val siteVisitFromDb2 = siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)
        assert(siteVisitFromDb2 != null)
        assert(siteVisitFromDb2!!.siteName == siteVisit.siteName)
        assert(siteVisitFromDb2.visitDate == siteVisit.visitDate)
        assert(siteVisitFromDb2.visitStartTime == siteVisit.visitStartTime)
        assert(siteVisitFromDb2.visitEndTime == siteVisit.visitEndTime)
        assert(siteVisitFromDb2.visitId == siteVisit.visitId)

    }//end testSiteVisit

    @Test
    fun testChecklistTitle() {
        //create a checklist title with "Testing checklists Unit" as the title
        checklistTitle = ChecklistTitle(
            title = "Testing checklists Unit")

        //upload to the database
        checklistTitleDao.insert(checklistTitle)

        //verify that the title is in the database and matches initial value
        val checklistTitleFromDb = checklistTitleDao.getChecklistTitle(checklistTitle.title)
        assert(checklistTitleFromDb != null)
        assert(checklistTitleFromDb!!.title == checklistTitle.title)
        assert(checklistTitleFromDb.titleId != 0)
    }//end testChecklistTitle
    @Test
    fun testCheckListItem() {
        //create a checklist item using the visit id from siteVisit and the titleId from checklistTitle
        checkListItem = CheckListItem(
            visitId = siteVisit.visitId,
            checkListItem = checklistTitle.titleId)

        //upload to the database
        checkListItemDao.insert(checkListItem)
        //verify that the checklist item is in the database and matches initial value
        val checkListItemFromDb = checkListItemDao.getChecklistItem(checkListItem.visitId, checkListItem.checkListItem)
        assert(checkListItemFromDb != null)
        assert(checkListItemFromDb!!.visitId == checkListItem.visitId)
        assert(checkListItemFromDb.checkListItem == checkListItem.checkListItem)
        assert(checkListItemFromDb.checkListComments == checkListItem.checkListComments)
        assert(checkListItemFromDb.checkListItemState == checkListItem.checkListItemState)
        assert(checkListItemFromDb.timespent == checkListItem.timespent)
        assert(checkListItemFromDb.checkListId != 0)

        //update the checklist item and then update database
        checkListItem = checkListItemFromDb
        checkListItem.checkListItemState = "Complete"
        checkListItem.timespent = "1"
        checkListItem.checkListComments = "Test"
        checkListItemDao.updateCheckListItem(checkListItem)

        //verify that the checklist item matches updated value
        val checkListItemFromDb2 = checkListItemDao.getChecklistItem(checkListItem.visitId, checkListItem.checkListItem)
        assert(checkListItemFromDb2 != null)
        assert(checkListItemFromDb2!!.visitId == checkListItem.visitId)
        assert(checkListItemFromDb2.checkListItem == checkListItem.checkListItem)
        assert(checkListItemFromDb2.checkListComments == checkListItem.checkListComments)
        assert(checkListItemFromDb2.checkListItemState == checkListItem.checkListItemState)
        assert(checkListItemFromDb2.timespent == checkListItem.timespent)
        assert(checkListItemFromDb2.checkListId == checkListItem.checkListId)
    }
}