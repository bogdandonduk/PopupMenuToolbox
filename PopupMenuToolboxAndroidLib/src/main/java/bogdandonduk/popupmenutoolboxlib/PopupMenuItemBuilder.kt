package bogdandonduk.popupmenutoolboxlib

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.PopupWindow

class PopupMenuItemBuilder internal constructor() {
    @PublishedApi
    internal var model = PopupMenuItem(
        "Item",
    ) { _, _, _ ->

    }

    inline fun setText(modification: (oldText: CharSequence) -> CharSequence) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldTextColor: Int?) -> Int?) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setIcon(modification: (old: Drawable?) ->Drawable?) = this.apply {
        model.icon = modification.invoke(model.icon)
    }

    fun getIcon() = model.icon

    inline fun setIconTintColor(modification: (old: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    inline fun setIconContentDescription(modification: (oldDescription: String?) -> String?) = this.apply {
        model.iconContentDescription = modification.invoke(model.iconContentDescription)
    }

    fun getIconContentDescription() = model.iconContentDescription

    inline fun setDividerLineColor(modification: (old: Int?) -> Int?) = this.apply {
        model.dividerLineColor = modification.invoke(model.dividerLineColor)
    }

    fun getDividerLineColor() = model.dividerLineColor

    inline fun setIsChecked(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        model.isChecked = modification.invoke(model.isChecked)
    }

    fun getIsChecked() = model.isChecked

    inline fun setClosingGroup(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        model.closingGroup = modification.invoke(model.closingGroup)
    }

    fun getClosingGroup() = model.closingGroup

    fun setOnClickAction(onClickAction: (settingTag: String?, view: View, tooltipPopupWindow: PopupWindow) -> Unit) = this.apply {
        model.onClickAction = onClickAction
    }

    fun removeOnClickAction() = this.apply {
        model.onClickAction = { _, _, _ ->

        }
    }

    inline fun setSettingTag(modification: (oldSettingTag: String?) -> String?) = this.apply {
        model.settingTag = modification.invoke(model.settingTag)
    }

    fun getSettingTag() = model.settingTag
}