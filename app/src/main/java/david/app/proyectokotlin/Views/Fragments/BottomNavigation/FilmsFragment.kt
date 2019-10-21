package david.app.proyectokotlin.Views.Fragments.BottomNavigation


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.core.util.Pair
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Books
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Films
import david.app.proyectokotlin.Entity.Books
import david.app.proyectokotlin.Entity.Films
import david.app.proyectokotlin.Interfaz.OnItemClickListener

import david.app.proyectokotlin.R
import david.app.proyectokotlin.Views.DetailsActivity
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FilmsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    companion object {
        fun newInstance() = FilmsFragment().apply {

        }
    }

    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    val mAdapter: RecyclerAdapter_Films = RecyclerAdapter_Films()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            mSwipeRefreshLayout = it.findViewById(R.id.SwipeRefreshLayout_films) as SwipeRefreshLayout
            mRecyclerView = it.findViewById(R.id.RecyclerView_films) as RecyclerView

            mSwipeRefreshLayout.setOnRefreshListener(this)
            mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
            )
            setUpRecyclerView()
        }
    }

    override fun onRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.isRefreshing = true
        }
        setUpRecyclerView()
        mSwipeRefreshLayout.isRefreshing = false
    }

    fun setUpRecyclerView(){
        mAdapter.RecyclerAdapter_Films(getFilms())
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val item = mAdapter.getList().get(position)

                val i = Intent(context, DetailsActivity::class.java)
                i.putExtra("Objeto", item as Serializable)
                i.putExtra("Tipo", "Films")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    val avatar = view.findViewById(R.id.films_image) as ImageView
                    val titulo = view.findViewById(R.id.films_titulo) as TextView
                    // val contenido = view.findViewById(R.id.books_contenido) as TextView

                    val viewAvatar = Pair<View, String>(avatar, avatar.transitionName)
                    val viewTitulo = Pair<View, String>(titulo, titulo.transitionName)

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        viewAvatar, viewTitulo
                    )

                    startActivity(i, options.toBundle())
                } else startActivity(i)
            }
        })
    }

    fun getFilms(): MutableList<Films> {
        var films: MutableList<Films> = ArrayList()

        films.add(
            Films(
                1,
                "Pelicula 1",
                "Contenido 1",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            )
        )
        films.add(
            Films(
                2,
                "Pelicula 2",
                "Contenido 1",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            )
        )
        films.add(
            Films(
                3,
                "Pelicula 3",
                "Contenido 1",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            )
        )
        films.add(
            Films(
                4,
                "Pelicula 4",
                "Contenido 1",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            )
        )
        films.add(
            Films(
                5,
                "Pelicula 5",
                "Contenido 1",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            )
        )
        films.add(
            Films(
                6,
                "Pelicula 6",
                "Contenido 6",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            )
        )

        return films
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
