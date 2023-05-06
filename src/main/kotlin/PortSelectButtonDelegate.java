import com.fazecast.jSerialComm.SerialPort;

import java.util.ArrayList;
import java.util.List;

public class PortSelectButtonDelegate extends PortSelectButtonLayout {
    PortSelectButtonDelegate(){
        var delegate_thread = new DelegateThread();
        delegate_thread.start();
    }

    private static final int THREAD_INTERVAL = 200; // ms
    private static class DelegateThread extends Thread {
        SerialPort[] prev_ports = new SerialInterface().get_ports();
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    SerialPort[] ports = new SerialInterface().get_ports();
                    boolean port_updated = (prev_ports.length != ports.length);
                    if (!port_updated) {
                        for (int idx = 0; idx < ports.length; idx++) {
                            if (!ports[idx].getDescriptivePortName().equals(prev_ports[idx].getDescriptivePortName())) {
                                port_updated = true;
                                break;
                            }
                        }
                    }
                    if (port_updated) {
                        port_combo_box.removeAllItems();
                        for (SerialPort port: ports) {
                            port_combo_box.addItem(port.getDescriptivePortName());
                        }
                        prev_ports = ports;
                    }
                    Thread.sleep(THREAD_INTERVAL);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static void connect_btn_clk(){
        int port_idx = port_combo_box.getSelectedIndex();
        new SerialInterface().set_port(new SerialInterface().get_ports()[port_idx]);
    }
}
