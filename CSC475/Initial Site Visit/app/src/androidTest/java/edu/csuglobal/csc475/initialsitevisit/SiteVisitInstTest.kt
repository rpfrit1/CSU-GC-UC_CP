package edu.csuglobal.csc475.initialsitevisit

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.matcher.IntentMatchers.hasType
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.runner.intent.IntentStubber
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@SmallTest
class SiteVisitInstTest {
    private var retrievedSiteVisit: SiteVisit? = null
    private var retrievedChecklistTitle: ChecklistTitle? = null
    private var retrievedCheckListItem: CheckListItem? = null
    private lateinit var db: SiteVisitDatabase
    private lateinit var siteVisitDao: SiteVisitDao
    private lateinit var checklistTitleDao: ChecklistTitleDao
    private lateinit var checkListItemDao: CheckListItemDao
    private lateinit var siteVisit: SiteVisit
    private var visitId: Int = 0
    private lateinit var checkListItem: CheckListItem
    private var itemId: Int = 0
    private lateinit var checklistTitle: ChecklistTitle
    private var titleId: Int = 0

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SiteVisitDatabase::class.java).build()
        siteVisitDao = db!!.siteVisitDao()
        checklistTitleDao = db!!.checklistTitleDao()
        checkListItemDao = db!!.checklistItemDao()

        //create a new site visit with the random site id, visitDate, and visitStartTime
        siteVisit = SiteVisit(
            siteName = "ABC3",
            visitDate = "03/10/2024",
            visitStartTime = "08:00",
            visitEndTime = "10:00"
        )
        siteVisitDao.insertSiteVisit(siteVisit)
        visitId = siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)!!.visitId

        //create a new checklist title with a random title
        checklistTitle = ChecklistTitle(title = "Testing database connection")
        checklistTitleDao.insert(checklistTitle)
        titleId = checklistTitleDao.getChecklistTitle(checklistTitle.title)!!.titleId

        //create a new checklist item with a random item
        checkListItem = CheckListItem(
            visitId = visitId,
            checkListItem = titleId,
            startTime = "0",
            checkListItemState = "Completed Successfully",
            checkListComments = "Test completed",
            timespent = "45"
        )
        checkListItemDao.insert(checkListItem)
        itemId = checkListItemDao.getChecklistItem(visitId, titleId)!!.checkListId

    }//end setup

    @Test
    @Throws(Exception::class)
    fun testSiteVisit() {
        testSiteVisitValid()
        testSiteVisitName()
        testSiteVisitDate()
        testSiteVisitStart()
        testSiteVisitEnd()
        testCheckListTitleValid()
        testCheckListTitle()
        testCheckListItemValid()
        testCheckListItemTitle()
        testCheckListItemStartTime()
        testCheckListItemState()
        testCheckListItemComments()
        testCheckListItemTimespent()
    }//end testSiteVisit

    @Test
    fun testCheckListTitleValid() {
        assert(checklistTitleDao.getChecklistTitle(checklistTitle.title) != null)
    }//end testCheckListTitleValid

    @Test
    fun testCheckListTitle() {
        assert(checklistTitleDao.getChecklistTitle(checklistTitle.title)?.title == checklistTitle.title)
    }//end testCheckListTitle

    @Test
    fun testCheckListItemValid() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId) != null)
    }//end testCheckListItemValid

    @Test
    fun testCheckListItemTitle() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId)?.checkListItem == checkListItem.checkListItem)
    }//end testCheckListItemTitle

    @Test
    fun testCheckListItemStartTime() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId)?.startTime == checkListItem.startTime)
    }

    @Test
    fun testCheckListItemState() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId)?.checkListItemState == checkListItem.checkListItemState)
    }//end testCheckListItemState

    @Test
    fun testCheckListItemComments() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId)?.checkListComments == checkListItem.checkListComments)
    }//end testCheckListItemComments

    @Test
    fun testCheckListItemTimespent() {
        assert(checkListItemDao.getChecklistItem(visitId, titleId)?.timespent == checkListItem.timespent)
    }//end testCheckListItemTimespent

    @Test
    fun testSiteVisitEnd() {
        assert(siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)?.visitEndTime == siteVisit.visitEndTime)
    }//end testSiteVisitEnd

    @Test
    fun testSiteVisitStart() {
        assert(siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)?.visitStartTime == siteVisit.visitStartTime)
    }//end testSiteVisitStart

    @Test
    fun testSiteVisitDate() {
        assert(siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)?.visitDate == siteVisit.visitDate)
    }//end testSiteVisitDate

    @Test
    fun testSiteVisitName() {
        assert(siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)?.siteName == siteVisit.siteName)
    }

    @Test
    fun testSiteVisitValid() {
        assert(siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate) != null)
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }//end closeDb
}//end SiteVisitInstTest

