package bogdandonduk.popupmenutoolboxlib

import bogdandonduk.randomkeygenerator.RandomKeyGenerator

object PopupMenuConfig {
    const val DEFAULT_POPUP_MENU_CORNER_RADIUS = 30

    enum class QueueMode {
        DISPLAY_NEW,
        CONTINUE_OLD_IF_SHOWING
    }

    object RandomKeyGenerationUtils {
        internal val transientKeyRegistry = mutableListOf<String>()

        fun generate() = RandomKeyGenerator.generateWithPrefix("popup_menu") {
            PopupMenuToolbox.getSavedPopupMenuModel(it) != null || transientKeyRegistry.contains(it)
        }

        fun getRandomKeyWithClassSuffix(host: Any) = "${generate()}_${host::class.java}"
    }

    object KeysExtensionVocabulary {
        const val KEY_POPUP_MENU_SEARCH = "key_popup_menu_search"
        const val KEY_POPUP_MENU_SORTING = "key_popup_menu_sorting"
    }
}