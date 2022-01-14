package com.fatihkoc.featuredKenny;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.fatihkoc.featuredKenny.databinding.ActivityScoreBoardBinding;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {
    private ActivityScoreBoardBinding binding;
    SQLiteDatabase database;
    ArrayList<gamer> gamerArrayList;
    gamerAdapter gamerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScoreBoardBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        database=this.openOrCreateDatabase("Tables",MODE_PRIVATE,null);
        gamerArrayList=new ArrayList<>();
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        gamerAdapter=new gamerAdapter(gamerArrayList);
        binding.recyclerView2.setAdapter(gamerAdapter);
        try {

            Cursor cursor=database.rawQuery("SELECT * FROM Tables",null);
            int idIx=cursor.getColumnIndex("id");
            int nameIx=cursor.getColumnIndex("name");
            int timeIx=cursor.getColumnIndex("time");
            int difficultyIx=cursor.getColumnIndex("difficulty");
            int skorIx=cursor.getColumnIndex("skor");
            while (cursor.moveToNext()){
                int id=cursor.getInt(idIx);
                String isim=cursor.getString(nameIx);
                int time=cursor.getInt(timeIx);
                String difficulty=cursor.getString(difficultyIx);
                int skor=cursor.getInt(skorIx);
                gamer gamer1=new gamer(id,time,skor,isim,difficulty);
                gamerArrayList.add(gamer1);
                System.out.println("dldldldlddlld" +id+isim+time+difficulty+skor);
            }
            cursor.close();
            gamerAdapter.notifyDataSetChanged();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}