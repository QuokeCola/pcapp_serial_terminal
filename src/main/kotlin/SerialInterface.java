import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SerialInterface implements SerialPortMessageListener{
    private static SerialPort port           = null;   // The serial port
    public static List<serial_callback> callbacks = new ArrayList<>();
    public static String delimiter = "\r\n";

    private static boolean is_opened = false;

    public boolean is_opened() {
        return is_opened;
    }

    /**
     * @return Get current port's descriptive port name.
     */
    public String port_name() {
        try{
            return port.getDescriptivePortName();
        } catch (Exception ignored) {};
        return null;
    }

    SerialInterface() {
        System.setProperty("fazecast.jSerialComm.appid", "QuokeCola.Serial-Terminal");
    }

    /**
     * Get the available serial ports through jSerialComm
     */
    SerialPort[] get_ports() {
        return SerialPort.getCommPorts();
    }

    void set_port_baudrate(int baudrate) {
        port.setBaudRate(baudrate);
    }

    int get_port_baudrate() {
       return port.getBaudRate();
    }

    /**
     * Set the serial port for this serial interface and open it.
     * @param port_ Available port that obtained from <SerialInterface.get_ports()> method
     */
    void open_port(SerialPort port_) {
        try{ // if port is not null.
            stop_receive();
        } catch (Exception ignored) { // Initially, port is not set yet so there will be a null error
        }
        // Set port.
        port = port_;
        port.allowElevatedPermissionsRequest();
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 1000, 0);
        port.addDataListener(this);
        is_opened = true;
    }

    /**
     * Stop receiving data.
     */
    void stop_receive() {
        if (port.isOpen()) {
            port.closePort();
            port.removeDataListener();
        }
        is_opened = false;
    }

    static void send(String string, String delimiter) throws IOException {
        byte[] out = (string + delimiter).getBytes();
        port.getOutputStream().write(out);
    }

    @Override
    public byte[] getMessageDelimiter() {
        return delimiter.getBytes();
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return false;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] rx_byte = event.getReceivedData();
        String rx_str  = new String(rx_byte, StandardCharsets.ISO_8859_1);
        rx_str = rx_str.strip();
        if (!rx_str.equals("")) {
            for (serial_callback callback:
                    callbacks) {
                try {
                    callback.process_callback(rx_str.strip());
                } catch (Exception ignored) {}
            }
        }
    }
}

