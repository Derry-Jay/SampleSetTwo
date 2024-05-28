package com.set.two.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;

import java.util.Calendar;

public class Methods {
    private final View v;

    public Methods(View view) {
        this.v = view;
    }

    public HandleXML xmlToContent(String uri) {
        HandleXML obj = new HandleXML(uri);
        obj.fetchXML();
        System.out.println(obj.getTitle());
        System.out.println(obj.getLink());
        System.out.println(obj.getDescription());
        return obj;
    }

    public void sendSMS(String phNo, String msg) {
        try {
            PendingIntent pi = PendingIntent.getActivity(v.getContext(), 0, new Intent(v.getContext(), v.getClass()), PendingIntent.FLAG_IMMUTABLE);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phNo, null, msg, pi, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAlarm(Calendar target) {
        try {
            Context ctx = v.getContext();
            Intent intent = new Intent(ctx, AlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(ctx, 1, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, target.getTimeInMillis(), pi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openTimePicker(boolean is24r, String title) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog tpd = new TimePickerDialog(v.getContext(), (view, hourOfDay, minute) -> {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);
            if (calSet.compareTo(calNow) <= 0) {
                calSet.add(Calendar.DATE, 1);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24r);
        tpd.setTitle(title);
        tpd.show();
    }
}