package david.app.proyectokotlin.Interfaz

import androidx.recyclerview.widget.RecyclerView
import android.view.View

interface OnItemClickListener {
    fun onItemClicked(position:Int,view: View)
}

