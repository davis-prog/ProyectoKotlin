package david.app.proyectokotlin.Views.Fragments.BottomNavigation


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import david.app.proyectokotlin.Adapter.RecyclerAdapter_Music
import david.app.proyectokotlin.Entity.Music
import david.app.proyectokotlin.Eventos.BottomNavigationBehavior
import david.app.proyectokotlin.Interfaz.OnItemClickListener

import david.app.proyectokotlin.R
import david.app.proyectokotlin.Views.DetailsActivity
import kotlinx.android.synthetic.main.fragment_music.*
import kotlinx.android.synthetic.main.fragment_slideshow.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MusicFragment : Fragment() {
    companion object {
        fun newInstance() = MusicFragment().apply {

        }
    }

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: RecyclerAdapter_Music = RecyclerAdapter_Music()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.let {
            mRecyclerView = it.findViewById(R.id.RecyclerView_music) as RecyclerView

            mAdapter.RecyclerAdapter_Music(getMusic())
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            mRecyclerView.adapter = mAdapter
            mRecyclerView.addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val item = mAdapter.getList().get(position)
                    val i = Intent(context, DetailsActivity::class.java)
                    i.putExtra("Objeto", item as Serializable)
                    i.putExtra("Tipo", "Music")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        val avatar = view.findViewById(R.id.music_avatar) as ImageView
                        val titulo = view.findViewById(R.id.music_titulo) as TextView
                        val autor = view.findViewById(R.id.music_autor) as TextView
                        val contenido = view.findViewById(R.id.music_contenido) as TextView

                        val viewAvatar = Pair<View, String>(avatar, avatar.transitionName)
                        val viewTitulo = Pair<View, String>(titulo, titulo.transitionName)
                        val viewAuto = Pair<View, String>(autor, autor.transitionName)
                        val viewContenido = Pair<View, String>(contenido, contenido.transitionName)

                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(),
                            viewAvatar, viewTitulo, viewAuto, viewContenido
                        )

                        startActivity(i, options.toBundle())
                    } else startActivity(i)


                }
            })

        }
    }

    fun getMusic(): MutableList<Music> {
        var music: MutableList<Music> = ArrayList()

        for (x in 1 until 11) {
            music.add(
                Music(
                    "Musica " + x,
                    "Autor " + x,
                    "Contenido " + x,
                    "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
                )
            )
        }

        return music
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
