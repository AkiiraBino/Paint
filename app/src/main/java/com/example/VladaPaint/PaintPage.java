package com.example.VladaPaint;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.app.Dialog;

public class PaintPage extends AppCompatActivity{
    private PaintPageDraw paintPageDraw;
    private float smallBrush, mediumBrush, largeBrush;
    Dialog brushDialog;
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
        closeDialog();
    }

    public void changeBrushMedium(View view)
    {
        paintPageDraw.setBrushSize(mediumBrush);
        closeDialog();
    }

    public void changeBrushLarge(View view)
    {
        paintPageDraw.setBrushSize(largeBrush);
        closeDialog();
    }

    public void newList(View view)
    {
        paintPageDraw.newList(view);
        closeDialog();
    }

    public void closeDialog()
    {
        brushDialog.dismiss();
    }
}