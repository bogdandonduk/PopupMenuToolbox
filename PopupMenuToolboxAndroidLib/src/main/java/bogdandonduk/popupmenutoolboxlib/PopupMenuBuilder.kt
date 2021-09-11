package bogdandonduk.popupmenutoolboxlib

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import bogdandonduk.popupmenutoolboxlib.databinding.LayoutPopupMenuBinding
import top.defaults.drawabletoolbox.DrawableBuilder

class PopupMenuBuilder internal constructor(key: String) {
    internal companion object {
        const val LOG_MESSAGE_POPUP_MENU_ALREADY_SHOWING = "Old PopupMenu with this tag is already showing and new one has no effect because flag DisplayMode.CONTINUE_OLD_IF_SHOWING is set"
    }

    @PublishedApi
    internal var model =
        PopupMenuToolbox.getSavedPopupMenuModel(key).run {
            if(this != null)
                PopupMenuModel(
                    key,
                    title,
                    titleTextColor,
                    titleIcon,
                    titleIconTintColor,
                    titleIconContentDescription,
                    genericTextColor,
                    genericIconTintColor,
                    genericDividerLinesColor,
                    menuItems,
                    onShowAction,
                    onDismissAction,
                    alpha,
                    backgroundColor,
                    strokeWidth,
                    strokeColor,
                    cornerRadiusTopLeftPx,
                    cornerRadiusTopRightPx,
                    cornerRadiusBottomRightPx,
                    cornerRadiusBottomLeftPx,
                ).also {
                    it.anchorViewId = anchorViewId
                    it.gravity = gravity
                    it.xOff = xOff
                    it.yOff = yOff

                    it.customAnimationStyle = customAnimationStyle

                    it.listState = listState

                    it.displayed = displayed

                    it.saveModel = saveModel
                }
            else {
                PopupMenuConfig.RandomKeyGenerationUtils.transientKeyRegistry.add(key)

                PopupMenuModel(
                    key = key,
                    title = null,
                    titleTextColor = null,
                    titleIcon = null,
                    titleIconTintColor = null,
                    titleIconContentDescription = null,
                    genericTextColor = Color.WHITE,
                    genericIconTintColor = null,
                    genericDividerLinesColor = Color.DKGRAY,
                    menuItems = mutableListOf(),
                    onShowAction = null,
                    onDismissAction = null,
                    alpha = 1f,
                    backgroundColor = Color.BLACK,
                    strokeWidth = 1,
                    strokeColor = Color.WHITE,

                    cornerRadiusTopLeftPx = PopupMenuConfig.DEFAULT_POPUP_MENU_CORNER_RADIUS,
                    cornerRadiusTopRightPx = PopupMenuConfig.DEFAULT_POPUP_MENU_CORNER_RADIUS,
                    cornerRadiusBottomRightPx = PopupMenuConfig.DEFAULT_POPUP_MENU_CORNER_RADIUS,
                    cornerRadiusBottomLeftPx = PopupMenuConfig.DEFAULT_POPUP_MENU_CORNER_RADIUS,
                )
            }
        }

    inline fun setTitle(modification: (oldTitle: String?) -> String?) = this.apply {
        model.title = modification.invoke(model.title)
    }

    fun getTitle() = model.title

