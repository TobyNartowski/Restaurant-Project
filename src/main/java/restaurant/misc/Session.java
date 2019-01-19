package restaurant.misc;

import restaurant.model.Client;

public class Session {

    private static Client client;

    public static void setSession(Client sessionClient) {
        client = sessionClient;
    }

    public static Client getClient() {
        return client;
    }

    public static void logOut() {
        client = null;
    }
}
