package com.example.VladaPaint;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void movePage(View view)
    {
        Intent mP = new Intent(this, PaintPage.class);
        startActivity(mP);
    }
}