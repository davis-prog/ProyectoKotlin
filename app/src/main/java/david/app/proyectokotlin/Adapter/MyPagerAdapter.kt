package david.app.proyectokotlin.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import david.app.proyectokotlin.Views.Fragments.Tabs.FirstFragment
import david.app.proyectokotlin.Views.Fragments.Tabs.SecondFragment
import david.app.proyectokotlin.Views.Fragments.Tabs.ThirdFragment


class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FirstFragment()
            }
            1 -> SecondFragment()
            else -> {
                return ThirdFragment()
            }
        }
    }

    override fun getCount(): Int{
        return  3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Tablero 1"
            1 -> "Tablero 2"
            else -> {
                return "Tablero 3"
            }
        }
    }


}