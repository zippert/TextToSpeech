package com.tedkomp.TTS;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TTSTest extends Activity implements OnClickListener {
	private TextToSpeech tts;
	private Button button;
	private EditText textField;
	private Spinner spinner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.Button01);
		textField = (EditText) findViewById(R.id.EditText01);
		spinner = (Spinner) findViewById(R.id.Spinner01);
		button.setOnClickListener(this);

		String[] array = new String[] { getString(R.string.eng),
				getString(R.string.fre), getString(R.string.ger),
				getString(R.string.ita) };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, array);
		spinner.setAdapter(adapter);
	}

	@Override
	public void onClick(View arg0) {
		Intent i = new Intent();
		i.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(i, 0);
		tts = new TextToSpeech(this, new OnInitListener() {

			@Override
			public void onInit(int status) {
				tts.setLanguage(getLocale());
				tts.speak(textField.getText().toString(),
						TextToSpeech.QUEUE_ADD, null);
			}
		});

	}

	private Locale getLocale() {
		Locale l = null;
		String selection = (String)spinner.getSelectedItem();
		if(selection.equals(getString(R.string.eng))){
			return Locale.US;
		} else if(selection.equals(getString(R.string.fre))){
			return Locale.FRENCH;
		} else if(selection.equals(getString(R.string.ger))){
			return Locale.GERMAN;
		} else if(selection.equals(getString(R.string.ita))){
			return Locale.ITALIAN;
		} 
		return l;
	}
}