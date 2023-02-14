@file:Suppress("PrivatePropertyName", "PropertyName")

import com.fazecast.jSerialComm.SerialPort


class SerialInterface: Thread() {
    private val BUFFER_SIZE: Int = 10000                  // MAXIMUM LINES THE BUFFER COULD STORE
    private var port : SerialPort? = null            // The serial port
    private var input_stream  = port?.inputStream    // IO input  stream
    private var output_stream = port?.outputStream   // IO output stream

    private var buffer_string = ""
    var buffer_line = mutableListOf<String>("WELCOME TO SERIAL TERMINAL", "AUTHOR: CHEN QIAN")

    /**
     * Initialize the SerialInterface
     */
    init {
        System.setProperty("fazecast.jSerialComm.appid", "ChibiOS-Terminal")
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
    override fun start() {
        port?.openPort()
        port?.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0)
        input_stream = port?.inputStream
        super.start()
    }

    /**
     * Data receive thread.
     */
    override fun run() {
        try {
            // Added buffer string at each iteration
            buffer_string += input_stream?.read()?.toChar()
            // If buffer contains /r or /n, stores the lines into buffer lines and remove the first line.
            if (buffer_string.contains("/r")||buffer_string.contains("/n")){
                buffer_line.add(buffer_string.split("/n")[0].removeSurrounding("/r"))
                buffer_string = buffer_string.split("/n")[1].removeSurrounding("/r")
                if (buffer_line.size > BUFFER_SIZE) {
                    buffer_line.drop(1);
                }
            }

        } catch (e: Exception) {
            // If error occurs
            e.printStackTrace()
            port?.closePort()

        }
    }

}