//create  tests for the TaskDetail activity
@RunWith(AndroidJUnit4::class)
@SmallTest
class CheckListItemTest {
    private lateinit var db: SiteVisitDatabase
    private lateinit var siteVisitDao: SiteVisitDao
    private lateinit var checklistTitleDao: ChecklistTitleDao
    private lateinit var checkListItemDao: CheckListItemDao
    private lateinit var siteVisit: SiteVisit
    private lateinit var checkListItem: CheckListItem
    private lateinit var checklistTitle: ChecklistTitle

    @get:Rule
    var activityRule = ActivityScenarioRule(TaskDetail::class.java)
    @get:Rule
    var intentsRule = IntentsTestRule(TaskDetail::class.java)


    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SiteVisitDatabase::class.java).build()
        siteVisitDao = db.siteVisitDao()
        checklistTitleDao = db.checklistTitleDao()
        checkListItemDao = db.checklistItemDao()

        //create a new site visit with the random site id, visitDate, and visitStartTime
        siteVisit = SiteVisit(
            siteName = "ABC3",
            visitDate = "03/10/2024",
            visitStartTime = "08:00",
            visitEndTime = "10:00"
        )
        siteVisitDao.insertSiteVisit(siteVisit)
        siteVisit = siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)!!

        //create a new checklist title with a random title
        checklistTitle = ChecklistTitle(title = "Testing database connection")
        checklistTitleDao.insert(checklistTitle)
        checklistTitle = checklistTitleDao.getChecklistTitle(checklistTitle.title)!!

        //create a new checklist item with a random item
        checkListItem = CheckListItem(
            visitId = siteVisit.visitId,
            checkListItem = checklistTitle.titleId,
            checkListComments = "Testing"
        )
        checkListItemDao.insert(checkListItem)
        checkListItem =
            checkListItemDao.getChecklistItem(siteVisit.visitId, checklistTitle.titleId)!!
    }//end setup
/*
    //create a test for btnBackToList and verify that it loads the TaskList activity
    @Test
    fun testBtnBackToList() {
        //verify that the TaskList activity is loaded when btnBackToList is clicked
        onView(withId(R.id.btnBackToList)).perform(click())
        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasExtra("visitId", checkListItem.visitId))
        intended(hasComponent(TaskList::class.java.name))
    }//end testBtnBackToList
*/
    //create tests for the comments area of the TaskDetail activity
    @Test
    fun testComments() {
        val comments = onView(withId(R.id.txtNotes))
        comments.perform(typeText("Test completed"))

        //verify that txtComments has the correct comments
        onView(withId(R.id.txtNotes)).check(matches(withText("Test completed")))
    }//end testComments
/*
    //create tests for each button in the activity
    @Test
    fun testBtnSuccess() {
        //set the starttime of checkListItem to five minutes ago today
        val startTime = System.currentTimeMillis() - (5 / 1000 / 60)
        checkListItem.startTime = startTime.toString()
        checkListItemDao.updateCheckListItem(checkListItem)

        //verify that the state of checkListItem changes to "Completed Successful" and timespent is set to the difference between startTime and current time
        onView(withId(R.id.btnSuccess)).perform(click())
        checkListItemDao.updateCheckListItem(checkListItem)
        assert(checkListItem.checkListItemState == "Completed Successfully")
    }//end testBtnSuccess

    @Test
    fun testBtnProblems() {
        //set the starttime of checkListItem to five minutes ago today
        val startTime = System.currentTimeMillis() - (5 / 1000 / 60)
        checkListItem.startTime = startTime.toString()

        //verify that the state of checkListItem changes to "Completed with Issues" and timespent is set to the difference between startTime and current time
        onView(withId(R.id.btnProblems)).perform(click())
        assert(checkListItem.checkListItemState == "Completed with Issues")
    }//end testBtnProblems

    @Test
    fun testBtnInProcess() {
        //verify that when btnInProcess is clicked, the state goes to "In Process" and starttime is set to current time
        onView(withId(R.id.btnInProcess)).perform(click())
            .check(matches(withText(checkListItem.checkListItemState)))
        assert(checkListItem.checkListItemState == "In Process")
    }//end testBtnInProcess

    @Test
    fun testBtnNa() {
        //set starttime of checkListItem to five minutes ago today
        val startTime = System.currentTimeMillis() - (5 / 1000 / 60)
        checkListItem.startTime = startTime.toString()

        //verify that the state of checkListItem changes to "Not Applicable"
        onView(withId(R.id.btnNa)).perform(click())
        assert(checkListItem.checkListItemState == "Not Applicable")
    }//end testBtnNa
*/
    @Test
    fun testBtnNotStarted() {
        //verify that when btnNotStarted is clicked, the state goes to "Not Started" and starttime and timespent are empty
        onView(withId(R.id.btnNotStarted)).perform(click())
        assert(checkListItem.checkListItemState == "Not Started")
        assert(checkListItem.startTime == "")
    }//end testBtnNotStarted
}//end CheckListItemTest

