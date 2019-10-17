package david.app.proyectokotlin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Superhero
import david.app.proyectokotlin.R


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var superheros: MutableList<Superhero> = ArrayList()
    var copyList: MutableList<Superhero> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(superHero: MutableList<Superhero>, context: Context) {
        //superHero.sortByDescending({ it.orden})
        this.superheros = superHero
        this.context = context
        this.copyList = superHero
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = superheros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)
    }

    fun addItemList(item: Superhero) {
        this.superheros.add(item)
        notifyDataSetChanged()
    }

    fun getList(): MutableList<Superhero> {
        return this.superheros;
    }

    fun filter(queryString: String) {
        superheros.clear()
        if (queryString.isEmpty()) {
            superheros.addAll(copyList)
        } else {
            for (item in copyList) {
                if (item.realName!!.toLowerCase().contains(queryString.toLowerCase())) {
                    superheros.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        val realName = view.findViewById(R.id.tvRealName) as TextView
        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val avatar = view.findViewById(R.id.ivAvatar) as ImageView

        fun bind(superhero: Superhero, context: Context) {
            superheroName.text = superhero.superhero
            realName.text = superhero.realName
            publisher.text = superhero.publisher
            superhero.photo?.let { avatar.loadUrl(it) }

        }

        fun ImageView.loadUrl(url: String) {
            //Picasso.with(context).load(url).into(this)
            Picasso.get().load(url).into(this)
        }

    }

}