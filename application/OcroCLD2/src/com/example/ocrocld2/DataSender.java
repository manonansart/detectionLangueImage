package com.example.ocrocld2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class DataSender extends AsyncTask<Object, Integer, String> {
	private final String url = "http://asi-android.insa-rouen.fr/traitement.php";
	private final String boundary="*****";
	private final String tirets = "--";
	private final String finLignes = "\r\n";
	private final String filename = "userfile";
	private TextView text = null;
	private String resultat;
	
	@Override
	protected String doInBackground(Object... params) {
		try {
			File image = (File)params[0];
			text = (TextView)params[1];
			
			FileInputStream fileInputStream = new FileInputStream(image);
			URL myUrl = new URL(url);
			HttpURLConnection connexion = (HttpURLConnection)myUrl.openConnection();
			DataOutputStream dos = null;
			connexion.setDoInput(true);
			connexion.setDoOutput(true);
			connexion.setUseCaches(false);
			connexion.setRequestMethod("POST");
			connexion.setRequestProperty("Connection", "Keep-Alive");
			connexion.setRequestProperty("ENCTYPE", "multipart/form-data");
			connexion.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			connexion.setRequestProperty("uploaded_file", filename);
			dos = new DataOutputStream(connexion.getOutputStream());
			dos.writeBytes(tirets + boundary + finLignes);
			dos.writeBytes("Content-Disposition: form-data; name="+filename+";filename=\""+filename+"\""+finLignes);
			dos.writeBytes(finLignes);
			int bytesAvailable = fileInputStream.available();
			int bufferSize = bytesAvailable;
			byte[] buffer = new byte[bufferSize];
			int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			while(bytesRead > 0) {
				dos.write(buffer,0,bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = bytesAvailable;
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
			
			dos.writeBytes(finLignes);
			dos.writeBytes(tirets+boundary+tirets+finLignes);
			dos.close();
			DataInputStream dis = new DataInputStream(connexion.getInputStream());
			String in = "";
			for( int c= dis.read();c!=-1 ;c=dis.read())
			{
				in +=(char)c;
			}
			dis.close();
			Log.i("paoAndroid",in);
			resultat = in;
			int serverResponseCode = connexion.getResponseCode();
			String serverResponseMessage = connexion.getResponseMessage();
			
			Log.i("paoAndroid", "response : "+serverResponseCode + " " +serverResponseMessage);
			fileInputStream.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	protected void onPostExecute(String fileURL){
		text.setText(resultat);
	}
}
