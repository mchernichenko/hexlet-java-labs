package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error: Connection already connected.");
    }

    @Override
    public void disconnect() {
        connection.setState(connection.getDiconnected());
        System.out.println("Connection is connected.");
    }

    @Override
    public void write(String dataStr) {
        System.out.println("Данные добавления в буфер.");
    }
}
// END
