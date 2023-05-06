public interface serial_callback {
    /**
     * will be called every time the data received.
     */
    public void process_callback(String rx_string);
}
