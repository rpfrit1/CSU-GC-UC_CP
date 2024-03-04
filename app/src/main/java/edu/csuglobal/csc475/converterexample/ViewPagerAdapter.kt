package edu.csuglobal.csc475.converterexample

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    val context: Context,
    val fm: FragmentManager, val totalTabs:Int): FragmentPagerAdapter(fm) {
    //end ViewPagerAdapter class
    override fun getCount(): Int {
        return totalTabs
    }//end getCount function

    override fun getItem(position: Int): Fragment {
        when (position) {
            0-> return LengthFragment()
            1 -> return TempFragment()
            2 -> return MassFragment()
            3 -> return AreaFragment()
            4 -> return VolumeFragment()
            else -> return LengthFragment()
        }//end when
    }//end getItem function

}//end ViewPagerAdapter class
