package com.example.pruebawear;

import static com.example.pruebawear.MainActivity.idNotification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pruebawear.databinding.ActivityCallBinding;

public class CallActivity extends Activity {

    private TextView mTextView;
    private ActivityCallBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.textName;

        NotificationManager notifMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifMan.cancel(idNotification);

        setContentView(R.layout.activity_call);
    }
}