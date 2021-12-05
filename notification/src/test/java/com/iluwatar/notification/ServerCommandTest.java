package com.iluwatar.notification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerCommandTest {
    // test submit
    @Test
    void testServerCommand() {

        DataTransferObject data = new DataTransferObject();
        ServerCommand server = new ServerCommand(data);
        NotificationError notificationError = new NotificationError("error");
        Notification notification = data.getNotification();
        notification.setErrors(notificationError);
        NotificationError output = server.getNotification().getErrors().get(0);
        assertEquals(notificationError, output);
    }
}