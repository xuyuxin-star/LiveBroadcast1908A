package com.anfly.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BigflyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TAG", "BigflyReceiver通过动态注册广播收到普通广播");
        Intent intent1 = new Intent(context, MainTwoActivity.class);
        context.startActivity(intent1);
    }
}
