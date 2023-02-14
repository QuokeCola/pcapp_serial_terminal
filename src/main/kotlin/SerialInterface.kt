@file:Suppress("PrivatePropertyName", "PropertyName")

import com.fazecast.jSerialComm.SerialPort
import com.fazecast.jSerialComm.SerialPortEvent

import com.fazecast.jSerialComm.SerialPortMessageListener




/**
 * @author Chen Qian
 * @sample
 * SerialInterface()
 * val port = SerialInterface().get_ports()[0]
 * SerialInterface().set_port(port)
 * SerialInterface().start()
 */
class SerialInterface {
    private val RX_LINE_SIZE: Int = 10000            // Maximum number of lines could store.
    private val BUFFER_STR_SIZE:  Int = 200          // Maximum number of chars each line could store.
    private var port : SerialPort? = null            // The serial port
    private var input_stream  = port?.inputStream    // IO input  stream
    private var output_stream = port?.outputStream   // IO output stream

    private var buffer_string = ""
    var rx_lines = mutableListOf<String>("WELCOME TO SERIAL TERMINAL", "AUTHOR: CHEN QIAN")

    /**
     * Initialize the SerialInterface
     */
    init {
        System.setProperty("fazecast.jSerialComm.appid", "QuokeCola.Serial-Terminal")
    }

    /**
     * Get the available serial ports through jSerialComm
     */
    fun get_ports(): Array<SerialPort> {
        return SerialPort.getCommPorts();
    }

    /**
     * Set the serial port for this serial interface
     * @param port_ Available port that obtained from <SerialInterface.get_ports()> method
     */
    fun set_port(port_: SerialPort) {
        port = port_
    }

    /**
     * Start the data receive thread.
     */
    fun start_receive() {
        port?.openPort()
        port?.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0)
        port?.setBaudRate(115200);
        port?.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 1000, 0);
        port?.addDataListener(MessageListener())
        input_stream = port?.inputStream
        output_stream = port?.outputStream
    }

    fun send(string: String) {
        output_stream?.write(string.toByteArray())
    }

    /**
     * Data receive listener.
     */
    private class MessageListener : SerialPortMessageListener {
        var delimiter = "/r/n"
        fun byteToHex(num: Byte): String {
            val hexDigits = CharArray(2)
            hexDigits[0] = Character.forDigit(num.toInt() shr 4 and 0xF, 16)
            hexDigits[1] = Character.forDigit(num.toInt() and 0xF, 16)
            return String(hexDigits)
        }

        override fun getListeningEvents(): Int {
            return SerialPort.LISTENING_EVENT_DATA_RECEIVED
        }

        override fun serialEvent(event: SerialPortEvent) {
            val byteArray = event.receivedData
            val hexStringBuffer = StringBuffer()
            for (i in byteArray.indices) hexStringBuffer.append(byteToHex(byteArray[i]))
            SerialInterface().rx_lines.add(hexStringBuffer.toString())
            println("Received the following message: $hexStringBuffer")
        }

        override fun getMessageDelimiter(): ByteArray {
            return delimiter.toByteArray()
        }

        override fun delimiterIndicatesEndOfMessage(): Boolean {
            return false
        }
    }

}