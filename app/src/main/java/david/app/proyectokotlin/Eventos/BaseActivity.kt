package david.app.proyectokotlin.Eventos

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import david.app.proyectokotlin.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class BaseActivity : AppCompatActivity(){

    lateinit var toolbar: Toolbar

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun setupToolbar(
        toolbarId: Int,
        title: String, @ColorRes titleColor: Int, @ColorRes colorBg: Int, @DrawableRes burger: Int
    ) {
        toolbar = findViewById<View>(toolbarId) as Toolbar
        toolbar.setBackgroundColor(resources.getColor(colorBg))
        setSupportActionBar(toolbar)
     /*   val pageTitle = toolbar.findViewById(R.id.tv_title) as TextView
        pageTitle.text = title
        pageTitle.setTextColor(resources.getColor(titleColor))
        supportActionBar!!.setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(burger)*/
    }

    fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}