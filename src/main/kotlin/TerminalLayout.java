import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Component.BOTTOM_ALIGNMENT;

public class TerminalLayout {
    public  static JPanel main_panel           = new JPanel();
    public  static JButton clear_btn           = new JButton("clear output");
    public static JButton send_button         = new JButton("send");

    public static JSpinner disp_length_spinner = new JSpinner();
    public static JTextPane terminal_disp     = new JTextPane();
    public static final JScrollPane terminal_screen = new JScrollPane(terminal_disp);
    public static final JTextField terminal_input   = new JTextField();
    private static final JPanel macro_panel          = new JPanel();
    private static final List<JButton> macro_buttons = new ArrayList<JButton>();
    private static final int BORDER_WIDTH = 20;
    private static final int GAP_WIDTH = 20;

    TerminalLayout(){
        SpringLayout layout = new SpringLayout();
        main_panel.add(clear_btn);
        terminal_disp.setEditable(false);
        terminal_disp.setAlignmentY(BOTTOM_ALIGNMENT);
        terminal_input.setSize(new Dimension(send_button.getSize()));
        macro_panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("macros"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        macro_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        var disp_length_spinner_label = new JLabel("display lines:");
        disp_length_spinner_label.setLabelFor(disp_length_spinner);
        main_panel.add(disp_length_spinner_label);
        main_panel.add(disp_length_spinner);
        main_panel.add(terminal_screen);
        main_panel.add(terminal_input);
        main_panel.add(send_button);
        main_panel.add(macro_panel);
        main_panel.add(disp_length_spinner);

        layout.putConstraint(SpringLayout.EAST, clear_btn,
                -BORDER_WIDTH,
                SpringLayout.EAST, main_panel);
        layout.putConstraint(SpringLayout.NORTH, clear_btn,
                BORDER_WIDTH,
                SpringLayout.NORTH, main_panel);

        layout.putConstraint(SpringLayout.WEST, disp_length_spinner_label,
                BORDER_WIDTH,
                SpringLayout.WEST, main_panel);
        layout.putConstraint(SpringLayout.NORTH, disp_length_spinner_label,
                BORDER_WIDTH,
                SpringLayout.NORTH, main_panel);

        layout.putConstraint(SpringLayout.WEST, disp_length_spinner,
                BORDER_WIDTH,
                SpringLayout.EAST, disp_length_spinner_label);
        layout.putConstraint(SpringLayout.NORTH, disp_length_spinner,
                BORDER_WIDTH,
                SpringLayout.NORTH, main_panel);

        layout.putConstraint(SpringLayout.EAST, send_button,
                -BORDER_WIDTH,
                SpringLayout.EAST, main_panel);
        layout.putConstraint(SpringLayout.SOUTH, send_button,
                -GAP_WIDTH,
                SpringLayout.NORTH, macro_panel);

        layout.putConstraint(SpringLayout.WEST, terminal_input,
                BORDER_WIDTH,
                SpringLayout.WEST, main_panel);
        layout.putConstraint(SpringLayout.EAST, terminal_input,
                -GAP_WIDTH,
                SpringLayout.WEST, send_button);
        layout.putConstraint(SpringLayout.SOUTH, terminal_input,
                0,
                SpringLayout.SOUTH, send_button);
        layout.putConstraint(SpringLayout.NORTH, terminal_input,
                GAP_WIDTH,
                SpringLayout.SOUTH, terminal_screen);

        layout.putConstraint(SpringLayout.NORTH, terminal_screen,
                GAP_WIDTH,
                SpringLayout.SOUTH,    clear_btn
        );
        layout.putConstraint(SpringLayout.WEST, terminal_screen,
                BORDER_WIDTH,
                SpringLayout.WEST, main_panel);
        layout.putConstraint(SpringLayout.EAST, terminal_screen,
                -BORDER_WIDTH,
                SpringLayout.EAST, main_panel);
        layout.putConstraint(SpringLayout.SOUTH, terminal_screen,
                -GAP_WIDTH,
                SpringLayout.NORTH, send_button);

        layout.putConstraint(SpringLayout.EAST, macro_panel,
                -BORDER_WIDTH+2,
                SpringLayout.EAST, main_panel);
        layout.putConstraint(SpringLayout.SOUTH, macro_panel,
                -BORDER_WIDTH+2,
                SpringLayout.SOUTH, main_panel);
        layout.putConstraint(SpringLayout.WEST, macro_panel,
                BORDER_WIDTH-2,
                SpringLayout.WEST, main_panel);
        main_panel.setLayout(layout);

        macro_panel.setSize(macro_panel.getPreferredSize());
        macro_panel.revalidate();
        macro_panel.repaint();
        main_panel.revalidate();
        main_panel.repaint();
    }
}
