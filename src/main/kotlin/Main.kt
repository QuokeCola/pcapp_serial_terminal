import com.formdev.flatlaf.FlatDarkLaf
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.KeyEvent
import java.lang.Boolean
import javax.swing.*
import kotlin.Array
import kotlin.String

class MasterPane : JPanel(GridLayout(1, 1)) {
    val serial_interface = SerialInterface()
    init {
        print(serial_interface.get_ports())
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

    protected fun makeTextPanel(text: String?): JComponent {
        val panel = JPanel(false)
        val filler = JLabel(text)
        filler.horizontalAlignment = JLabel.CENTER
        panel.layout = GridLayout(1, 1)
        panel.add(filler)
        return panel
    }

    companion object {
        /** Returns an ImageIcon, or null if the path was invalid.  */
        protected fun createImageIcon(path: String): ImageIcon? {
            val imgURL = MasterPane::class.java.getResource(path)
            return if (imgURL != null) {
                ImageIcon(imgURL)
            } else {
                System.err.println("Couldn't find file: $path")
                null
            }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from
         * the event dispatch thread.
         */
        private fun createAndShowGUI() {
            //Create and set up the window.
            val frame = JFrame("TabbedPaneDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.minimumSize = Dimension(500,600)
            //Add content to the window.
            frame.add(MasterPane(), BorderLayout.CENTER)

            //Display the window.
            frame.pack()
            frame.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) {
            FlatDarkLaf.setup()
            //Schedule a job for the event dispatch thread:
            //creating and showing this application's GUI.
            SwingUtilities.invokeLater { //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE)
                createAndShowGUI()
            }
        }
    }
}