package bogdandonduk.popupmenutoolboxlib

interface PopupMenusHandler {
    fun continuePopupMenus(vararg excludedKeys: String)

    fun dismissPopupMenus(vararg excludedKeys: String)
}