    inline fun setTitleTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.titleTextColor = modification.invoke(model.titleTextColor)
    }

    fun getTitleTextColor() = model.titleTextColor

    inline fun setTitleIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.titleIcon = modification.invoke(model.titleIcon)
    }

    fun getTitleIcon() = model.titleIcon

    inline fun setTitleIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.titleIconTintColor = modification.invoke(model.titleIconTintColor)
    }

    fun getTitleIconTintColor() = model.titleIconTintColor

    inline fun setTitleIconContentDescription(modification: (oldDescription: String?) -> String?) = this.apply {
        model.titleIconContentDescription = modification.invoke(model.titleIconContentDescription)
    }

    fun getTitleIconContentDescription() = model.titleIconContentDescription

    inline fun setGenericTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.genericTextColor = modification.invoke(model.genericTextColor)
    }

    fun getGenericTextColor() = model.genericTextColor

    inline fun setGenericIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericIconTintColor = modification.invoke(model.genericIconTintColor)
    }

    fun getGenericIconTintColor() = model.genericIconTintColor

    inline fun setGenericDividerLinesColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericDividerLinesColor = modification.invoke(model.genericDividerLinesColor)
    }

    fun getGenericDividerLinesColor() = model.genericDividerLinesColor

    fun setAllMenuItems(modification: (oldItems: MutableList<PopupMenuItem>) -> MutableList<PopupMenuItem>) = this.apply {
        model.menuItems = modification.invoke(model.menuItems)
    }

    fun removeAllMenuItems() = this.apply {
        model.menuItems.clear()
    }

    fun setOnShowAction(onShowAction: ((view: View, tooltipPopupWindow: PopupWindow) -> Unit)?) = this.apply {
        model.onShowAction = onShowAction
    }

    fun hasOnShowAction() = model.onShowAction != null

    fun removeOnShowAction() = this.apply {
        model.onShowAction = null
    }

    fun setOnDismissAction(onDismissAction: ((tooltipPopupWindow: PopupWindow) -> Unit)?) = this.apply {
        model.onDismissAction = onDismissAction
    }

    fun hasOnDismissAction() = model.onDismissAction != null

    fun removeOnDismissAction() = this.apply {
        model.onDismissAction = null
    }

    inline fun setAlpha(modification: (oldAlpha: Float) -> Float) = this.apply {
        model.alpha = modification.invoke(model.alpha)
    }

    fun getAlpha() = model.alpha

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setStrokeWidth(modification: (oldWidth: Int) -> Int) {
        model.strokeWidth = modification.invoke(model.strokeWidth)
    }

    fun getStrokeWidth() = model.strokeWidth

    inline fun setStrokeColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.strokeColor = modification.invoke(model.strokeColor)
    }

    fun setCornerRadii(topLeftPx: Int, topRightPx: Int, bottomRightPx: Int, bottomLeftPx: Int) = this.apply {
        model.cornerRadiusTopLeftPx = topLeftPx
        model.cornerRadiusTopRightPx = topRightPx
        model.cornerRadiusBottomRightPx = bottomRightPx
        model.cornerRadiusBottomLeftPx = bottomLeftPx
    }

    inline fun setCornerRadiusTopLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopLeftPx = modification.invoke(model.cornerRadiusTopLeftPx)
    }

    fun getCornerRadiusTopLeftPx() = model.cornerRadiusTopLeftPx

    inline fun setCornerRadiusTopRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopRightPx = modification.invoke(model.cornerRadiusTopRightPx)
    }

    fun getCornerRadiusTopRightPx() = model.cornerRadiusTopLeftPx

    inline fun setCornerRadiusBottomRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomRightPx = modification.invoke(model.cornerRadiusBottomRightPx)
    }

    fun getCornerRadiusBottomRightPx() = model.cornerRadiusTopLeftPx

    inline fun setCornerRadiusBottomLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomRightPx = modification.invoke(model.cornerRadiusBottomLeftPx)
    }

    fun getCornerRadiusBottomLeftPx() = model.cornerRadiusBottomLeftPx

    fun setAnchorViewId(modification: (id: Int?) -> Int?) = this.apply {
        model.anchorViewId = modification.invoke(model.anchorViewId)
    }

    fun getAnchorViewId() = model.anchorViewId

    fun setGravity(modification: (oldGravity: Int) -> Int) = this.apply {
        model.gravity = modification.invoke(model.gravity)
    }

    fun getGravity() = model.gravity

    fun setXOff(modification: (oldXOff: Int) -> Int) = this.apply {
        model.xOff = modification.invoke(model.xOff)
    }

    fun getXOff() = model.xOff

    fun setYOff(modification: (oldYOff: Int) -> Int) = this.apply {
        model.yOff = modification.invoke(model.yOff)
    }

    fun getYOff() = model.yOff

    fun setCustomAnimationStyle(modification: (oldStyle: Int?) -> Int?) = this.apply {
        model.customAnimationStyle = modification.invoke(model.customAnimationStyle)
    }

    fun getCustomAnimationStyle() = model.customAnimationStyle

    fun getKey() = model.key

    fun save(replaceIfPresent: Boolean = true) = this.apply {
        PopupMenuToolbox.savePopupMenuModel(model.key, model, replaceIfPresent)
    }

    fun show(
        activity: Activity,
        anchorView: View,
        queueMode: PopupMenuConfig.QueueMode = PopupMenuConfig.QueueMode.CONTINUE_OLD_IF_SHOWING,
        saveInfo: Boolean = true
    ) =
        if(queueMode.name == PopupMenuConfig.QueueMode.DISPLAY_NEW.name
            || PopupMenuToolbox.getSavedPopupMenuModel(model.key) == null
            || PopupMenuToolbox.getSavedPopupMenuModel(model.key)!!.popup == null
        ) {
            if(queueMode.name == PopupMenuConfig.QueueMode.DISPLAY_NEW.name)
                PopupMenuToolbox.dismissPopupMenu(model.key, false)

            model.anchorViewId = if(anchorView.id != View.NO_ID) anchorView.id else null

            model.displayed = true

            model.saveModel = saveInfo

            save()

            PopupMenu(
                null,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                model.key
            ).run {
                contentView = LayoutPopupMenuBinding.inflate(activity.layoutInflater, null, false).apply {
                    layoutPopupMenuContentConstraintLayout.let {
                        it.alpha = model.alpha

                        it.background = DrawableBuilder()
                            .solidColor(model.backgroundColor)
                            .cornerRadii(
                                model.cornerRadiusTopLeftPx,
                                model.cornerRadiusTopRightPx,
                                model.cornerRadiusBottomRightPx,
                                model.cornerRadiusBottomLeftPx,
                            )
                            .strokeWidth(model.strokeWidth)
                            .strokeColor(model.strokeColor)
                            .build()
                    }

                    layoutPopupMenuListRecyclerView.let {
                        it.adapter = PopupMenuAdapter(
                            model.key,
                            this@run,
                            activity
                        )

                        it.layoutManager!!.onRestoreInstanceState(model.listState)
                    }
                }.root

                setOnDismissListener {
                    if(!fakeDismissal) {
                        PopupMenuToolbox.getSavedPopupMenuModel(model.key)?.popup = null

                        if(!model.saveModel) PopupMenuToolbox.deletePopupMenuInfo(model.key)

                        model.onDismissAction?.invoke(this)
                    }
                }

                isOutsideTouchable = false

                animationStyle = model.customAnimationStyle ?: defaultAnimationStyle

                isClippingEnabled = false

                contentView.rootView.alpha = 0f

                showAsDropDown(anchorView)

                contentView.post {
                    val contentViewHeight = contentView.height

                    fakeDismissal = true
                    PopupMenuToolbox.getSavedPopupMenuModel(model.key)!!.popup?.dismiss()
                    fakeDismissal = false

                    val displayMetrics = DisplayMetrics()

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                        activity.display?.getMetrics(displayMetrics)
                    else
                        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

                    val anchorBottom: Int

                    IntArray(2).run {
                        anchorView.getLocationInWindow(this)

                        anchorBottom = this[1] + anchorView.height
                    }

                    contentView.rootView.alpha = model.alpha

                    isClippingEnabled = true

                    val requiredSpace = anchorBottom + contentViewHeight + model.yOff /*+ activity.resources.getIdentifier("navigation_bar_height", "dimen", "android")*/

                    show(anchorView, model.xOff, if(requiredSpace > displayMetrics.heightPixels) -(contentViewHeight + anchorView.height + model.yOff) else model.yOff, model.gravity)
                }
            }

            PopupMenuConfig.RandomKeyGenerationUtils.transientKeyRegistry.remove(model.key)

            model.key
        } else {
            Log.d(this::class.java.name, LOG_MESSAGE_POPUP_MENU_ALREADY_SHOWING)

            null
        }

    fun `continue`(activity: Activity, anchorView: View? = null, saveInfo: Boolean = true) {
        PopupMenuToolbox.getSavedPopupMenuModel(model.key)?.run {
            popup?.dismiss()

            if(model.displayed)
                when {
                    anchorView != null -> show(
                        activity,
                        anchorView,
                        PopupMenuConfig.QueueMode.DISPLAY_NEW,
                        saveInfo
                    )
                    model.anchorViewId != null -> show(
                        activity,
                        activity.findViewById(model.anchorViewId!!),
                        PopupMenuConfig.QueueMode.DISPLAY_NEW,
                        saveInfo
                    )
                    else -> throw IllegalStateException("PopupMenu trying to continue could not restore automatically because either it was previously anchored to a view with invalid null id or the activity parameter is not hosting the anchor view. Try this method with explicitly specified anchorView parameter or check if the activity parameter is the correct type")
                }
        }
    }
}