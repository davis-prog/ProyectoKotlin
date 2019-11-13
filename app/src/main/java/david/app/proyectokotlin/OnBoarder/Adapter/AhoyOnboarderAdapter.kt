package david.app.proyectokotlin.OnBoarder.Adapter

import android.graphics.Typeface
import android.util.Log
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import david.app.proyectokotlin.OnBoarder.Entity.AhoyOnboarderCard
import david.app.proyectokotlin.OnBoarder.Fragment.AhoyOnboarderFragment
import david.app.proyectokotlin.OnBoarder.Utils.ShadowTransformer
import kotlinx.android.synthetic.main.fragment_ahoy.*
import java.util.*

class AhoyOnboarderAdapter(
    pages: List<AhoyOnboarderCard>,
    fm: FragmentManager,
    baseElevation: Float,
    typeface: Typeface?
) : FragmentStatePagerAdapter(fm), ShadowTransformer.CardAdapter {


    private val TAG = AhoyOnboarderAdapter::class.java.simpleName
    private var pages: List<AhoyOnboarderCard> = ArrayList()
    private val mFragments = ArrayList<AhoyOnboarderFragment>()
    private var mBaseElevation: Float? = null
    private var typeface: Typeface? = null

    init {
        this.pages = pages
        this.typeface = typeface
        this.mBaseElevation = baseElevation

        for (i in 0 until pages.size) {
            addCardFragment(pages.get(i))
        }
    }

    override fun getItem(position: Int): Fragment = mFragments.get(position)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        mFragments[position] = fragment as AhoyOnboarderFragment
        return fragment
    }

    override fun getBaseElevation(): Float = mBaseElevation!!

    override fun getCardViewAt(position: Int): CardView? {
        setTypeface(typeface?.let { it }, position)

        var view = mFragments[position]


        var cardview =  view.getCardView

        return cardview
    }

    fun addCardFragment(page: AhoyOnboarderCard) {
        mFragments.add(AhoyOnboarderFragment.newInstance(page))
    }

    override fun getCount(): Int = pages.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun setTypeface(typeface: Typeface?, i: Int) {
        if (typeface != null) {
            if (mFragments.get(i) == null) {
                Log.i(TAG, "Fragment is null")
                return
            }

            if (mFragments.get(i).getTitleView == null) {
                Log.i(TAG, "TitleView is null")
                return
            }

            if (mFragments.get(i).getTitleView == null) {
                Log.i(TAG, "DescriptionView is null")
                return
            }
            mFragments[i].getTitleView.typeface = typeface
            mFragments[i].getDescriptionView.typeface = typeface
        }

    }


}