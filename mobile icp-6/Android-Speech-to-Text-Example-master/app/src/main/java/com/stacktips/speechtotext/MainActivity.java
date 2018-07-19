

package com.stacktips.speechtotext;

        import android.content.ActivityNotFoundException;
        import android.content.Intent;
        import android.os.Bundle;
        import android.content.SharedPreferences;
        import android.speech.RecognizerIntent;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Html;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.speech.tts.TextToSpeech;
        import java.util.ArrayList;
        import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    TextToSpeechClass TTS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TTS = new TextToSpeechClass();
        TTS.init(this);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        TTS.Speak("Hello");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);





        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {

                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                mVoiceInputTv.append(Html.fromHtml("<font color=#FFA500>"+result.get(0)+"</font>" +"<br>"));


                if(result.get(0).contains("thank you")|| result.get(0).contains("u") || result.get(0).contains("you")){
                    TTS.Speak("Thank you too.  Rohit  Take care");
                    mVoiceInputTv.append(Html.fromHtml("<font color=green>Thank you too.  Rohit  Take care</font><br>"));
                }
                if(result.get(0).contains("medicine")){

                    TTS.Speak("I think you have fever. Please take this medicine");

                    mVoiceInputTv.append(Html.fromHtml("<font color=green>I think you have fever. Please take this medicine</font><br>"));
                }
                if(result.get(0).contains("hello")){
                    TTS.Speak("What is your name");
                    mVoiceInputTv.append(Html.fromHtml("<font color=green>What is your name</font><br>"));
                }
                if(result.get(0).contains("not feeling")|| result.get(0).contains("not well")){
                    TTS.Speak("I can understand. Please tell your symptoms in short");
                    mVoiceInputTv.append(Html.fromHtml("<font color=green>I can understand. Please tell your symptoms in short</font><br>"));

                }
                if(result.get(0).contains("David")){
                    TTS.Speak("Hello David. I am your personal assistant"+"\n\n");
                    mVoiceInputTv.append(Html.fromHtml("<font color=green>Hello David. I am your personal assistant</font><br>"));
                }




            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TTS.shutDown();
    }
}