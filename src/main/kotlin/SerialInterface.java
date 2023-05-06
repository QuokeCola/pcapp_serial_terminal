import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SerialInterface implements SerialPortMessageListener{
    private static final int RX_LINE_SIZE    = 1000;   // Maximum number of lines could store.
    private static final int BUFFER_STR_SIZE = 200;    // Maximum number of chars each line could store.
    private static SerialPort port           = null;   // The serial port
    public static List<serial_callback> callbacks = new ArrayList<>();
    public static String delimiter = "\n";

    public static List<String> rx_lines = new ArrayList<String>();

    SerialInterface() {
        System.setProperty("fazecast.jSerialComm.appid", "QuokeCola.Serial-Terminal");
    }

    /**
     * Get the available serial ports through jSerialComm
     */
    SerialPort[] get_ports() {
        return SerialPort.getCommPorts();
    }

    /**
     * Set the serial port for this serial interface
     * @param port_ Available port that obtained from <SerialInterface.get_ports()> method
     */
    void set_port(SerialPort port_) {
        port = port_;
    }

    /**
     * Start the data receive thread.
     */
    void start_receive() {
        port.allowElevatedPermissionsRequest();
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 1000, 0);
        port.addDataListener(this);
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
            rx_lines.add(rx_str.strip());
            for (serial_callback callback:
                    callbacks) {
                try {
                    callback.process_callback(rx_str.strip());
                } catch (NoSuchMethodError ignored) {}
            }
        }
    }
}

