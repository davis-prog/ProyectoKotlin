package david.app.proyectokotlin.Views.Fragments.NavigationView


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import david.app.proyectokotlin.Eventos.BottomNavigationBehavior

import david.app.proyectokotlin.R
import david.app.proyectokotlin.Views.Fragments.BottomNavigation.BooksFragment
import david.app.proyectokotlin.Views.Fragments.BottomNavigation.FilmsFragment
import david.app.proyectokotlin.Views.Fragments.BottomNavigation.MusicFragment
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment(), BottomNavigationView.OnNavigationItemReselectedListener {
    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance() = SlideshowFragment().apply {
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
        //return inflater.inflate(R.layout.fragment_slideshow, container, false)
        val view: View = inflater.inflate(R.layout.fragment_slideshow, container, false)
        /*    var navigation_view = view.findViewById(R.id.bottom_navigation_view) as BottomNavigationView
            navigation_view.setOnNavigationItemReselectedListener(this)
    */
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            bottom_navigation_view.setOnNavigationItemReselectedListener(this)
            bottom_navigation_view.selectedItemId = R.id.action_films
            navigateToFragment(MusicFragment.newInstance())
        }
    }

    override fun onNavigationItemReselected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.action_music -> {
                menuItem.isChecked = true
                navigateToFragment(MusicFragment.newInstance())
                true
            }
            R.id.action_films -> {
                menuItem.isChecked = true
                navigateToFragment(FilmsFragment.newInstance())
                true
            }
            R.id.action_books -> {
                menuItem.isChecked = true
                navigateToFragment(BooksFragment.newInstance())
                true
            }
            else -> false
        }
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.main_FragmentContainer, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
