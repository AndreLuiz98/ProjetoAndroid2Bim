package pacoteService;

import java.io.IOException;

import com.android.example.R;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class streamMusica extends Activity {

	static MediaPlayer mPlayer;
	Button buttonPlay;
	Button buttonStop;
	String url = ""; // Rhavy nessa parte eu iria inserir o ling do servidor com a musica, 
					//porém eu nao encontrei um servidor on, mas aqui eu consegui rodar a musica 
					//coloquei o arquivo .mp3 dentro do sdcard virtual do meu avd.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buttonPlay = (Button) findViewById(R.id.play);
		buttonPlay.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mPlayer = new MediaPlayer();
				mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				try {
					mPlayer.setDataSource(url);
				} catch (IllegalArgumentException e) {
					Toast.makeText(getApplicationContext(), "Você não pode definir o URI corretamente!", Toast.LENGTH_LONG).show();
				} catch (SecurityException e) {
					Toast.makeText(getApplicationContext(), "Você não pode definir o URI corretamente!", Toast.LENGTH_LONG).show();
				} catch (IllegalStateException e) {
					Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					mPlayer.prepare();
				} catch (IllegalStateException e) {
					Toast.makeText(getApplicationContext(), "Você não pode definir o URI corretamente!", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), "Você não pode definir o URI corretamente!", Toast.LENGTH_LONG).show();
				}
				mPlayer.start();
			}
		});

		buttonStop = (Button) findViewById(R.id.stop);
		buttonStop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				if(mPlayer!=null && mPlayer.isPlaying()){
					mPlayer.stop();
				}
			}
		});
	}

	protected void onDestroy() {
		super.onDestroy();
		
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

}
