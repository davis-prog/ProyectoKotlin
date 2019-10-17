package david.app.proyectokotlin.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Breakfast
import david.app.proyectokotlin.R

class RecyclerAdapter_First : RecyclerView.Adapter<RecyclerAdapter_First.ViewHolder>() {
    var data: MutableList<Breakfast> = ArrayList()
    var tipo: String = ""
    fun RecyclerAdapter_First(data: MutableList<Breakfast>, tipo: String) {
        this.data = data
        this.tipo = tipo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var layout: Int = 0

        when (tipo) {
            "BRE" -> {
                layout = R.layout.list_item_first_foods
            }
            "CAT" -> {
                layout = R.layout.list_item_first_category
            }
            "LEARN" -> {
                layout = R.layout.list_item_first_learn
            }
        }

        val v = ViewHolder(layoutInflater.inflate(layout, parent, false))
        return v

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item, tipo)
    }

    fun getList(): MutableList<Breakfast> {
        return this.data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var view2 = view

        fun bind(item: Breakfast, tipo: String) {

            when (tipo) {
                "BRE" -> {
                    val titulo = view2!!.findViewById(R.id.first_titulo) as TextView
                    val contenido = view2!!.findViewById(R.id.first_contenido) as TextView
                    val avatar = view2!!.findViewById(R.id.first_avatar) as ImageView

                    titulo.text = item.titulo
                    contenido.text = item.contenido
                    item.avatar?.let { avatar.loadUrl(it) }
                }
                "CAT" -> {
                    val buttom = view2!!.findViewById(R.id.first_button_category) as Button
                    buttom.text = item.titulo
                }
                "LEARN" -> {
                    val titulo = view2!!.findViewById(R.id.first_titulo) as TextView
                    val avatar = view2!!.findViewById(R.id.first_avatar) as ImageView
                    val hours = view2!!.findViewById(R.id.first_hours) as TextView
                    val autor = view2!!.findViewById(R.id.first_autor) as TextView

                    item.avatar?.let { avatar.loadUrl(it) }
                }
            }

        }


        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }

    }
}