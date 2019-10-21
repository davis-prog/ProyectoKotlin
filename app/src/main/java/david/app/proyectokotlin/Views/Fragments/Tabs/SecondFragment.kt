package david.app.proyectokotlin.Views.Fragments.Tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Second
import david.app.proyectokotlin.Entity.Desserts

import david.app.proyectokotlin.R
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondFragment : Fragment() {

    val mAdapter: RecyclerAdapter_Second = RecyclerAdapter_Second()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            mAdapter.RecyclerAdapter_Second(getRecipe())

            RecyclerView_Second.setHasFixedSize(true)
            //layoutManager.isAutoMeasureEnabled = true
            RecyclerView_Second.layoutManager = GridLayoutManager(requireContext(), 2)
            RecyclerView_Second.itemAnimator = DefaultItemAnimator()
            RecyclerView_Second.adapter = mAdapter

        }
    }


    fun getRecipe(): MutableList<Desserts> {
        var itemList: MutableList<Desserts> = ArrayList()
        val recipe = arrayOf(
            "BLOOD ORANGE CAKE",
            "SEMIFREDDO TIRAMISU",
            "MARBLE CAKE",
            "RICE PUDDING",
            "RAINBOW CAKE",
            "ICE CREAM",
            "STROWBERRY CAKE",
            "CUPCAKE FRUIT"
        )
        val img = arrayOf(
            "https://images.pexels.com/photos/53468/dessert-orange-food-chocolate-53468.jpeg?h=350&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/159887/pexels-photo-159887.jpeg?h=350&auto=compress",
            "https://images.pexels.com/photos/136745/pexels-photo-136745.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/39355/dessert-raspberry-leaf-almond-39355.jpeg?h=350&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/239578/pexels-photo-239578.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/55809/dessert-strawberry-tart-berry-55809.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"
        )
        val time = arrayOf("1h:5'", "30m", "1h:10'", "50m", "20m", "1h:20'", "20m", "1h:20'")
        val rating = floatArrayOf(3f, 4f, 4f, 3f, 5f, 4f, 4f, 3f)

        for (i in recipe.indices) {
            itemList.add(Desserts(recipe[i], time[i], img[i], rating[i]))
        }
        return itemList
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }




}
