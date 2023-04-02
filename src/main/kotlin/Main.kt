import com.formdev.flatlaf.FlatDarkLaf
import java.awt.BorderLayout
import java.awt.Dimension
import java.lang.Boolean
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun createAndShowGUI(): JFrame {
    //Create and set up the window.
    val frame = JFrame("Serial Manager")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.minimumSize = Dimension(500,600)

    //Display the window.
    return frame
}
fun main() {
    FlatDarkLaf.setup()
    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.

    SwingUtilities.invokeLater { //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE)
        val frame = createAndShowGUI()

        val master = MasterPane()
        frame.add(master, BorderLayout.CENTER)
        frame.pack()
        frame.isVisible = true
    }
}