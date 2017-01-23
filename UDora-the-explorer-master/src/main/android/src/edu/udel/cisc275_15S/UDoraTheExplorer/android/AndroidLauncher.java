package edu.udel.cisc275_15S.UDoraTheExplorer.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new UDoraTheExplorer(), config);
	}
}
