package david.app.proyectokotlin.Views.Fragments.NavigationView


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import david.app.proyectokotlin.Adapter.RecyclerAdapter
import david.app.proyectokotlin.Entity.Superhero
import david.app.proyectokotlin.Interfaz.OnItemClickListener

import david.app.proyectokotlin.R
import david.app.proyectokotlin.Views.DetailsActivity
import java.io.Serializable

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance() = HomeFragment().apply {
            arguments = Bundle(2).apply {
                putString(ARG_PARAM1, "")
                putString(ARG_PARAM2, "")
            }
        }
    }

    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mFloatingActionButton: FloatingActionButton
    val mAdapter: RecyclerAdapter = RecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            mRecyclerView = it.findViewById(R.id.RecyclerView) as RecyclerView
            mSwipeRefreshLayout = it.findViewById(R.id.simpleSwipeRefreshLayout) as SwipeRefreshLayout
            mFloatingActionButton = it.findViewById(R.id.fab_agregar) as FloatingActionButton
            //searchBreed.setOnQueryTextListener()

            mSwipeRefreshLayout.setOnRefreshListener(this)
            mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
            )
            /*mSwipeRefreshLayout.post { (action) -> Runnable {
                   setUpRecyclerView()
               } }*/

            setUpRecyclerView()

            mFloatingActionButton.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.isRefreshing = true
        }
        setUpRecyclerView()
        mSwipeRefreshLayout.isRefreshing = false
    }

    fun setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter.RecyclerAdapter(getSuperheroes(), requireContext())
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val item = mAdapter.getList().get(position)

                //toast(item.superhero!!)
                //Toast.makeText(context, item.superhero, Toast.LENGTH_SHORT).show()

                val i = Intent(context, DetailsActivity::class.java)
                i.putExtra("Objeto", item as Serializable)
                i.putExtra("Tipo", "Home")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    val Avatar = view.findViewById(R.id.ivAvatar) as ImageView
                    val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
                    val realName = view.findViewById(R.id.tvRealName) as TextView
                    val publisher = view.findViewById(R.id.tvPublisher) as TextView

                    val viewAvatar = Pair<View, String>(Avatar, Avatar.transitionName)
                    val viewSuperheroName = Pair<View, String>(superheroName, superheroName.transitionName)
                    val viewRealName = Pair<View, String>(realName, realName.transitionName)
                    val viewPublisher = Pair<View, String>(publisher, publisher.transitionName)


                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        viewAvatar, viewSuperheroName, viewRealName, viewPublisher
                    )

                    startActivity(i, options.toBundle())

                } else startActivity(i)
            }
        })


        mRecyclerView!!.itemAnimator = DefaultItemAnimator()

        /*  mRecyclerView.itemAnimator?.apply {
              addDuration = 1000
              removeDuration = 100
              moveDuration = 1000
              changeDuration = 100
          }*/
    }

    fun getSuperheroes(): MutableList<Superhero> {
        var superheros: MutableList<Superhero> = ArrayList()
        superheros.add(
            Superhero(
                1,
                "Spiderman",
                "Marvel",
                "Peter Parker",
                "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"
            )
        )
        superheros.add(
            Superhero(
                2,
                "Daredevil",
                "Marvel",
                "Matthew Michael Murdock",
                "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
            )
        )
        superheros.add(
            Superhero(
                3,
                "Wolverine",
                "Marvel",
                "James Howlett",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            )
        )
        superheros.add(
            Superhero(
                4,
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            )
        )
        superheros.add(
            Superhero(
                5,
                "Thor",
                "Marvel",
                "Thor Odinson",
                "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"
            )
        )
        superheros.add(
            Superhero(
                6,
                "Flash",
                "DC",
                "Jay Garrick",
                "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"
            )
        )
        superheros.add(
            Superhero(
                7,
                "Green Lantern",
                "DC",
                "Alan Scott",
                "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"
            )
        )
        superheros.add(
            Superhero(
                8,
                "Wonder Woman",
                "DC",
                "Princess Diana",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        return superheros
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }
}
