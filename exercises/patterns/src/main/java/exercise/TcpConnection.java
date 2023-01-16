package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    // все возможные состояния объекта
    private final Connection connected;
    private final Connection diconnected;

    private Connection state; // текущее состояние объекта
    private String ipAddress;
    private int port;

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.connected = new Connected(this);
        this.diconnected = new Disconnected(this);
        this.state = diconnected;
    }

    public Connection getState() {
        return state;
    }

    public void setState(Connection state) {
        this.state = state;
    }

    public Connection getConnected() {
        return connected;
    }

    public Connection getDiconnected() {
        return diconnected;
    }

    // действия объекта и делегирование их объекту текущего состояния
    public String getCurrentState() {
        return state.getCurrentState();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String dataStr) {
        state.write(dataStr);
    }

}
// END
