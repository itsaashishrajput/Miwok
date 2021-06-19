package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One" , "lutti",R.drawable.number_one, R.raw.number_one));
       // words.add("Two");
        words.add(new Word("Two" , "otiika ", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three" , "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four" , "oyyissa",R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five" , "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six" , "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven" , "kenekaku", R.drawable.number_seven , R.raw.number_seven));
        words.add(new Word("Eight" , "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine" , "wo'e", R.drawable.number_nine , R.raw.number_nine));
        words.add(new Word("Ten" , "na' aacha", R.drawable.number_ten, R.raw.number_ten));


       /* LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        int count = 0;
        while(count < words.size()){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(count));
            rootView.addView(wordView);
            count++;*/
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });

        }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
            if (mMediaPlayer != null){
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

        }
    }

