
class TerminalPanelDelegate {
    init {

    }

    fun reset_UI() {
        SerialInterface.send("help","\r\n")
    }



}