package com.imobile3.groovypayments.ui.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.imobile3.groovypayments.R;
import com.imobile3.groovypayments.data.entities.UserEntity;
import com.imobile3.groovypayments.ui.BaseActivity;

public class UserProfileActivity extends BaseActivity {

    @NonNull
    private final UserEntity User = new UserEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        userProfile();
        setUpMainNavBar();
        setUpViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void setUpMainNavBar() {
        super.setUpMainNavBar();
        mMainNavBar.showBackButton();
        mMainNavBar.showLogo();
        mMainNavBar.showSubtitle(getString(R.string.user_profile_subtitle_format,
                User.getFirstName()));
    }

    @Override
    protected void initViewModel() {
        // No view model needed.
    }

    //Get the saved data from Login activity
    private void userProfile() {
        SharedPreferences sharedpreferences = getSharedPreferences("UserName",
                Context.MODE_PRIVATE);
        String email = sharedpreferences.getString("name", "");
        String nameOnly = email.substring(0,email.indexOf('@'));
        User.setId(100L);
        User.setUsername(nameOnly);
        User.setEmail(email);
        User.setFirstName(nameOnly);
    }

    private void setUpViews() {
        TextView lblUsername = findViewById(R.id.label_username);
        TextView lblEmail = findViewById(R.id.label_email);
        TextView lblHoursWeek = findViewById(R.id.label_hours_week);

        lblUsername.setText(User.getUsername());
        lblEmail.setText(User.getEmail());
        lblHoursWeek.setText(String.valueOf(User.getId()));
    }
}
