package bogdandonduk.popupmenutoolboxlib

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.ColorInt

class PopupMenuItem(
    var text: CharSequence = "Item",
    @ColorInt var textColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,
    var iconContentDescription: String? = null,

    @ColorInt var dividerLineColor: Int? = null,

    var isChecked: Boolean = false,
    var closingGroup: Boolean = false,

    var settingTag: String? = null,

    var onClickAction: (settingTag: String?, view: View, tooltipPopupWindow: PopupWindow) -> Unit
)