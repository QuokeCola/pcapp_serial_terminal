import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PortSelectButtonDelegate extends PortSelectButtonLayout {
    PortSelectButtonDelegate(){
        var delegate_thread = new DelegateThread();
        delegate_thread.start();
        port_combo_box.addActionListener(port_select_btn_listener);
        connect_button.addActionListener(connect_btn_listener);
        for (int baudrate_candidate: baudrate_candidates) {
             baudrate_combo_box.addItem(Integer.toString(baudrate_candidate));
        }
        baudrate_combo_box.addActionListener(baudrate_select_btn_listener);
    }

    private static final int[] baudrate_candidates = new int[]{110, 300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 38400, 57600, 115200, 128000, 256000};
    private static final int THREAD_INTERVAL = 200; // ms
    private static class DelegateThread extends Thread {
        SerialPort[] prev_ports = new SerialInterface().get_ports();

        @Override
        public void run() {
            port_combo_box.removeAllItems();
            for (SerialPort port: prev_ports) {
                port_combo_box.addItem(port.getDescriptivePortName());
            }
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

    private static boolean opening_port_selected = false;

    ActionListener connect_btn_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int port_idx = port_combo_box.getSelectedIndex();
            if (port_idx== -1) {
                return;
            }
            SerialInterface SRIF = new SerialInterface();
            opening_port_selected = SRIF.get_ports()[port_idx].getDescriptivePortName().equals(SRIF.port_name());
            if (SRIF.is_opened()) {
                SRIF.stop_receive();
                set_connect_btn_icon(true);
            } else if(opening_port_selected) {
                SRIF.open_port(SRIF.get_ports()[port_idx]);
                SRIF.set_port_baudrate(baudrate_candidates[baudrate_combo_box.getSelectedIndex()]);
                set_connect_btn_icon(false);
            }
            if (!opening_port_selected) {
                SRIF.open_port(SRIF.get_ports()[port_idx]);
                SRIF.set_port_baudrate(baudrate_candidates[baudrate_combo_box.getSelectedIndex()]);
                set_connect_btn_icon(false);
            }
        }
    };

    ActionListener port_select_btn_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int port_idx = port_combo_box.getSelectedIndex();
            if (port_idx == -1) {
                return;
            }
            SerialInterface SRIF = new SerialInterface();
            opening_port_selected = SRIF.get_ports()[port_idx].getDescriptivePortName().equals(SRIF.port_name());
            set_connect_btn_icon(!opening_port_selected||!SRIF.is_opened());
            int baudrate_idx = java.util.Arrays.binarySearch(baudrate_candidates, SRIF.get_ports()[port_idx].getBaudRate());
            System.out.println(baudrate_idx);
            if (baudrate_idx >= 0 && baudrate_idx < baudrate_candidates.length){
                System.out.println("changed selected item");
                baudrate_combo_box.setSelectedItem(baudrate_idx);
                baudrate_combo_box.setSelectedIndex(baudrate_idx);
            }
        }
    };

    ActionListener baudrate_select_btn_listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("set port baudrate");
            SerialInterface SRIF = new SerialInterface();
            if(SRIF.is_opened()) {
                SRIF.set_port_baudrate(baudrate_candidates[baudrate_combo_box.getSelectedIndex()]);
            }
        }
    };
}
