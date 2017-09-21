package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Cart;
import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Product;
import com.github.pushmyshop.pushmyshopapi.models.PushSubscription;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/** temporary class to test webpush using VAPID **/
@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/compagnies/{compagnyId}/webpush")
public class PushController {

    @Value("${web.push.vapid.public-key}")
    private String vapidPublicKey;
    @Value("${web.push.vapid.private-key}")
    private String vapidPrivateKey;

    private List<PushSubscription> activeSubscription = new ArrayList<>();

    @PostMapping
    public String subscribe(@RequestBody PushSubscription subscription) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
        activeSubscription.add(subscription);

        sendPushMessage(subscription, "my Fist push !!!".getBytes());
        return "Web push subscribed";
    }


    private static final int TTL = 255;

    public void sendPushMessage(PushSubscription sub, byte[] payload) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

            // Create a notification with the endpoint, userPublicKey from the subscription and a custom payload
        Notification notification = new Notification(
                    sub.getEndpoint(),
                    sub.getPublicKey(),
                    sub.getAuth(),
                    payload
            );

            // Instantiate the push service, no need to use an API key for Push API
        PushService  pushService = new PushService();
        pushService.setPrivateKey(Utils.loadPrivateKey(this.vapidPrivateKey));
        pushService.setPublicKey(Utils.loadPublicKey(this.vapidPublicKey));

        // Send the notification
        pushService.send(notification);
    }
}


