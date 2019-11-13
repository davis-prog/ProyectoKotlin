package david.app.proyectokotlin.OnBoarder

import android.animation.Animator
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import david.app.proyectokotlin.OnBoarder.Adapter.AhoyOnboarderAdapter
import david.app.proyectokotlin.OnBoarder.Entity.AhoyOnboarderCard
import david.app.proyectokotlin.OnBoarder.Utils.ShadowTransformer
import david.app.proyectokotlin.OnBoarder.Views.CircleIndicatorView
import david.app.proyectokotlin.OnBoarder.Views.FlowingGradientClass
import david.app.proyectokotlin.R

abstract class OnBoarderActivity : AppCompatActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {


    //@BindView(R.id.circle_indicator_view)
    private var circleIndicatorView: CircleIndicatorView? = null
    //@BindView(R.id.vp_pager)
    private var vpOnboarderPager: ViewPager? = null
    //@BindView(R.id.parent_layout)
    private var parentLayout: RelativeLayout? = null
    //@BindView(R.id.btn_skip)
    private var btnSkip: TextView? = null
    //@BindView(R.id.ivNext)
    private var ivNext: ImageView? = null
    //@BindView(R.id.ivPrev)
    private var ivPrev: ImageView? = null
    //@BindView(R.id.navigation_layout)
    private var navigationControls: FrameLayout? = null

    //@BindView(R.id.buttons_layout)
    private var buttonsLayout: FrameLayout? = null
    //@BindView(R.id.background_image)
    private var backgroundImage: ImageView? = null
    //@BindView(R.id.background_image_overlay)
    private var backgroundImageOverlay: View? = null


