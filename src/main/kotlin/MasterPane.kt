import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.KeyEvent
import javax.swing.*
import kotlin.String

class MasterPane : JPanel(BorderLayout()) {
    init {
        PortSelectButtonDelegate()
        add(PortSelectButtonDelegate.menu_bar, BorderLayout.PAGE_START)

        val tabbedPane = JTabbedPane()
        val icon = createImageIcon("")
        TerminalPanelLayout().preferredSize = Dimension(500,600);
        tabbedPane.addTab(
            "Terminal", icon, TerminalPanelLayout(),
            "Input commands and check feedback."
        )
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1)
        //Add the tabbed pane to this panel.
        add(tabbedPane)
        //The following line enables to use scrolling tabs.
        tabbedPane.tabLayoutPolicy = JTabbedPane.SCROLL_TAB_LAYOUT
    }

    fun createImageIcon(path: String): ImageIcon? {
        val imgURL = MasterPane::class.java.getResource(path)
        return if (imgURL != null) {
            ImageIcon(imgURL)
        } else {
            System.err.println("Couldn't find file: $path")
            null
        }
    }
}

