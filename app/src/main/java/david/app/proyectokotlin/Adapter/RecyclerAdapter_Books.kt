package david.app.proyectokotlin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Books
import david.app.proyectokotlin.R

class RecyclerAdapter_Books : RecyclerView.Adapter<RecyclerAdapter_Books.ViewHolder>() {

    var data: MutableList<Books> = ArrayList()
    lateinit var context: Context


    fun RecyclerAdapter_Books(data : MutableList<Books>){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = ViewHolder(layoutInflater.inflate(R.layout.list_item_books, parent, false))
        return v
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item)
    }

    fun getList(): MutableList<Books> {
        return this.data;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById(R.id.books_titulo) as TextView
        val contenido = view.findViewById(R.id.books_contenido) as TextView
        val avatar = view.findViewById(R.id.books_image) as ImageView

        fun bind(item: Books){
            titulo.text = item.titulo
            contenido.text = item.contenido
            item.photo?.let { avatar.loadUrl(it) }
        }

        fun ImageView.loadUrl(url: String){
            Picasso.get().load(url).into(this)
        }
    }

}