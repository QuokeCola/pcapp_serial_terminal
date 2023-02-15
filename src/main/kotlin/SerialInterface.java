import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerialInterface implements SerialPortMessageListener{
    private static final int RX_LINE_SIZE    = 1000;   // Maximum number of lines could store.
    private static final int BUFFER_STR_SIZE = 200;    // Maximum number of chars each line could store.
    private static SerialPort port           = null;   // The serial port
    public static String delimiter = "\r\n";

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

    static void send(String string) {
        byte[] out = (string + "\r\n").getBytes();
        System.out.print(Arrays.toString(out));
        port.writeBytes(out, out.length);
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
        rx_lines.add(rx_str);
    }
}
