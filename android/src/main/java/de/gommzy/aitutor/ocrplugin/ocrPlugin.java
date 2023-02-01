package de.gommzy.aitutor.ocrplugin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;


import org.json.JSONArray;

@CapacitorPlugin(name = "ocr")
public class ocrPlugin extends Plugin {

    @PluginMethod
    public void scan(PluginCall call) {
        try {
            String image = call.getString("image");

            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
            InputImage inputImage = InputImage.fromBitmap(bitMap,0);
            recognizer.process(inputImage).addOnSuccessListener(visionText -> {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(visionText.getText());
                JSObject ret = new JSObject();
                ret.put("text", jsonArray);
                call.resolve(ret);
            })
            .addOnFailureListener(
            e -> {
                JSONArray jsonArray = new JSONArray();
                JSObject ret = new JSObject();
                ret.put("text", jsonArray);
                call.resolve(ret);
            });
        } catch (Exception e) {
            e.printStackTrace();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("Dieses Gerät untertützt");
            JSObject ret = new JSObject();
            ret.put("text", jsonArray);
            call.resolve(ret);
        }
    }
}

