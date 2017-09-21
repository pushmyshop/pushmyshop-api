package com.github.pushmyshop.pushmyshopapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class PushSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //endpoint":"https://fcm.googleapis.com/fcm/send/cQYQ-STBkJE:APA91bElMUsKgBFltLLenDuUjzf2lCzGA8u8Glxi5rpibtLXke1V46DbnNRoA9HDMAGjuxj-RGT7vgmSJy4OwjRTS4JTSZeH8dxDT3bPP_gLrXflFsdy2Deh_sg-94D3FMXWjfm8eMJU"
    private String endpoint;
    //"publicKey":"BAIMcNjMs-Bs5ilaV4izvhCOiGePurgRTD1NY9n54QJwzUJXtchKkwD7sdrcGKwhngu3xY-Qze8bxGNB8b5ySLw="
    private String publicKey;
    // "auth":"2-euIhfQvojItU7E2i-HFw=="
    private String auth;

}
