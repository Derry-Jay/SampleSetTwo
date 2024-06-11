package com.set.two.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        StringBuilder str = new StringBuilder();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            assert pdus != null;
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], "3gpp2");
                str = str.append("SMS from ").append(msgs[i].getOriginatingAddress()).append(": ").append(msgs[i].getMessageBody()).append("\n");
            }
        }
    }
}