package bogdandonduk.popupmenutoolboxlib

object PopupMenuToolbox {
    private val savedPopupMenuModels = mutableMapOf<String, PopupMenuModel>()

    internal fun getSavedPopupMenuModel(key: String) = savedPopupMenuModels[key]

    @Synchronized
    internal fun savePopupMenuModel(key: String, model: PopupMenuModel, replaceIfPresent: Boolean = true) {
        if(replaceIfPresent || !savedPopupMenuModels.containsKey(key))
            savedPopupMenuModels[key] = model
    }

    @Synchronized
    fun deletePopupMenuInfo(key: String) {
        savedPopupMenuModels.remove(key)
    }

    fun dismissPopupMenu(key: String, deleteInfo: Boolean = true) {
        savedPopupMenuModels[key]?.run {
            popup?.dismiss()

            displayed = false
        }

        if(deleteInfo) savedPopupMenuModels.remove(key)
    }

    fun buildPopupMenu(key: String = PopupMenuConfig.RandomKeyGenerationUtils.generate()) = PopupMenuBuilder(key)

    fun buildPopupMenuItem() = PopupMenuItemBuilder()

    fun isPopupMenuShowing(key: String) = savedPopupMenuModels[key] != null && savedPopupMenuModels[key]!!.displayed
}