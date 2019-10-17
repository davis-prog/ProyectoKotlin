package david.app.proyectokotlin.Interfaz

import android.support.v7.widget.RecyclerView
import android.view.View

interface OnItemClickListener {
    fun onItemClicked(position:Int,view: View)
}

