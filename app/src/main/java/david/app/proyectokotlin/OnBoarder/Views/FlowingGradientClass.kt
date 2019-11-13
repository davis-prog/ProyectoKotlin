package david.app.proyectokotlin.OnBoarder.Views

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

class FlowingGradientClass {
    internal var Duration = 4000
    internal var rl: RelativeLayout? = null
    internal var ll: LinearLayout? = null
    internal var im: ImageView? = null
    internal var alphaint: Int = 0
    internal var d: Int = 0
    internal lateinit var frameAnimation: AnimationDrawable


    fun setTransitionDuration(time: Int): FlowingGradientClass {
        this.Duration = time

        return this
    }

    fun onLinearLayout(ll: LinearLayout): FlowingGradientClass {
        this.ll = ll
        return this
    }

    fun onImageView(im: ImageView): FlowingGradientClass {
        this.im = im
        return this
    }

    fun onRelativeLayout(rl: RelativeLayout): FlowingGradientClass {
        this.rl = rl
        return this
    }

    fun start(): FlowingGradientClass {

        if (ll != null) {
            ll!!.setBackgroundResource(d)
        } else if (rl != null) {
            rl!!.setBackgroundResource(d)
        } else if (im != null) {
            im!!.setBackgroundResource(d)
        }
        if (ll != null) {
            frameAnimation = ll!!.getBackground() as AnimationDrawable
        } else if (rl != null) {
            frameAnimation = rl!!.getBackground() as AnimationDrawable
        } else if (im != null) {
            frameAnimation = im!!.getBackground() as AnimationDrawable
        }
        frameAnimation.setEnterFadeDuration(Duration)
        frameAnimation.setExitFadeDuration(Duration)
        frameAnimation.start()

        return this
    }

    fun setBackgroundResource(d: Int): FlowingGradientClass {
        this.d = d
        return this
    }

    fun setAlpha(alphaint: Int): FlowingGradientClass {
        this.alphaint = alphaint
        frameAnimation.alpha = alphaint
        return this
    }
}