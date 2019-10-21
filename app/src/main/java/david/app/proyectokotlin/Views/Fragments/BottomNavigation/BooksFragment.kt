package david.app.proyectokotlin.Views.Fragments.BottomNavigation


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.core.util.Pair
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import david.app.proyectokotlin.Adapter.RecyclerAdapter
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Books
import david.app.proyectokotlin.Entity.Books
import david.app.proyectokotlin.Interfaz.OnItemClickListener

import david.app.proyectokotlin.R
import david.app.proyectokotlin.Views.DetailsActivity
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BooksFragment : Fragment() {
    companion object {
        fun newInstance() = BooksFragment().apply {

        }
    }

    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView
    lateinit var mRecyclerView2: RecyclerView
    val mAdapter_novelas: RecyclerAdapter_Books = RecyclerAdapter_Books()
    val mAdapter_cuentos: RecyclerAdapter_Books = RecyclerAdapter_Books()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            // mSwipeRefreshLayout = it.findViewById(R.id.SwipeRefreshLayout_books) as SwipeRefreshLayout

            mAdapter_novelas.RecyclerAdapter_Books(getBooksNovelas())
            mAdapter_cuentos.RecyclerAdapter_Books(getBooksCuentos())

            mRecyclerView = it.findViewById(R.id.RecyclerView_books) as RecyclerView
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            mRecyclerView.adapter = mAdapter_novelas
            mRecyclerView.addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val item = mAdapter_novelas.getList().get(position)

                    val i = Intent(context, DetailsActivity::class.java)
                    i.putExtra("Objeto", item as Serializable)
                    i.putExtra("Tipo", "Books")//"Books_Novelas")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        val avatar = view.findViewById(R.id.books_image) as ImageView
                        val titulo = view.findViewById(R.id.books_titulo) as TextView
                        val contenido = view.findViewById(R.id.books_contenido) as TextView

                        val viewAvatar = Pair<View, String>(avatar, avatar.transitionName)
                        val viewTitulo = Pair<View, String>(titulo, titulo.transitionName)
                        val viewContenido = Pair<View, String>(contenido, contenido.transitionName)

                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(),
                            viewAvatar, viewTitulo, viewContenido
                        )

                        startActivity(i, options.toBundle())
                    } else startActivity(i)
                }
            })


            mRecyclerView2 = it.findViewById(R.id.RecyclerView_books2) as RecyclerView
            mRecyclerView2.setHasFixedSize(true)
            mRecyclerView2.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            mRecyclerView2.adapter = mAdapter_cuentos
            mRecyclerView2.addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val item = mAdapter_cuentos.getList().get(position)

                    val i = Intent(context, DetailsActivity::class.java)
                    i.putExtra("Objeto", item as Serializable)
                    i.putExtra("Tipo", "Books")//"Books_Cuentos")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        val avatar = view.findViewById(R.id.books_image) as ImageView
                        val titulo = view.findViewById(R.id.books_titulo) as TextView
                        val contenido = view.findViewById(R.id.books_contenido) as TextView

                        val viewAvatar = Pair<View, String>(avatar, avatar.transitionName)
                        val viewTitulo = Pair<View, String>(titulo, titulo.transitionName)
                        val viewContenido = Pair<View, String>(contenido, contenido.transitionName)

                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(),
                            viewAvatar, viewTitulo, viewContenido
                        )

                        startActivity(i, options.toBundle())
                    } else startActivity(i)
                }
            })
        }
    }

    fun getBooksNovelas(): MutableList<Books> {
        var books: MutableList<Books> = ArrayList()

       /* books.add(Books(1, "Novela 1", "Novela", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        books.add(Books(2, "Novela 2", "Novela", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        books.add(Books(3, "Novela 3", "Novela", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        books.add(Books(4, "Novela 4", "Novela", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        books.add(Books(5, "Novela 5", "Novela", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
*/

        for (x in 1 until 11) {
            // Prints 0 through 9
            books.add(
                Books(
                    x,
                    "Novela " + x,
                    "Descripcion",
                    "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"
                )
            )
        }

        return books
    }

    fun getBooksCuentos(): MutableList<Books> {
        var books: MutableList<Books> = ArrayList()

        for (x in 1 until 121) {
            // Prints 0 through 9
            books.add(
                Books(
                    x,
                     "Cuento " + x,
                    "Descripcion",
                    "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
                )
            )
        }


       /* books.add(
            Books(
                1,
                "Novela 1",
                "Cuentos",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        books.add(
            Books(
                2,
                "Novela 2",
                "Cuentos",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        books.add(
            Books(
                3,
                "Novela 3",
                "Cuentos",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        books.add(
            Books(
                4,
                "Novela 4",
                "Cuentos",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        books.add(
            Books(
                5,
                "Novela 5",
                "Cuentos",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
*/
        return books
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
