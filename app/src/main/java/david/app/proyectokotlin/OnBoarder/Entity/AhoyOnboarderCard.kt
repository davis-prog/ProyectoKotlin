package david.app.proyectokotlin.OnBoarder.Entity

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


class AhoyOnboarderCard {

    lateinit var title: String
    lateinit var description: String
    lateinit var imageResource: Drawable
    @StringRes
    var titleResourceId: Int = 0
    @StringRes
    var descriptionResourceId: Int = 0
    @DrawableRes
    var imageResourceId: Int = 0
    @ColorRes
    var titleColor: Int = 0
    @ColorRes
    var descriptionColor: Int = 0
    @ColorRes
    var backgroundColor: Int = 0

    var titleTextSize: Float = 0.toFloat()
    var descriptionTextSize: Float = 0.toFloat()
    var iconWidth: Int = 0
    var iconHeight: Int = 0
    var marginTop: Int = 0
    var marginLeft: Int = 0
    var marginRight: Int = 0
    var marginBottom: Int = 0

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

    constructor(title: Int, description: Int) {
        this.titleResourceId = title
        this.descriptionResourceId = description
    }

    constructor(title: String, description: String, imageResourceId: Int) {
        this.title = title
        this.description = description
        this.imageResourceId = imageResourceId
    }

    constructor(title: String, description: String, imageResource: Drawable) {
        this.title = title
        this.description = description
        this.imageResource = imageResource
    }

    constructor(title: Int, description: Int, imageResourceId: Int) {
        this.titleResourceId = title
        this.descriptionResourceId = description
        this.imageResourceId = imageResourceId
    }

    constructor(title: Int, description: Int, imageResource: Drawable) {
        this.titleResourceId = title
        this.descriptionResourceId = description
        this.imageResource = imageResource
    }

    fun setIconLayoutParams(
        iconWidth: Int,
        iconHeight: Int,
        marginTop: Int,
        marginLeft: Int,
        marginRight: Int,
        marginBottom: Int
    ) {
        this.iconWidth = iconWidth
        this.iconHeight = iconHeight
        this.marginLeft = marginLeft
        this.marginRight = marginRight
        this.marginTop = marginTop
        this.marginBottom = marginBottom
    }
}
