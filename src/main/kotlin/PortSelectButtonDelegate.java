import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PortSelectButtonDelegate extends PortSelectButtonLayout {
    PortSelectButtonDelegate(){

    }

    static void update_serial_port() {
        SerialPort[] ports = new SerialInterface().get_ports();
        List<String> port_names = new ArrayList<String>();
        for (SerialPort port: ports) {
            port_names.add(port.getDescriptivePortName());
        }
        update_port_combo_box(port_names);
    }

    static void connect_btn_clk(){
        int port_idx = port_combo_box.getSelectedIndex();

    }
}
