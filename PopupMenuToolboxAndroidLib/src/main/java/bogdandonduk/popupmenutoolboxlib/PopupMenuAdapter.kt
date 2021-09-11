package bogdandonduk.popupmenutoolboxlib

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.popupmenutoolboxlib.databinding.LayoutPopupMenuItemBinding
import bogdandonduk.popupmenutoolboxlib.databinding.LayoutPopupMenuTitleBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import top.defaults.drawabletoolbox.DrawableBuilder

internal class PopupMenuAdapter(
    var key: String,
    var popup: PopupMenu,
    var hostActivity: Activity
) : RecyclerView.Adapter<PopupMenuAdapter.ViewHolder>() {
    var titleSet = false

    var model = PopupMenuToolbox.getSavedPopupMenuModel(key)!!

    init {
        model.run {
            if(title != null) {
                menuItems.add(0,
                    PopupMenuItem(
                        title!!,
                        titleTextColor ?: genericTextColor,
                        titleIcon,
                        titleIconTintColor,
                        titleIconContentDescription
                    ) { _, _, _ ->

                    }
                )

                titleSet = true
            }
        }
    }

    inner class ViewHolder(var root: View, var text: TextView, var icon: ImageView, var dividerLine: View) : RecyclerView.ViewHolder(root) {
        lateinit var item: PopupMenuItem

        init {
            root.setOnClickListener {
                PopupMenuToolbox.dismissPopupMenu(key, false)

                if(this::item.isInitialized)
                    item.onClickAction.invoke(item.settingTag, it, popup)
            }
        }
    }

    override fun getItemViewType(position: Int) = if(titleSet && position == 0) 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val root: View
        val text: TextView
        val icon: ImageView
        var dividerLine: View

        if(viewType == 0)
            LayoutPopupMenuItemBinding.inflate(hostActivity.layoutInflater, parent, false).run {
                root = this.root
                text = layoutPopupMenuItemTextTextView
                icon = layoutPopupMenuItemIconImageView
                dividerLine = layoutPopupMenuItemDividerUnderlineLinearLayout
            }
        else
            LayoutPopupMenuTitleBinding.inflate(hostActivity.layoutInflater, parent, false).run {
                root = this.root
                text = layoutPopupMenuTitleTextTextView
                icon = layoutPopupMenuTitleIconImageView
                dividerLine = layoutPopupMenuTitleDividerUnderlineLinearLayout
            }

        return ViewHolder(root, text, icon, dividerLine)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            item = model.menuItems[position]

            val rippleColor = CommonToolbox.getRippleColorByLuminance(hostActivity, model.backgroundColor)

            if(!titleSet || position != 0)
                root.background =
                    DrawableBuilder()
                    .solidColor(if(item.isChecked) rippleColor else Color.TRANSPARENT)
                    .ripple()
                    .rippleColor(rippleColor)
                    .apply {
                        when {
                            itemCount == 1 -> cornerRadii(if(titleSet) 0 else model.cornerRadiusTopLeftPx, if(titleSet) 0 else model.cornerRadiusTopRightPx, model.cornerRadiusBottomRightPx, model.cornerRadiusBottomLeftPx)
                            position == 0 -> cornerRadii(if(titleSet) 0 else model.cornerRadiusTopLeftPx, if(titleSet) 0 else model.cornerRadiusTopRightPx, 0, 0)
                            position == itemCount - 1 -> cornerRadii(0, 0, model.cornerRadiusBottomRightPx, model.cornerRadiusBottomLeftPx)

                            else -> cornerRadii(0, 0, 0, 0)
                        }
                    }
                    .build()

            text.run {
                text = item.text
                setTextColor(item.textColor ?: model.genericTextColor)

                post {
                    if(height >= lineHeight * 2)
                        root.setPadding(root.paddingStart + 20, root.paddingTop, root.paddingEnd + 20, root.paddingBottom)

                    if(item.icon != null) {
                        icon.run {
                            visibility = View.VISIBLE

                            Glide.with(hostActivity)
                                .load(item.icon)
                                .priority(Priority.HIGH)
                                .into(icon)

                            contentDescription = item.iconContentDescription

                            if(item.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, item.iconTintColor!!)
                            else if(model.genericIconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, model.genericIconTintColor!!)

                            post {
                                root.setPadding(root.paddingStart, root.paddingTop, root.paddingEnd + width, root.paddingBottom)
                            }
                        }
                    } else {
                        icon.run {
                            visibility = View.GONE
                            setImageDrawable(null)
                            contentDescription = null
                        }
                    }
                }
            }

            if(item.closingGroup) {
                if(item.dividerLineColor != null)
                    dividerLine.setBackgroundColor(item.dividerLineColor!!)
                else if(model.genericDividerLinesColor != null)
                    dividerLine.setBackgroundColor(model.genericDividerLinesColor!!)
            }
        }
    }

    override fun getItemCount() = model.menuItems.size
}