package david.app.proyectokotlin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import david.app.proyectokotlin.Views.DetailsActivity
import java.io.Serializable
import java.lang.Exception
//import android.util.Pair as UtilPair
import android.support.v4.util.Pair as AndroidPair
import android.view.inputmethod.InputMethodManager
import android.support.v7.widget.SearchView
import david.app.proyectokotlin.Adapter.RecyclerAdapter
import david.app.proyectokotlin.Entity.DogsResponse
import david.app.proyectokotlin.Entity.Superhero
import david.app.proyectokotlin.Interfaz.APIService
import david.app.proyectokotlin.Views.Fragments.NavigationView.GalleryFragment
import david.app.proyectokotlin.Views.Fragments.NavigationView.HomeFragment
import david.app.proyectokotlin.Views.Fragments.NavigationView.SlideshowFragment
import david.app.proyectokotlin.Views.Fragments.NavigationView.ToolsFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SearchView.OnQueryTextListener {

    internal var TAG = MainActivity::class.java.simpleName
    lateinit var context: Context

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: RecyclerAdapter = RecyclerAdapter()

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    var navigationPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar_appBar) as Toolbar
        setSupportActionBar(toolbar)
        context = this

        //SearchView
        /*   searchBreed.setIconifiedByDefault(false)
           searchBreed.queryHint = "Buscar..."
           searchBreed.setOnQueryTextListener(this)*/


        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        navigationView = findViewById(R.id.nav_view) as NavigationView
        setUpDrawerLayout()

        navigationPosition = R.id.nav_home
        navigateToFragment(HomeFragment.newInstance())
        navigationView.setCheckedItem(navigationPosition)
        toolbar.title = getString(R.string.TitleHome)

        navigationView.setNavigationItemSelectedListener(this)

        changeNavigationHeaderInfo()

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)

        if (navigationPosition == R.id.nav_home) {
            finish()
        } else {
            navigationPosition = R.id.nav_home
            navigateToFragment(HomeFragment.newInstance())
            navigationView.setCheckedItem(navigationPosition)
            toolbar.title = getString(R.string.TitleHome)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu!!.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(this)
        }
        /*  try {
              val searchView =  searchItem.actionView as SearchView
              searchView.setIconifiedByDefault(false)
              searchView.queryHint = "Buscar..."

          }catch (e:Exception){
              Log.e(TAG, e.message)
          }*/


        //searchItem.expandActionView()
/*        searchView.doOnLayout{
            searchView.clearFocus()
        }*/

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        supportFinishAfterTransition()

        /* return when (item.itemId) {
             R.id.action_settings -> true
             else -> super.onOptionsItemSelected(item)
         }*/

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title = ""
        //searchBreed.visibility = View.GONE
        when (item.itemId) {
            R.id.nav_home -> {
                //searchBreed.visibility = View.VISIBLE
                title = getString(R.string.TitleHome)
                navigationPosition = R.id.nav_home
                navigateToFragment(HomeFragment.newInstance())
            }
            R.id.nav_gallery -> {
                title = getString(R.string.TitleGallery)
                navigationPosition = R.id.nav_gallery
                navigateToFragment(GalleryFragment.newInstance())
            }
            R.id.nav_slideshow -> {
                title =  getString(R.string.TitleSlideshow)
                navigationPosition = R.id.nav_slideshow
                navigateToFragment(SlideshowFragment.newInstance())
            }
            R.id.nav_tools -> {
                title = "Tools"
                navigationPosition = R.id.nav_tools
                navigateToFragment(ToolsFragment.newInstance())
            }
            R.id.nav_share -> {
                title = "share"
            }
            R.id.nav_send -> {
                title = "send"
            }
        }

        item.isChecked = true
        toolbar.title = title
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun changeNavigationHeaderInfo() {
        val headerView = navigationView.getHeaderView(0)
        headerView.textEmail.text = "╠androida┬┼┘_♠_david@proyectoKotlin.com"
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    // RecyclerView
    fun setUpRecyclerView() {
        //mRecyclerView = findViewById(R.id.RecyclerView) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getSuperheroes(), this)
        mRecyclerView.adapter = mAdapter


        mRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val item = mAdapter.getList().get(position)
                toast(item.superhero!!)
                //Toast.makeText(context, item.superhero, Toast.LENGTH_SHORT).show()

                val i = Intent(context, DetailsActivity::class.java)
                i.putExtra("Objeto", item as Serializable)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    val Avatar = view.findViewById(R.id.ivAvatar) as ImageView
                    val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
                    val realName = view.findViewById(R.id.tvRealName) as TextView
                    val publisher = view.findViewById(R.id.tvPublisher) as TextView

                    val viewAvatar = AndroidPair<View, String>(Avatar, Avatar.transitionName)
                    val viewSuperheroName = AndroidPair<View, String>(superheroName, superheroName.transitionName)
                    val viewRealName = AndroidPair<View, String>(realName, realName.transitionName)
                    val viewPublisher = AndroidPair<View, String>(publisher, publisher.transitionName)


                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@MainActivity,
                        viewAvatar, viewSuperheroName, viewRealName, viewPublisher
                    );

                    startActivity(i, options.toBundle())

                } else startActivity(i)
            }
        })

    }

    fun getSuperheroes(): MutableList<Superhero> {
        var superheros: MutableList<Superhero> = ArrayList()
        superheros.add(
            Superhero(
                1,
                "Spiderman",
                "Marvel",
                "Peter Parker",
                "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"
            )
        )
        superheros.add(
            Superhero(
                2,
                "Daredevil",
                "Marvel",
                "Matthew Michael Murdock",
                "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
            )
        )
        superheros.add(
            Superhero(
                3,
                "Wolverine",
                "Marvel",
                "James Howlett",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            )
        )
        superheros.add(
            Superhero(
                4,
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            )
        )
        superheros.add(
            Superhero(
                5,
                "Thor",
                "Marvel",
                "Thor Odinson",
                "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"
            )
        )
        superheros.add(
            Superhero(
                6,
                "Flash",
                "DC",
                "Jay Garrick",
                "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"
            )
        )
        superheros.add(
            Superhero(
                7,
                "Green Lantern",
                "DC",
                "Alan Scott",
                "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"
            )
        )
        superheros.add(
            Superhero(
                8,
                "Wonder Woman",
                "DC",
                "Princess Diana",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
        return superheros
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        }

        )
    }


    // funciones del SearchView
    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        try {
            searchByName(query.toLowerCase())
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }
        return true
    }


    // funcion para llamar al API rest
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        doAsync {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as DogsResponse
            uiThread {
                if (puppies.status == "success") {
                    initCharacter(puppies)
                } else {
                    showErrorDialog()
                }
                hideKeyboard()
            }
        }
    }

    private fun initCharacter(puppies: DogsResponse) {
        lateinit var imagesPuppies: List<String>
        imagesPuppies = puppies.images
        /*if(puppies.status == "success") {
            imagesPuppies = puppies.images
        }*/

        var i = imagesPuppies.count() + 1
        imagesPuppies.forEach {
            //println("The element is $it")
            var item = Superhero(i, "Prueba", "Prueba", "Prueba", "$it")
            mAdapter.addItemList(item)
            i++
        }
        //dogsAdapter = DogsAdapter(imagesPuppies)
        //rvDogs.setHasFixedSize(true)
        //rvDogs.layoutManager = LinearLayoutManager(this)
        //rvDogs.adapter = dogsAdapter
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtalo de nuevo.") {
            yesButton { }
        }.show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //imm.hideSoftInputFromWindow(viewRoot.windowToken,0)
    }


}
