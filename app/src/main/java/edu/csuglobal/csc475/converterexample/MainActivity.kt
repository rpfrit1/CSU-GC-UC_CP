package edu.csuglobal.csc475.converterexample

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //initiate properties
        tabLayout = findViewById(R.id.conversionTabs)
        val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout.tabCount)
        val viewPager = findViewById<ViewPager>(R.id.pager)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.text = "Length"
        tabLayout.getTabAt(1)?.text = "Temperature"
        tabLayout.getTabAt(2)?.text = "Mass/Weight"
        tabLayout.getTabAt(3)?.text = "Area"
        tabLayout.getTabAt(4)?.text = "Volume"

        //create listeners for the viewPager and the tabLayout
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }//end onPageScrollStateChanged function

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }//end onPageScrolled function

            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }//end onPageSelected function
        })//end onPageChangeListener object

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }//end onTabSelected function

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                }//end onTabUnselected function

            override fun onTabReselected(tab: TabLayout.Tab?) {
                }//end onTabReselected function
            })//end onTabSelectedListener object
    }//end onCreate function
}//end MainActivity class

