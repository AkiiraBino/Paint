package com.example.VladaPaint;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaintPage extends AppCompatActivity{
    private PaintPageDraw paintPageDraw;
    private float smallBrush, mediumBrush, largeBrush;
    Dialog brushDialog;
    Button buttonInput;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_page);
        paintPageDraw = (PaintPageDraw) findViewById(R.id.drawView);
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        brushDialog = new Dialog(this);
        et = (EditText) findViewById(R.id.textHEX);
        buttonInput = (Button) findViewById(R.id.inputButton);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String regex = "[a-fA-F[0-9]]{6}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(et.getText().toString());
                if(matcher.matches())
                {
                    paintPageDraw.setColor("#" + et.getText().toString());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Неверный HEX код", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public void changePaint(View view)
    {
        paintPageDraw.setErase(false);
        String color = view.getTag().toString();
        paintPageDraw.setColor(color);
    }

    public void Click(View view)
    {
        if(view.getId() == R.id.buttonBrush)
        {
            brushDialog.setContentView(R.layout.brush_size_layout);
            brushDialog.show();
            paintPageDraw.setErase(false);
        }

        if(view.getId() == R.id.buttonErase)
        {
            paintPageDraw.setErase(true);
            brushDialog.setTitle("Eraser size");
            brushDialog.setContentView(R.layout.brush_size_layout);
            brushDialog.show();
        }

        if(view.getId() == R.id.buttonNew)
        {
            brushDialog.setContentView(R.layout.newlist);
            brushDialog.show();
        }

    }

    public void changeBrushSmall(View view)
    {
        paintPageDraw.setBrushSize(smallBrush);
        closeDialog(view);
    }

    public void changeBrushMedium(View view)
    {
        paintPageDraw.setBrushSize(mediumBrush);
        closeDialog(view);
    }

    public void changeBrushLarge(View view)
    {
        paintPageDraw.setBrushSize(largeBrush);
        closeDialog(view);
    }

    public void newList(View view)
    {
        paintPageDraw.newList(view);
        closeDialog(view);
    }

    public void closeDialog(View view)
    {
        paintPageDraw.closeDialog(view);
        brushDialog.dismiss();
    }
}