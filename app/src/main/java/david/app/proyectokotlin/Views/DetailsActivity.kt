package david.app.proyectokotlin.Views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import david.app.proyectokotlin.Entity.Books
import david.app.proyectokotlin.Entity.Films
import david.app.proyectokotlin.Entity.Music
import david.app.proyectokotlin.R
import david.app.proyectokotlin.Entity.Superhero
import org.jetbrains.anko.toast

class DetailsActivity : AppCompatActivity() {

    var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    var fab: FloatingActionButton? = null
    lateinit var toolbar: Toolbar
    lateinit var realName: TextView
    lateinit var publisher: TextView
    lateinit var avatar: ImageView

    var Objeto: String = "Objeto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        toolbar = findViewById(R.id.toolbar_Details) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        fab = findViewById(R.id.fab) as FloatingActionButton

        toolbarTextAppearance()

        realName = findViewById(R.id.tvRealNameDetails) as TextView
        publisher = findViewById(R.id.tvPublisherDetails) as TextView
        avatar = findViewById(R.id.avatarDetails) as ImageView

        val item = intent.extras.get("Tipo") as String

        when (item) {
            "Home" -> {
                ControlesHome()
            }
            "Films" -> {
                ControlesFilms()
            }
            "Books" -> {
                ControlesBooks()
            }
            "Music" -> {
                ControlesMusic()
            }
        }

        fab!!.setOnClickListener({ v -> toast("Hello") })

    }

    private fun ControlesHome() {
        val item = intent.extras.get(Objeto) as Superhero
        item.photo?.let { avatar.loadUrl(it) }
        collapsingToolbarLayout!!.title = item.superhero
        realName.text = item.realName
        publisher.text = item.publisher

    }

    private fun ControlesFilms() {
        val item = intent.extras.get(Objeto) as Films
        item.photo?.let { avatar.loadUrl(it) }
        collapsingToolbarLayout!!.title = item.titulo
        realName.text = ""//item.realName
        publisher.text = ""//item.publisher
    }

    private fun ControlesBooks() {
        val item = intent.extras.get(Objeto) as Books
        item.photo?.let { avatar.loadUrl(it) }
        collapsingToolbarLayout!!.title = item.titulo
        realName.text = item.contenido
        publisher.text = ""//item.publisher
    }

    private fun ControlesMusic() {
        val item = intent.extras.get(Objeto) as Music
        item.avatar?.let { avatar.loadUrl(it) }
        collapsingToolbarLayout!!.title = item.titulo
        realName.text = item.contenido
        publisher.text = item.autor

    }

    private fun toolbarTextAppearance() {
        collapsingToolbarLayout?.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapsingToolbarLayout?.setExpandedTitleTextAppearance(R.style.expandedappbar)
    }

    fun ImageView.loadUrl(url: String) {
        //Picasso.with(context).load(url).into(this)
        Picasso.get().load(url).into(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
