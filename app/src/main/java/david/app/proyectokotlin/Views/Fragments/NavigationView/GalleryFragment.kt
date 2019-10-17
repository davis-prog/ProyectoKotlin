package david.app.proyectokotlin.Views.Fragments.NavigationView


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import david.app.proyectokotlin.Adapter.MyPagerAdapter

import david.app.proyectokotlin.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    companion object{
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance() = GalleryFragment().apply {
            arguments = Bundle(2).apply {
                putString(ARG_PARAM1, "")
                putString(ARG_PARAM2, "")
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_gallery, container, false)
        val view: View = inflater.inflate(R.layout.fragment_gallery, container,    false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            val fragmentAdapter = MyPagerAdapter(it.supportFragmentManager)
            it.viewpager_main.adapter = fragmentAdapter
            tabs_main.setupWithViewPager(viewpager_main)
            tabs_main.getTabAt(0)
        }
    }


}
