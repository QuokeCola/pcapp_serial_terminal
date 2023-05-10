import de.schipplock.gui.swing.svgicon.SvgIconManager;

import javax.swing.*;
import java.awt.*;

public class PortSelectButtonLayout {

    PortSelectButtonLayout(){
        var menu_bar_layout = new FlowLayout();
        menu_bar_layout.setAlignment(FlowLayout.RIGHT);

        menu_bar.setFloatable(false);
        menu_bar.setRollover(false);

        menu_bar.setLayout(menu_bar_layout);
        var icon_dim = new Dimension(18,18);
        connect_button.setSize(icon_dim);
        set_connect_btn_icon(true);
        menu_bar.add(port_combo_box);
        menu_bar.add(connect_button);
    }
    public static final JToolBar menu_bar = new JToolBar();
    public static final JComboBox<String> port_combo_box = new JComboBox<String>();
    public static final JButton connect_button = new JButton();

    /**
     * Set connect button's icon.
     * @param connect true: icon turns to green connect button. false: icon turns to red disconnect button.
     */
    public void set_connect_btn_icon(boolean connect) {
        var icon_dim = new Dimension(18,18);
        var icon = connect ? SvgIconManager.getIcon("icons/connect.svg",icon_dim) :
                             SvgIconManager.getIcon("icons/disconnect.svg",icon_dim);
        connect_button.setIcon(icon);
    }
}
