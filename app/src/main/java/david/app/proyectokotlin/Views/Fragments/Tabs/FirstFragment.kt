package david.app.proyectokotlin.Views.Fragments.Tabs


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import david.app.proyectokotlin.Adapter.RecyclerAdapter_First
import david.app.proyectokotlin.Entity.Breakfast

import david.app.proyectokotlin.R
import kotlinx.android.synthetic.main.fragment_first.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FirstFragment : Fragment() {

    val mAdapterBreakfast: RecyclerAdapter_First = RecyclerAdapter_First()
    val mAdapterCategory: RecyclerAdapter_First = RecyclerAdapter_First()
    val mAdapterLearn: RecyclerAdapter_First = RecyclerAdapter_First()

    lateinit var carouselView: CarouselView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)

        val view: View = inflater.inflate(R.layout.fragment_first, container, false)

        carouselView = view.findViewById(R.id.carouselView) as CarouselView
        carouselView.pageCount = sampleImages.size

        carouselView.setImageListener(imageListener)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {

            mAdapterBreakfast.RecyclerAdapter_First(getBreakfast(), "BRE")
            RecyclerView_first_breakfast.setHasFixedSize(true)
            RecyclerView_first_breakfast.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            RecyclerView_first_breakfast.adapter = mAdapterBreakfast

            mAdapterCategory.RecyclerAdapter_First(getCategoria(), "CAT")
            RecyclerView_first_category.setHasFixedSize(true)
            RecyclerView_first_category.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            RecyclerView_first_category.adapter = mAdapterCategory

            mAdapterLearn.RecyclerAdapter_First(getLearn(), "LEARN")
            RecyclerView_first_Learn.setHasFixedSize(true)
            RecyclerView_first_Learn.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            RecyclerView_first_Learn.adapter = mAdapterLearn


        }
    }

    fun getBreakfast(): MutableList<Breakfast> {
        var music: MutableList<Breakfast> = ArrayList()

        for (x in 1 until 11) {
            music.add(
                Breakfast(
                    "French Toast " + x,
                    "Casserole " + x,
                    "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
                )
            )
        }

        return music
    }

    fun getCategoria(): MutableList<Breakfast> {
        var music: MutableList<Breakfast> = ArrayList()

        for (x in 1 until 11) {
            music.add(
                Breakfast(
                    "VEGETARIAN " + x,
                    "Casserole " + x,
                    "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
                )
            )
        }

        return music
    }

    fun getLearn(): MutableList<Breakfast> {
        var music: MutableList<Breakfast> = ArrayList()

        for (x in 1 until 11) {
            music.add(
                Breakfast(
                    "Outrageous Chocolate Chip Cookies " + x,
                    "Casserole " + x,
                    "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"
                )
            )
        }

        return music
    }

    internal var sampleImages =
        intArrayOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5)

    internal var imageListener: ImageListener =
        ImageListener { position, imageView ->
            imageView.setImageResource(sampleImages[position])
        }

}


