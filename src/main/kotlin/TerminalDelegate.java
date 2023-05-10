import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TerminalDelegate extends TerminalLayout{
    private static int TERMINAL_DISP_MAX_LINE = 200;
    private static List<String> rx_buffer_str = new ArrayList<>();
    private static Document terminal_doc;

    private static SimpleAttributeSet  keyWord = new SimpleAttributeSet();
    TerminalDelegate() {
        SerialInterface.callbacks.add(terminal_serial_callback);
        send_button.addActionListener(send_btns_listener);
        clear_btn.addActionListener(clear_btn_listener);
        terminal_doc = terminal_disp.getDocument();
        disp_length_spinner.setModel(new SpinnerNumberModel(TERMINAL_DISP_MAX_LINE, 1, 200, 1));
        disp_length_spinner.addChangeListener(disp_length_spinner_listener);
    }

    private static final serial_callback terminal_serial_callback = new serial_callback() {

        @Override
        public void process_callback(String rx_string) throws BadLocationException {
            while (rx_buffer_str.size() > TERMINAL_DISP_MAX_LINE) {
                terminal_doc.remove(0,rx_buffer_str.get(0).length());
                rx_buffer_str.remove(0);
            }
            if (rx_buffer_str.size()<TERMINAL_DISP_MAX_LINE) { // scroll to bottom
                JScrollBar vertical = terminal_screen.getVerticalScrollBar();
                vertical.setValue( vertical.getMaximum());
            }
            rx_buffer_str.add(rx_string+"\r\n");
            terminal_doc.insertString(terminal_doc.getLength(), rx_buffer_str.get(rx_buffer_str.size()-1), keyWord);
        }
    };

    ActionListener send_btns_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getActionCommand().equals("send")) {
                    SerialInterface.send(terminal_input.getText(), "\r\n");
                } else {
                    SerialInterface.send(e.getActionCommand(), "\r\n");
                }

            } catch (Exception ignored) {}
        }
    };

    ChangeListener disp_length_spinner_listener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            TERMINAL_DISP_MAX_LINE = Integer.parseInt(disp_length_spinner.getValue().toString());
        }
    };

    ActionListener clear_btn_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                rx_buffer_str.clear();
                terminal_doc.remove(0, terminal_doc.getLength());
            } catch (Exception ignored) {}
        }
    };
}