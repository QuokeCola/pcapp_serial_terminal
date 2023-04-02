import kotlin.jvm.internal.markers.KMutableList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PortSelectButtonLayout {

    PortSelectButtonLayout(){
        var menu_bar_layout = new FlowLayout();
        menu_bar_layout.setAlignment(FlowLayout.RIGHT);

        menu_bar.setFloatable(false);
        menu_bar.setRollover(false);

        menu_bar.setLayout(menu_bar_layout);
        connect_button.setText("connect");
        menu_bar.add(port_combo_box);
        menu_bar.add(connect_button);
    }
    public static final JToolBar menu_bar = new JToolBar();
    public static final JComboBox<String> port_combo_box = new JComboBox<String>();
    public static final JButton connect_button = new JButton("connect");

    static void update_port_combo_box(List<String> port_names) {
        port_combo_box.removeAllItems();
        for (String port_name : port_names) {
            port_combo_box.addItem(port_name);
        }
    }
}
