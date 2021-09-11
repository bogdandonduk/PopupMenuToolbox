package bogdandonduk.popupmenutoolboxlib

import android.view.View
import android.widget.PopupWindow

internal class PopupMenu(
    contentView: View?,
    width: Int,
    height: Int,
    var key: String
) : PopupWindow(contentView, width, height) {
    val defaultAnimationStyle = animationStyle

    var fakeDismissal = false

    private fun attachToModel() {
        PopupMenuToolbox.getSavedPopupMenuModel(key)!!.popup = this
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)

        attachToModel()
    }

    fun show(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        showAsDropDown(anchor, xoff, yoff, gravity)

        PopupMenuToolbox.getSavedPopupMenuModel(key)!!.onShowAction?.invoke(contentView.rootView, this)
    }
}