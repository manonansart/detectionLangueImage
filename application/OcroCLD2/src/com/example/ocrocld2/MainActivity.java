package com.example.ocrocld2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.*;
import java.io.*;
import android.net.Uri;
import android.os.Environment;
import android.content.Intent;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;

//Notre classe implémente SurfaceHolder.Callback

public class MainActivity extends Activity{
	private File mFichier;
	private static final int PHOTO_RESULT = 0;
	private Button bouton; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    bouton = (Button)findViewById(R.id.bouton);
	    
	    bouton.setOnClickListener(razListener);
    }
	
	private OnClickListener razListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// L'endroit où sera enregistrée la photo
			// Remarquez que mFichier est un attribut de ma classe
			mFichier = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
			// On récupère ensuite l'URI associée au fichier
			Uri fileUri = Uri.fromFile(mFichier);

			// Maintenant, on crée l'intent
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// Et on déclare qu'on veut que l'image soit enregistrée là où pointe l'URI
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

			// Enfin, on lance l'intent pour que l'application de photo se lance
			startActivityForResult(intent, PHOTO_RESULT);
		    }
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // Si on revient de l'activité qu'on avait lancée avec le code PHOTO_RESULT  
	  if (requestCode == PHOTO_RESULT && resultCode == RESULT_OK) {
	    // Si l'image est une miniature
	    if (data != null) {
	      if (data.hasExtra("data")){
	        Bitmap thumbnail = data.getParcelableExtra("data");}
	    } else {
	      // On sait ici que le fichier pointé par mFichier est accessible, on peut donc faire ce qu'on veut avec, par exemple en faire un Bitmap
	      Bitmap image = BitmapFactory.decodeFile("mFichier");
	    }
	    Log.i("paoAndroid", "Envoi de l'image... ");
	    TextView text = (TextView)findViewById(R.id.resultat);
	    text.setText("");
	    DataSender envoi = new DataSender();
	    envoi.execute(mFichier,text);
	  }
	}
}
