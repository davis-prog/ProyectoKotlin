package david.app.proyectokotlin.Views.Fragments.Tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Third

import david.app.proyectokotlin.R
import kotlinx.android.synthetic.main.fragment_third.*

import david.app.proyectokotlin.Entity.Desserts
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ThirdFragment : Fragment() {

    val mAdapter: RecyclerAdapter_Third = RecyclerAdapter_Third()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {

            mAdapter.RecyclerAdapter_Third(getListItemData())
            RecyclerView_Third.setHasFixedSize(true)
            RecyclerView_Third.layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            RecyclerView_Third.adapter = mAdapter

        }
    }


    private fun getListItemData(): MutableList<Desserts> {
        var itemList: MutableList<Desserts> = ArrayList()

        itemList.add(Desserts("1984", "George Orwell", "https://images.pexels.com/photos/53468/dessert-orange-food-chocolate-53468.jpeg?h=350&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("Pride and Prejudice", "Jane Austen","https://images.pexels.com/photos/39355/dessert-raspberry-leaf-almond-39355.jpeg?h=350&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("One Hundred Years of Solitude", "Gabriel Garcia Marquez","https://images.pexels.com/photos/239578/pexels-photo-239578.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("The Book Thief", "Markus Zusak","https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("The Hunger Games", "Suzanne Collins","https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("The Hitchhiker's Guide to the Galaxy", "Douglas Adams","https://images.pexels.com/photos/239578/pexels-photo-239578.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("The Theory Of Everything", "Dr Stephen Hawking", "https://images.pexels.com/photos/136745/pexels-photo-136745.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb", 0f))
        itemList.add(Desserts("The Theory ", "Dr Stephen ", "https://images.pexels.com/photos/159887/pexels-photo-159887.jpeg?h=350&auto=compress", 0f))


        return itemList
    }



}