//create tests for the MainActivity
@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {
    private lateinit var db: SiteVisitDatabase
    private lateinit var siteVisitDao: SiteVisitDao
    private lateinit var checklistTitleDao: ChecklistTitleDao
    private lateinit var checkListItemDao: CheckListItemDao
    private lateinit var siteVisit: SiteVisit
    private var visitId: Int = 0
    private lateinit var checkListItem: CheckListItem
    private var itemId: Int = 0
    private lateinit var checklistTitle: ChecklistTitle
    private var titleId: Int = 0

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule
    var intentsRule = IntentsTestRule(MainActivity::class.java)

    //create a test for btnToList verifying that it loads the TaskList activity
    @Test
    fun testBtnToList() {
        onView(withId(R.id.btnToList)).perform(click())
        intended(hasComponent(TaskList::class.java.name))
    }//end testBtnToList

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SiteVisitDatabase::class.java).build()
        siteVisitDao = db!!.siteVisitDao()
        checklistTitleDao = db!!.checklistTitleDao()
        checkListItemDao = db!!.checklistItemDao()

        //create a new site visit with the random site id, visitDate, and visitStartTime
        siteVisit = SiteVisit(
            siteName = "ABC3",
            visitDate = "03/10/2024",
            visitStartTime = "08:00",
            visitEndTime = "10:00"
        )
        siteVisitDao.insertSiteVisit(siteVisit)
        visitId = siteVisitDao.getSiteVisit(siteVisit.siteName, siteVisit.visitDate)!!.visitId

        //create a new checklist title with a random title
        checklistTitle = ChecklistTitle(title = "Testing database connection")
        checklistTitleDao.insert(checklistTitle)
        titleId = checklistTitleDao.getChecklistTitle(checklistTitle.title)!!.titleId

        //create a new checklist item with a random item
        checkListItem = CheckListItem(
            visitId = visitId,
            checkListItem = titleId,
            startTime = "0",
            checkListItemState = "Completed Successfully",
            checkListComments = "Test completed",
            timespent = "45"
        )
        checkListItemDao.insert(checkListItem)
        itemId = checkListItemDao.getChecklistItem(visitId, titleId)!!.checkListId
    }//end setup


    //create tests for the date, start time, and end time pickers on MainActivity
    @Test
    fun testDatePicker() {
        //verify that a DatePicker comes up when btnDate is clicked
        onView(withId(R.id.btnDate)).perform(click())
        onView(withClassName(Matchers.`is`("android.widget.DatePicker"))).
        perform(PickerActions.setDate(2024,3,10))
    }//end testDatePicker
    @Test
    fun testStartTimePicker() {
        //verify that a TimePicker dialog loads when btnStartTime is clicked
        onView(withId(R.id.btnStartTime)).perform(click())
        onView(withClassName(Matchers.`is`("android.widget.TimePicker")))
            .perform(PickerActions.setTime(8,0))
    }//end testStartTimePicker
    @Test
    fun testEndTimePicker() {
        //verify that a TimePicker loads when btnEndTime is clicked
        onView(withId(R.id.btnEndTime)).perform(click())
        onView(withClassName(Matchers.`is`("android.widget.TimePicker")))
    }//end testEndTimePicker

    //create tests for the text fields on MainActivity
    @Test
    fun testSiteName() {
        val siteName = onView(withId(R.id.txtSite))
        siteName.perform(typeText("ABC3"))

        //verify that txtSite has the correct site name
        onView(withId(R.id.txtSite)).check(matches(withText("ABC3")))
    }//end testSiteName

    @Test
    fun testDate() {
        val date = onView(withId(R.id.txtDate))
        date.perform(typeText("03/10/2024"))

        //verify that txtDate has the correct date
        onView(withId(R.id.txtDate)).check(matches(withText("03/10/2024")))
    }//end testDate

    @Test
    fun testStartTime() {
        //use mainActivityRule for this function

        val startTime = onView(withId(R.id.txtStartTime))
        startTime.perform(typeText("08:00"))

        //verify that txtStartTime has the correct time
        onView(withId(R.id.txtStartTime)).check(matches(withText("08:00")))
    }//end testStartTime

    @Test
    fun testEndTime() {
        val endTime = onView(withId(R.id.txtEndTime))
        endTime.perform(typeText("10:00"))

        //verify that txtEndTime has the correct time
        onView(withId(R.id.txtEndTime)).check(matches(withText("10:00")))
    }//end testEndTime
}//end MainActivityTest
