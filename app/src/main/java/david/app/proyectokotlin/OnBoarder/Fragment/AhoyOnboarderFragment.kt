package david.app.proyectokotlin.OnBoarder.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import david.app.proyectokotlin.OnBoarder.Entity.AhoyOnboarderCard
import david.app.proyectokotlin.R

private val AHOY_PAGE_TITLE = "ahoy_page_title"
private val AHOY_PAGE_TITLE_RES_ID = "ahoy_page_title_res_id"
private val AHOY_PAGE_TITLE_COLOR = "ahoy_page_title_color"
private val AHOY_PAGE_TITLE_TEXT_SIZE = "ahoy_page_title_text_size"
private val AHOY_PAGE_DESCRIPTION = "ahoy_page_description"
private val AHOY_PAGE_DESCRIPTION_RES_ID = "ahoy_page_description_res_id"
private val AHOY_PAGE_DESCRIPTION_COLOR = "ahoy_page_description_color"
private val AHOY_PAGE_DESCRIPTION_TEXT_SIZE = "ahoy_page_description_text_size"
private val AHOY_PAGE_IMAGE_RES_ID = "ahoy_page_image_res_id"
private val AHOY_PAGE_BACKGROUND_COLOR = "ahoy_page_background_color"
private val AHOY_PAGE_ICON_WIDTH = "ahoy_page_icon_width"
private val AHOY_PAGE_ICON_HEIGHT = "ahoy_page_icon_height"
private val AHOY_PAGE_MARGIN_LEFT = "ahoy_page_margin_left"
private val AHOY_PAGE_MARGIN_RIGHT = "ahoy_page_margin_right"
private val AHOY_PAGE_MARGIN_TOP = "ahoy_page_margin_top"
private val AHOY_PAGE_MARGIN_BOTTOM = "ahoy_page_margin_bottom"

class AhoyOnboarderFragment : Fragment() {

    private var title: String? = null
    private var description: String? = null
    @StringRes
    private var titleResId: Int = 0
    @ColorRes
    private var titleColor: Int = 0
    @StringRes
    private var descriptionResId: Int = 0
    @ColorRes
    private var backgroundColor: Int = 0
    @ColorRes
    private var descriptionColor: Int = 0
    @DrawableRes
    private var imageResId: Int = 0
    private var titleTextSize: Float = 0.toFloat()
    private var descriptionTextSize: Float = 0.toFloat()

    // private var view: View? = null

    private var iconHeight: Int = 0
    private var iconWidth: Int = 0
    private var marginTop: Int = 0
    private var marginBottom: Int = 0
    private var marginLeft: Int = 0
    private var marginRight: Int = 0

    /* @BindView(R.id.iv_image)
     private lateinit var ivOnboarderImage: ImageView
     @BindView(R.id.tv_title)
     private lateinit var tvOnboarderTitle: TextView
     @BindView(R.id.tv_description)
     private lateinit var tvOnboarderDescription: TextView
     @BindView(R.id.cv_cardview)
     var cardView: CardView? = null*/

    private lateinit var ivOnboarderImage: ImageView
    private lateinit var tvOnboarderTitle: TextView
    private lateinit var tvOnboarderDescription: TextView
    private var cardView: CardView? = null

