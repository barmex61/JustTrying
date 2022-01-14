package com.fatihkoc.featuredKenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatihkoc.featuredKenny.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    String difficulty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);



    }
    public void easy(View view){
        if(binding.nameText.getText().toString().equals("") || binding.timeText.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Lütfen İsim ve Süre Bilgilerini Eksiksiz Giriniz", Toast.LENGTH_LONG).show();
        }
        else {
            difficulty="easy";
            setExtras();

        }

    }
    public void medium(View view){
        if(binding.nameText.getText().toString().equals("") || binding.timeText.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Lütfen İsim ve Süre Bilgilerini Eksiksiz Giriniz", Toast.LENGTH_LONG).show();
        }
        else{
            difficulty="medium";
            setExtras();
        }

    }
    public void hard(View view){
        if(binding.nameText.getText().toString().equals("") || binding.timeText.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Lütfen İsim ve Süre Bilgilerini Eksiksiz Giriniz", Toast.LENGTH_LONG).show();
        }
        else{
            difficulty="hard";
            setExtras();
        }

    }
    public void showScores(View view){
            Intent intent=new Intent(MainActivity.this,ScoreBoard.class);
            startActivity(intent);
    }
    public void setExtras(){
        Intent intent=new Intent(MainActivity.this,GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sure",Integer.parseInt(binding.timeText.getText().toString()));
        intent.putExtra("isim",binding.nameText.getText().toString());
        intent.putExtra("zorluk",difficulty);
        startActivity(intent);
    }


}