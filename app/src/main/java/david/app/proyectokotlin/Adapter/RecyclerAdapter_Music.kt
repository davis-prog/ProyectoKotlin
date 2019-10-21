package david.app.proyectokotlin.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Music
import david.app.proyectokotlin.R

class RecyclerAdapter_Music : RecyclerView.Adapter<RecyclerAdapter_Music.ViewHolder>() {
    var data: MutableList<Music> = ArrayList()

    fun RecyclerAdapter_Music(data: MutableList<Music>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = ViewHolder(layoutInflater.inflate(R.layout.list_item_music, parent, false))
        return v
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }

    fun getList(): MutableList<Music> {
        return this.data;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById(R.id.music_titulo) as TextView
        val autor = view.findViewById(R.id.music_autor) as TextView
        val contenido = view.findViewById(R.id.music_contenido) as TextView
        val avatar = view.findViewById(R.id.music_avatar) as ImageView

        fun bind(item: Music) {
            titulo.text = item.titulo
            autor.text = item.autor
            contenido.text = item.contenido
            item.avatar?.let { avatar.loadUrl(it) }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}