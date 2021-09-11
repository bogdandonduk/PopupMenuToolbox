package bogdandonduk.popupmenutoolboxlib

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.StyleRes

@PublishedApi
internal class PopupMenuModel(
    var key: String,

    var title: String?,
    @ColorInt var titleTextColor: Int?,
    var titleIcon: Drawable?,
    @ColorInt var titleIconTintColor: Int?,
    var titleIconContentDescription: String?,

    @ColorInt var genericTextColor: Int,
    @ColorInt var genericIconTintColor: Int?,
    @ColorInt var genericDividerLinesColor: Int?,

    var menuItems: MutableList<PopupMenuItem>,

    var onShowAction: ((view: View, popupMenuWindow: PopupWindow) -> Unit)?,
    var onDismissAction: ((popupMenuWindow: PopupWindow) -> Unit)?,

    var alpha: Float,

    @ColorInt var backgroundColor: Int,

    var strokeWidth: Int,
    @ColorInt var strokeColor: Int,

    var cornerRadiusTopLeftPx: Int,
    var cornerRadiusTopRightPx: Int,
    var cornerRadiusBottomRightPx: Int,
    var cornerRadiusBottomLeftPx: Int,
) {
    var popup: PopupMenu? = null

    @IdRes
    var anchorViewId: Int? = null
    var gravity: Int = Gravity.NO_GRAVITY
    var xOff: Int = 0
    var yOff: Int = 10

    @StyleRes
    var customAnimationStyle: Int? = null

    var listState: Parcelable? = null

    var displayed = false

    var saveModel = true
}