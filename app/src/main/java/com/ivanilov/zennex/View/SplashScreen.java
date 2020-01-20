package com.ivanilov.zennex.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Класс для создания Спэшл бара при загрузки приложения, у него нет layout, у наго есть только стиль и изображение из drawable
 * @autor Герман Иванилов
 * @version 1.0
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
