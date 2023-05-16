import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.util.SystemInfo
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.KeyEvent
import javax.swing.*
import kotlin.String

class MasterPane : JPanel(BorderLayout()) {
    init {
        System.setProperty("sun.java2d.opengl", "true")
        PortSelectButtonDelegate()
        add(PortSelectButtonDelegate.menu_bar, BorderLayout.PAGE_START)

        val tabbedPane = JTabbedPane()
        val icon = createImageIcon("")
        TerminalDelegate()
        TerminalDelegate.main_panel.preferredSize = Dimension(500,600);
        tabbedPane.addTab(
            "Terminal", icon, TerminalLayout.main_panel,
            "Input commands and check feedback."
        )
        tabbedPane.addTab(
            "Plotter", icon, PlotterLayout.main_panel,
            "Plotter"
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

    companion object {
        fun createAndShowGUI(): JFrame {
            //Create and set up the window.
            val frame = JFrame("Serial Manager")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.minimumSize = Dimension(500,600)

            //Display the window.
            return frame
        }
        @JvmStatic
        fun main(args: Array<String>) {
            System.setProperty("apple.awt.application.appearance", "NSAppearanceNameDarkAqua")
            System.setProperty("javax.usb.services", "org.usb4java.javax.Services")
            FlatDarkLaf.setup()
            //Schedule a job for the event dispatch thread:
            //creating and showing this application's GUI.
            //Start serial receiving thread.
            SerialInterface()
            SwingUtilities.invokeLater { //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", false)
                val frame = createAndShowGUI()

                val master = MasterPane()
                if (SystemInfo.isMacOS) {
                    frame.rootPane.putClientProperty("apple.awt.fullWindowContent", true)
                    frame.rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
                    frame.add(Box.createVerticalStrut(30), BorderLayout.PAGE_START)
                }
                frame.add(master, BorderLayout.CENTER)
                frame.pack()
                frame.isVisible = true
            }
        }
    }
}

