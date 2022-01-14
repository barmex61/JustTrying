package com.fatihkoc.featuredKenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.fatihkoc.featuredKenny.databinding.ActivityGameBinding;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
        private ActivityGameBinding binding;
        SQLiteDatabase database;
        Handler handler;
        Runnable runnable;
        int skor,time,x;
        String difficulty,isim;
        ImageView images[];
        Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGameBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        images=new ImageView[]{binding.imageView12,binding.imageView13,binding.imageView14,binding.imageView15,binding.imageView16,binding.imageView17,binding.imageView18,binding.imageView19,binding.imageView20,binding.imageView21,binding.imageView22,binding.imageView23};
        database=this.openOrCreateDatabase("Tables",MODE_PRIVATE,null);
        Intent intent=getIntent();
        time=intent.getIntExtra("sure",0);
        isim=intent.getStringExtra("isim");
        difficulty=intent.getStringExtra("zorluk");
        binding.gameTimeText.setText("Time : "+ time);
        skor=0;
        int sayac=time*1000;
        hideImages();

        new CountDownTimer(sayac, 1000) {
            @Override
            public void onTick(long l) {

                binding.gameTimeText.setText("Time : " +l/1000);
            }

            @Override
            public void onFinish() {
                setDatabase();
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert=new AlertDialog.Builder(GameActivity.this);
                alert.setTitle("Oyun Bitti Keke.");
                alert.setMessage("Bi Tur Daha Atak Mı ?");
                alert.setPositiveButton("Evet Evet Hemen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            startActivity(intent);
                    }
                });
                alert.setNegativeButton("Yok Biraderim Kalsın", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }

                }).show();
            }
        }.start();
    }

    public void hideImages(){
        if (difficulty.equals("easy")){
            x=700;
        } else if(difficulty.equals("medium")){
            x=500;
        } else if(difficulty.equals("hard")){
            x=300;
        }

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView imageView:images) {
                    imageView.setVisibility(View.INVISIBLE);
                }
                    r=new Random();
                    int a=r.nextInt(11);
                    images[a].setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable,x);

            }
        };
        handler.post(runnable);
    }
    public void skorArtir(View view){
        skor++;
        binding.skorText.setText("Skor : " + skor);
    }
    public void setDatabase(){
        try {
            String zaman=String.valueOf(time);
            String score=String.valueOf(skor);
            database.execSQL("CREATE TABLE IF NOT EXISTS Tables(id INTEGER PRIMARY KEY,name VARCHAR, time VARCHAR, difficulty VARCHAR,skor VARCHAR)");
            String sql="INSERT INTO Tables(name,time,difficulty,skor) VALUES (?,?,?,?)";
            SQLiteStatement sqLiteStatement=database.compileStatement(sql);
            sqLiteStatement.bindString(1,isim);
            sqLiteStatement.bindString(2,zaman);
            sqLiteStatement.bindString(3,difficulty);
            sqLiteStatement.bindString(4,score);
            sqLiteStatement.execute();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}