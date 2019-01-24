package restaurant.misc;

import restaurant.exception.SessionNotSet;
import restaurant.model.Client;

public class Session {

    private static Client client;

    public static void setSession(Client sessionClient) {
        client = sessionClient;
    }

    public static Client getClient() throws SessionNotSet {
        if (client == null) {
            throw new SessionNotSet();
        }
        return client;
    }

    public static void logOut() {
        client = null;
    }
}
