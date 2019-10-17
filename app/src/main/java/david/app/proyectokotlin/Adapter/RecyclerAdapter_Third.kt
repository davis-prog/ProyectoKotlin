package david.app.proyectokotlin.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Desserts
import david.app.proyectokotlin.R

class RecyclerAdapter_Third : RecyclerView.Adapter<RecyclerAdapter_Third.ViewHolder>() {
    var data: MutableList<Desserts> = ArrayList()

    fun RecyclerAdapter_Third(data: MutableList<Desserts>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val v = ViewHolder(layoutInflater.inflate(R.layout.list_item_third, parent, false))
        return v
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data.get(position)
        holder.bind(item)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view!!.findViewById(R.id.third_titulo) as TextView
        val contenido = view!!.findViewById(R.id.third_contenido) as TextView
        var avatar = view!!.findViewById(R.id.third_avatar) as ImageView


        fun bind(item: Desserts){
            titulo.text = item.titulo
            contenido.text = item.contenido
            item.avatar?.let { avatar.loadUrl(it) }
        }

        fun ImageView.loadUrl(url: String){
            Picasso.get().load(url).into(this)
        }
    }
}