    private var ahoyOnboarderAdapter: AhoyOnboarderAdapter? = null
    private lateinit var mCardShadowTransformer: ShadowTransformer
    private var typeface: Typeface? = null
    private var colorList: List<Int>? = null
    private var solidBackground = false
    private var pages: List<AhoyOnboarderCard>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarder)
        setStatusBackgroundColor();
        hideActionBar()

        //ButterKnife.bind(this)

        circleIndicatorView = findViewById(R.id.circle_indicator_view) as? CircleIndicatorView
        vpOnboarderPager = findViewById(R.id.vp_pager) as? ViewPager
        parentLayout = findViewById(R.id.parent_layout) as? RelativeLayout
        btnSkip = findViewById(R.id.btn_skip) as? TextView
        ivNext = findViewById(R.id.ivNext) as? ImageView
        ivPrev = findViewById(R.id.ivPrev) as? ImageView
        navigationControls = findViewById(R.id.navigation_layout) as? FrameLayout
        buttonsLayout = findViewById(R.id.buttons_layout) as? FrameLayout
        backgroundImage = findViewById(R.id.background_image) as? ImageView
        backgroundImageOverlay = findViewById(R.id.background_image_overlay) as? View


        vpOnboarderPager!!.addOnPageChangeListener(this)
        btnSkip!!.setOnClickListener(this)
        ivPrev!!.setOnClickListener(this)
        ivNext!!.setOnClickListener(this)

        hideFinish(false)
        fadeOut(ivPrev, false)
    }

    fun setOnboardPages(pages: List<AhoyOnboarderCard>) {
        this.pages = pages

        ahoyOnboarderAdapter =
            AhoyOnboarderAdapter(pages, supportFragmentManager, dpToPixels(0, this), typeface?.let { it })
        mCardShadowTransformer = ShadowTransformer(vpOnboarderPager!!, ahoyOnboarderAdapter!!)
        mCardShadowTransformer.enableScaling(true)

        vpOnboarderPager!!.adapter = ahoyOnboarderAdapter
        vpOnboarderPager!!.setPageTransformer(false, mCardShadowTransformer)
        circleIndicatorView.let { v ->
            if (v != null) {
                v!!.setPageIndicators(pages.size)
            }
        }
        //!!.setPageIndicators(pages.size)
    }

    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * (context.resources.displayMetrics.density)
    }

    private fun setStatusBackgroundColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = ContextCompat.getColor(this, R.color.black_transparent)
        }
    }

    private fun hideActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }


    override fun onClick(v: View?) {
        var i: Int = v!!.id
        val isInFirstPage: Boolean = vpOnboarderPager!!.getCurrentItem() === 0
        val isInLastPage: Boolean = vpOnboarderPager!!.getCurrentItem() === ahoyOnboarderAdapter!!.getCount() - 1

        if (i == R.id.btn_skip && isInLastPage) {
            onFinishButtonPressed()
        } else if (i == R.id.ivPrev && !isInFirstPage) {
            vpOnboarderPager!!.setCurrentItem(vpOnboarderPager!!.getCurrentItem() - 1)
        } else if (i == R.id.ivNext && !isInLastPage) {
            vpOnboarderPager!!.setCurrentItem(vpOnboarderPager!!.getCurrentItem() + 1)
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        val firstPagePosition = 0
        val lastPagePosition = ahoyOnboarderAdapter!!.getCount() - 1
        circleIndicatorView.let { v ->
            if (v != null) {
                v!!.setCurrentPage(position)
            }
        }
        circleIndicatorView.let { v ->
            if (v != null) {
                v!!.setCurrentPage(position)
            }
        }

        if (position == lastPagePosition) {
            fadeOut(circleIndicatorView)
            showFinish()
            fadeOut(ivNext)
            fadeIn(ivPrev)
        } else if (position == firstPagePosition) {
            fadeOut(ivPrev)
            fadeIn(ivNext)
            hideFinish()
            fadeIn(circleIndicatorView)
        } else {
            fadeIn(circleIndicatorView)
            hideFinish()
            fadeIn(ivPrev)
            fadeIn(ivNext)
        }

        if (solidBackground && pages!!.size == colorList!!.size) {
            backgroundImage!!.setBackgroundColor(ContextCompat.getColor(this, colorList!!.get(position)))
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    private fun fadeOut(v: View?) {
        fadeOut(v, true)
    }

    private fun fadeOut(v: View?, delay: Boolean) {
        var duration: Long = 0
        if (delay) {
            duration = 300
        }

        if (v != null) {
            var gone = v!!.let { v -> v.visibility }

            if (gone != View.GONE) {
                val fadeOut: Animation = AlphaAnimation(1f, 0f)
                fadeOut.interpolator = AccelerateInterpolator()
                fadeOut.duration = duration
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        v.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                v.startAnimation(fadeOut)
            }
        }
    }

    private fun fadeIn(v: View?) {
        if (v != null) {
            if (v!!.visibility != View.GONE) {
                var fadeIn: Animation = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.duration = 300
                fadeIn.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {

                        v.visibility = View.VISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                v.startAnimation(fadeIn)
            }
        }
    }

    private fun showFinish() {
        btnSkip!!.visibility = View.VISIBLE
        this.btnSkip!!.animate().translationY(0f - dpToPixels(5, this)).setInterpolator(DecelerateInterpolator())
            .setDuration(500).start()
    }

    private fun hideFinish(delay: Boolean) {
        var duration: Long = 0
        if (delay) {
            duration = 250
        }

        this.btnSkip!!.animate().translationY(this.btnSkip!!.bottom + dpToPixels(100, this))
            .setInterpolator(AccelerateInterpolator()).setDuration(duration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    btnSkip!!.setVisibility(View.VISIBLE)
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }

            }).start()
    }

    private fun hideFinish() {
        hideFinish(true)
    }


    abstract fun onFinishButtonPressed()


    fun showNavigationControls(navigation: Boolean) {
        if (navigation) {
            navigationControls!!.visibility = View.VISIBLE
        } else {
            navigationControls!!.visibility = View.GONE
        }
    }

    fun setImageBackground(resId: Int) {
        backgroundImageOverlay!!.setVisibility(View.VISIBLE)
        backgroundImage!!.setImageResource(resId)
    }

    fun setColorBackground(@ColorRes color: Int) {
        backgroundImage!!.setBackgroundColor(ContextCompat.getColor(this, color))
    }

    fun setColorBackground(color: List<Int>) {
        this.colorList = color
        solidBackground = true
        backgroundImage!!.setBackgroundColor(ContextCompat.getColor(this, color[0]))
    }

    fun setGradientBackground() {
        val grad = FlowingGradientClass()
        grad.setBackgroundResource(R.drawable.translate)
            .onRelativeLayout(parentLayout!!)
            .setTransitionDuration(4000)
            .start()
    }

    fun setGradientBackground(drawable: Int) {
        val grad = FlowingGradientClass()
        grad.setBackgroundResource(drawable)
            .onRelativeLayout(parentLayout!!)
            .setTransitionDuration(4000)
            .start()
    }

    fun setInactiveIndicatorColor(color: Int) {

        this.circleIndicatorView.let { circleIndicatorView ->
            if (circleIndicatorView != null) {
                circleIndicatorView!!.setActiveIndicatorColor(color)
            }
        }


        //!!.setInactiveIndicatorColor(color)
    }

    fun setActiveIndicatorColor(color: Int) {

        this.circleIndicatorView.let { circleIndicatorView ->
            if (circleIndicatorView != null) {
                circleIndicatorView!!.setActiveIndicatorColor(color)
            }
        }


        //!!.setActiveIndicatorColor(color)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun setFinishButtonDrawableStyle(res: Drawable) {
        btnSkip!!.setBackground(res)
    }

    fun setFinishButtonTitle(title: CharSequence) {
        btnSkip!!.setText(title)
    }

    fun setFinishButtonTitle(@StringRes titleResId: Int) {
        btnSkip!!.setText(titleResId)
    }

    fun setFont(typeface: Typeface) {
        this.btnSkip!!.setTypeface(typeface)
        this.typeface = typeface
    }

}
