package david.app.proyectokotlin.OnBoarder.Utils

import android.view.View
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager

class ShadowTransformer : ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private val mViewPager: ViewPager
    private val mAdapter: CardAdapter
    private var mLastOffset: Float = 0.toFloat()
    private var mScalingEnabled: Boolean = false
    val MAX_ELEVATION_FACTOR: Int = 6

    interface CardAdapter {

        fun getBaseElevation(): Float
        fun getCardViewAt(position: Int): CardView?
        fun getCount(): Int
    }

    constructor(viewPager: ViewPager, adapter: CardAdapter) {
        this.mViewPager = viewPager

        viewPager.addOnPageChangeListener(this)
        this.mAdapter = adapter
    }

    fun enableScaling(enable: Boolean) {
        if (mScalingEnabled && !enable) {
            var currentCard: CardView? = mAdapter.getCardViewAt(mViewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1f)
                currentCard.animate().scaleX(1f)
            }
        } else if (!mScalingEnabled && enable) {
            val currentCard: CardView? = mAdapter.getCardViewAt(mViewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f)
                currentCard.animate().scaleX(1.1f)
            }

        }
    }

    override fun transformPage(page: View, position: Float) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val realCurrentPosition: Int
        val nextPosition: Int
        val baseElevation = mAdapter.getBaseElevation()
        val realOffset: Float
        val goingLeft = mLastOffset > positionOffset

        if (goingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        if (nextPosition > mAdapter.getCount() - 1 || realCurrentPosition > mAdapter.getCount() - 1) {
            return
        }

        val currentCard = mAdapter.getCardViewAt(realCurrentPosition)
        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
            }
            currentCard.cardElevation =
                baseElevation + (baseElevation * (MAX_ELEVATION_FACTOR - 1).toFloat() * (1 - realOffset))
        }


        val nextCard = mAdapter.getCardViewAt(nextPosition)

        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.setScaleX((1 + 0.1 * realOffset).toFloat())
                nextCard.setScaleY((1 + 0.1 * realOffset).toFloat())
            }
            nextCard.setCardElevation(
                baseElevation + (baseElevation
                        * (MAX_ELEVATION_FACTOR - 1).toFloat() * realOffset)
            )
        }

        mLastOffset = positionOffset
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageSelected(position: Int) {

    }


}