package david.app.proyectokotlin.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Desserts
import david.app.proyectokotlin.Eventos.CircleTransform
import david.app.proyectokotlin.R

class RecyclerAdapter_Second : RecyclerView.Adapter<RecyclerAdapter_Second.ViewHolder>() {
    var data: MutableList<Desserts> = ArrayList()

    fun RecyclerAdapter_Second(data: MutableList<Desserts>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val v = ViewHolder(layoutInflater.inflate(R.layout.list_item_second, parent, false))
        return v
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view!!.findViewById(R.id.second_titulo) as TextView
        val contenido = view!!.findViewById(R.id.second_contenido) as TextView
        val avatar = view!!.findViewById(R.id.second_avatar) as ImageView
        val rating = view!!.findViewById(R.id.ratingBar) as RatingBar

        fun bind(item: Desserts) {
            titulo.text = item.titulo
            contenido.text = item.contenido
            rating.rating = item.rating
            item.avatar?.let { avatar.loadUrl(it) }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).transform(CircleTransform()).into(this)
        }
    }
}