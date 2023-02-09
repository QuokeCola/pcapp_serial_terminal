@file:Suppress("PrivatePropertyName")

import java.awt.Dimension
import java.awt.FlowLayout
import java.io.Serial
import javax.swing.*

class TerminalPanel  : JPanel() {

    private val clear_button    = JButton("clear output")
    private val send_button    = JButton("send")
    private val terminal_disp = JTextPane()
    private val terminal_screen = JScrollPane(terminal_disp)
    private val terminal_input  = JTextField()
    private val macro_panel  = JPanel()

    private val hello_button = JButton("hello")
    private val stats_button = JButton("status")
    private val memory_button = JButton("memory")
    private val systime_buttton = JButton("runtime")
    private val threads_button = JButton("threads")
    private val threads_button1 = JButton("threads")
    private val threads_button2 = JButton("threads")

    private val BORDER_WIDTH = 20
    private val GAP_WIDTH = 20

    init {

        val layout = SpringLayout()

        this.add(clear_button)
        terminal_disp.isEditable = false
        terminal_disp.alignmentY = BOTTOM_ALIGNMENT
        terminal_input.size = Dimension(send_button.size)

        macro_panel.border = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("macros"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        )
        macro_panel.add(hello_button)
        macro_panel.add(stats_button)
        macro_panel.add(memory_button)
        macro_panel.add(systime_buttton)
        macro_panel.add(threads_button)
        macro_panel.add(threads_button1)
        macro_panel.add(threads_button2)

        macro_panel.layout = FlowLayout(FlowLayout.LEFT)
        this.add(terminal_screen)
        this.add(terminal_input)
        this.add(send_button)
        this.add(macro_panel)

        layout.putConstraint(SpringLayout.EAST, clear_button,
            -BORDER_WIDTH,
            SpringLayout.EAST, this)
        layout.putConstraint(SpringLayout.NORTH, clear_button,
            BORDER_WIDTH,
            SpringLayout.NORTH, this)

        layout.putConstraint(SpringLayout.EAST, send_button,
            -BORDER_WIDTH,
            SpringLayout.EAST, this)
        layout.putConstraint(SpringLayout.SOUTH, send_button,
            -GAP_WIDTH,
            SpringLayout.NORTH, macro_panel)

        layout.putConstraint(SpringLayout.WEST, terminal_input,
            BORDER_WIDTH,
            SpringLayout.WEST, this)
        layout.putConstraint(SpringLayout.EAST, terminal_input,
            -GAP_WIDTH,
            SpringLayout.WEST, send_button)
        layout.putConstraint(SpringLayout.SOUTH, terminal_input,
            0,
            SpringLayout.SOUTH, send_button)
        layout.putConstraint(SpringLayout.NORTH, terminal_input,
            GAP_WIDTH,
            SpringLayout.SOUTH, terminal_screen)

        layout.putConstraint(SpringLayout.NORTH, terminal_screen,
            GAP_WIDTH,
            SpringLayout.SOUTH,    clear_button
        )
        layout.putConstraint(SpringLayout.WEST, terminal_screen,
            BORDER_WIDTH,
            SpringLayout.WEST, this)
        layout.putConstraint(SpringLayout.EAST, terminal_screen,
            -BORDER_WIDTH,
            SpringLayout.EAST, this)
        layout.putConstraint(SpringLayout.SOUTH, terminal_screen,
            -GAP_WIDTH,
            SpringLayout.NORTH, send_button)

        layout.putConstraint(SpringLayout.EAST, macro_panel,
            -BORDER_WIDTH+2,
            SpringLayout.EAST, this)
        layout.putConstraint(SpringLayout.SOUTH, macro_panel,
            -BORDER_WIDTH+2,
            SpringLayout.SOUTH, this)
        layout.putConstraint(SpringLayout.WEST, macro_panel,
            BORDER_WIDTH-2,
            SpringLayout.WEST, this)

        this.layout = layout

        macro_panel.size = macro_panel.preferredSize;
        macro_panel.revalidate()
        macro_panel.repaint()
        this.revalidate()
        this.repaint()
    }

}