    companion object {
        fun newInstance(card: AhoyOnboarderCard) = AhoyOnboarderFragment().apply {
            val args = Bundle()
            args.putString(AHOY_PAGE_TITLE, card.title)
            args.putString(AHOY_PAGE_DESCRIPTION, card.description)
            args.putInt(AHOY_PAGE_TITLE_RES_ID, card.titleResourceId)
            args.putInt(AHOY_PAGE_DESCRIPTION_RES_ID, card.descriptionResourceId)
            args.putInt(AHOY_PAGE_TITLE_COLOR, card.titleColor)
            args.putInt(AHOY_PAGE_DESCRIPTION_COLOR, card.descriptionColor)
            args.putInt(AHOY_PAGE_IMAGE_RES_ID, card.imageResourceId)
            args.putFloat(AHOY_PAGE_TITLE_TEXT_SIZE, card.titleTextSize)
            args.putFloat(AHOY_PAGE_DESCRIPTION_TEXT_SIZE, card.descriptionTextSize)
            args.putInt(AHOY_PAGE_BACKGROUND_COLOR, card.backgroundColor)
            args.putInt(AHOY_PAGE_ICON_HEIGHT, card.iconHeight)
            args.putInt(AHOY_PAGE_ICON_WIDTH, card.iconWidth)
            args.putInt(AHOY_PAGE_MARGIN_LEFT, card.marginLeft)
            args.putInt(AHOY_PAGE_MARGIN_RIGHT, card.marginRight)
            args.putInt(AHOY_PAGE_MARGIN_TOP, card.marginTop)
            args.putInt(AHOY_PAGE_MARGIN_BOTTOM, card.marginBottom)

            val fragment = AhoyOnboarderFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments!!

        title = bundle.getString(AHOY_PAGE_TITLE, null)
        titleResId = bundle.getInt(AHOY_PAGE_TITLE_RES_ID, 0)
        titleColor = bundle.getInt(AHOY_PAGE_TITLE_COLOR, 0)
        titleTextSize = bundle.getFloat(AHOY_PAGE_TITLE_TEXT_SIZE, 0f)
        description = bundle.getString(AHOY_PAGE_DESCRIPTION, null)
        descriptionResId = bundle.getInt(AHOY_PAGE_DESCRIPTION_RES_ID, 0)
        descriptionColor = bundle.getInt(AHOY_PAGE_DESCRIPTION_COLOR, 0)
        descriptionTextSize = bundle.getFloat(AHOY_PAGE_DESCRIPTION_TEXT_SIZE, 0f)
        imageResId = bundle.getInt(AHOY_PAGE_IMAGE_RES_ID, 0)
        backgroundColor = bundle.getInt(AHOY_PAGE_BACKGROUND_COLOR, 0)
        iconWidth = bundle.getInt(AHOY_PAGE_ICON_WIDTH, dpToPixels(128, activity!!).toInt())
        iconHeight = bundle.getInt(AHOY_PAGE_ICON_HEIGHT, dpToPixels(128, activity!!).toInt())
        marginTop = bundle.getInt(AHOY_PAGE_MARGIN_TOP, dpToPixels(80, activity!!).toInt())
        marginBottom = bundle.getInt(AHOY_PAGE_MARGIN_BOTTOM, dpToPixels(0, activity!!).toInt())
        marginLeft = bundle.getInt(AHOY_PAGE_MARGIN_LEFT, dpToPixels(0, activity!!).toInt())
        marginRight = bundle.getInt(AHOY_PAGE_MARGIN_RIGHT, dpToPixels(0, activity!!).toInt())

        var view = inflater.inflate(R.layout.fragment_ahoy, container, false)
        ivOnboarderImage = view!!.findViewById(R.id.iv_image) as ImageView
        tvOnboarderTitle = view!!.findViewById(R.id.tv_title) as TextView
        tvOnboarderDescription = view!!.findViewById(R.id.tv_description) as TextView
        cardView = view.findViewById(R.id.cv_cardview) as CardView
        //ButterKnife.bind(view)


        if (title != null) {
            tvOnboarderTitle!!.text = title
        }

        if (titleResId != 0) {
            tvOnboarderTitle!!.text = resources.getString(titleResId)
        }

        if (description != null) {
            tvOnboarderDescription!!.text = description
        }

        if (descriptionResId != 0) {
            tvOnboarderDescription!!.text = resources.getString(descriptionResId)
        }

        if (titleColor != 0) {
            tvOnboarderTitle!!.setTextColor(ContextCompat.getColor(activity!!, titleColor))
        }

        if (descriptionColor != 0) {
            tvOnboarderDescription!!.setTextColor(ContextCompat.getColor(activity!!, descriptionColor))
        }

        if (imageResId != 0) {
            ivOnboarderImage!!.setImageDrawable(ContextCompat.getDrawable(activity!!, imageResId))
        }

        if (titleTextSize != 0f) {
            tvOnboarderTitle!!.setTextSize(titleTextSize)
        }

        if (descriptionTextSize != 0f) {
            tvOnboarderDescription!!.setTextSize(descriptionTextSize)
        }

        if (backgroundColor != 0) {
            cardView!!.setCardBackgroundColor(ContextCompat.getColor(activity!!, backgroundColor))
        }

        if (iconWidth != 0 && iconHeight != 0) {
            val layoutParams = FrameLayout.LayoutParams(iconWidth, iconHeight)
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL
            layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom)
            ivOnboarderImage!!.setLayoutParams(layoutParams)
        }

        return view
    }


    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * context.resources.displayMetrics.density
    }

    override fun onDetach() {
        super.onDetach()
    }


//
//    fun getCardView(): CardView? {
//        return  cardView?.let { it }
//    }

//    fun getTitleView(): TextView? {
//        return tvOnboarderTitle!!
//    }
//
//    fun getDescriptionView(): TextView? {
//        return tvOnboarderDescription!!
//    }


    val getCardView: CardView?
        get() {

            return cardView
        }

    val getTitleView: TextView
        get() {
            return tvOnboarderTitle
        }

    val getDescriptionView: TextView
        get() {
            return tvOnboarderDescription
        }


}