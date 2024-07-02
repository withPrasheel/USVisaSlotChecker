import java.io.BufferedReader;

import java.io.File;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.ProtocolException;

import java.net.URL;

class SlotChecker {
    public static void main(String[] args) throws IOException {
        SlotChecker.getSlotResponse();
    }

    public static void PlayBeep(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Can't read the file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getSlotResponse() throws IOException {
        URL urlForGetRequest = new URL("https://app.checkvisaslots.com/slots/v1");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("x-api-key", "8DLCEJ"); // set userId its a sample here
        conection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            // Object obj = new JSONParser().parse(response.toString());
            // JSONObject jo = (JSONObject) obj;
            // JSONArray ja = (JSONArray) jo.get("slotDetails");
            // Iterator itr2 = ja.iterator();
            // while (itr2.hasNext()) 
            // {
            //     Iterator itr1 = ((Map) itr2.next()).entrySet().iterator();
            //     while (itr1.hasNext()) {
            //         Map.Entry pair = itr1.next();
            //         System.out.println(pair.getKey() + " : " + pair.getValue());
            //     }
            // }
            // print result
            System.out.println("JSON String Result " + response.toString());
            PlayBeep("C:/Windows/Media/Alarm01.wav");
            JOptionPane.showMessageDialog(null, "Press OK to stop playing");
            // GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

    }
}