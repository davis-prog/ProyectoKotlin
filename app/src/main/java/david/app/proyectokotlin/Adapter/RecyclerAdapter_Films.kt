package david.app.proyectokotlin.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Films
import david.app.proyectokotlin.R

class RecyclerAdapter_Films : RecyclerView.Adapter<RecyclerAdapter_Films.ViewHolder>() {
    var data: MutableList<Films> = ArrayList()

    fun RecyclerAdapter_Films(data: MutableList<Films>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = ViewHolder(layoutInflater.inflate(R.layout.list_item_films, parent, false))
        return v
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }

    fun getList(): MutableList<Films> {
        return this.data;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById(R.id.films_titulo) as TextView
        //        val contenido = view.findViewById(R.id.books_contenido) as TextView
        val avatar = view.findViewById(R.id.films_image) as ImageView

        fun bind(item: Films) {
            titulo.text = item.titulo
            item.photo?.let { avatar.loadUrl(it) }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}
