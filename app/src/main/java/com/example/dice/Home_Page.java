package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class Home_Page extends AppCompatActivity {

    ImageView one_img, two_img;
    TextView total_txt;
    Button reset_btn;
    TextToSpeech textToSpeech;

    int[] image1 = {R.drawable.p_one, R.drawable.p_two, R.drawable.p_three, R.drawable.p_four, R.drawable.p_five, R.drawable.p_six};
    int[] image2 = {R.drawable.b_one, R.drawable.b_two, R.drawable.b_three, R.drawable.b_four, R.drawable.b_five, R.drawable.b_six};
    int i1 = 0;
    int i2 = 0;

    int sound1, sound2, sound3, sound4, sound5, sound6;
    int sound_shake, sound_dice, number_dice;

    double z;

    public static final Random RANDOM = new Random();
    SoundPool dice_sounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        binding();

        sound1 = dice_sounds.load(this, R.raw.sound, 1);


        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random ran = new Random();
                int a = ran.nextInt(image1.length);
                Random ran1 = new Random();
                int b = ran1.nextInt(image2.length);
                one_img.setImageResource(image1[a]);
                two_img.setImageResource(image2[b]);
                int z = Integer.parseInt(String.valueOf(a));
                int s = Integer.parseInt(String.valueOf(b));
                Vibrator vibe = (Vibrator) getSystemService(Home_Page.VIBRATOR_SERVICE);
                vibe.vibrate(50);
                int x = (z + 1) + (s + 1);
                total_txt.setText("" + x);

                textToSpeech.speak(total_txt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

                switch (z) {
                    case 1:
                        one_img.setImageResource(R.drawable.p_one);
                        number_dice = R.drawable.p_one;

                        sound_dice = sound1;
                        break;
                    case 2:
                        one_img.setImageResource(R.drawable.p_two);
                        number_dice = R.drawable.p_two;
                        sound_dice = sound1;
                        break;
                    case 3:
                        one_img.setImageResource(R.drawable.p_three);
                        number_dice = R.drawable.p_three;

                        sound_dice = sound1;
                        break;
                    case 4:
                        one_img.setImageResource(R.drawable.p_four);
                        number_dice = R.drawable.p_four;

                        sound_dice = sound1;
                        break;
                    case 5:
                        one_img.setImageResource(R.drawable.p_five);
                        number_dice = R.drawable.p_five;

                        sound_dice = sound1;
                        break;
                    case 6:
                        one_img.setImageResource(R.drawable.p_six);
                        number_dice = R.drawable.p_six;

                        sound_dice = sound1;
                        break;
                    default:
                }

                final Animation anim1 = AnimationUtils.loadAnimation(Home_Page.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(Home_Page.this, R.anim.shake);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        dice_sounds.play(sound_dice, 1.0f, 1.0f, 0, 0, 1.0f);
                    }



                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value = randomDiceValue();
                        int res = getResources().getIdentifier("dice_" + value, "drawable", "com.ssaurel.dicer");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                one_img.startAnimation(anim1);
                two_img.startAnimation(anim2);
                // dice_sounds.play(sound_shake, 1.0f, 1.0f, 0, 0, 1.0f);


            }

        });


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

    }

    private void binding() {

        one_img = findViewById(R.id.one_img);
        two_img = findViewById(R.id.two_img);
        reset_btn = findViewById(R.id.reset_btn);
        total_txt = findViewById(R.id.total_txt);

    }




    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}
