package david.app.proyectokotlin.OnBoarder.Views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import david.app.proyectokotlin.R

class CircleIndicatorView : View {

   // lateinit var context: Context? = null

    private var activeIndicatorPaint: Paint? = null
    private var inactiveIndicatorPaint: Paint? = null
    private var radius: Int = 0
    private var size: Int = 0
    private var position: Int = 0
    private var indicatorsCount: Int = 0

    /*  fun CircleIndicatorView(context: Context) {
          super(context)
          init(context)
      }
      fun CircleIndicatorView(context: Context, attrs: AttributeSet, defStyle: Int) {
          super(context, attrs, defStyle)
          init(context)
      }
      */
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }


    private fun init(context: Context) {
       // this.context = context
        activeIndicatorPaint = Paint()
        activeIndicatorPaint!!.color = ContextCompat.getColor(context, R.color.active_indicator)
        activeIndicatorPaint!!.isAntiAlias = true
        inactiveIndicatorPaint = Paint()
        inactiveIndicatorPaint!!.color = ContextCompat.getColor(context, R.color.inactive_indicator)
        inactiveIndicatorPaint!!.isAntiAlias = true
        radius = getResources().getDimensionPixelSize(R.dimen.indicator_size);
        size = radius * 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0 until indicatorsCount) {
            canvas!!.drawCircle(
                (radius + size * i).toFloat(),
                radius.toFloat(),
                (radius / 2).toFloat(),
                inactiveIndicatorPaint
            )
        }

        canvas!!.drawCircle(
            (radius + (size * position)).toFloat(),
            radius.toFloat(),
            (radius / 2).toFloat(),
            activeIndicatorPaint
        )
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    fun setCurrentPage(position: Int) {
        this.position = position
        invalidate()
    }

    fun setPageIndicators(size: Int) {
        this.indicatorsCount = size
        invalidate()
    }

    private fun measureWidth(measureSpec: Int): Int {
        var result: Int = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = size * indicatorsCount
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    private fun measureHeight(measureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = 2 * radius + paddingTop + paddingBottom
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    fun setInactiveIndicatorColor(@ColorRes color: Int) {
        inactiveIndicatorPaint!!.color = ContextCompat.getColor(context, color)
        invalidate()
    }


    fun setActiveIndicatorColor(@ColorRes color: Int) {
        activeIndicatorPaint!!.color = ContextCompat.getColor(context, color)
        invalidate()
    }

}