package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?" , "minto wuksus", R.raw.phrase_where_are_you_going));
        // words.add("Two");
        words.add(new Word("What is your name?" , "tinnə oyaase'nə",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is ..." , "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?" , "michəksəs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I am feeling good" , "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?" , "əənəs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes I am coming." , "həə’ əənəm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming." , "əənəm", R.raw.phrase_im_coming));
        words.add(new Word("Let's go." , "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here." , "ənni'nem",R.raw.phrase_come_here));


       /* LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        int count = 0;
        while(count < words.size()){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(count));
            rootView.addView(wordView);
            count++;*/
        WordAdapter adapter = new WordAdapter(this, words , R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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