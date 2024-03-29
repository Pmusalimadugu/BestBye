package com.apollo8.bestbye;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private static final String TAG = "MainActivity";

    //vars
    private static String[] assetFiles;
    private static String[] localFiles;
    private static JSONObject[] JSONS;
    private static String[] names;
    private static String[] expDates;
    private static String[] imgs;
    private static Double[] scnDate;
    private static String[] barcodes;
    private static String[] JSONString;




    public String readJSONAssets(Context context, String location) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(location);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;



    }

    public static String readJSON(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    public boolean createJSON(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public boolean isJSONPresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

    public static void deleteFile(Context context, int index) {
        Log.e("DELETE", "" + index);
        context.deleteFile("sample" + index + ".json");
        updateView(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //mystuff

        /**

        Log.d("ASDFASDFASDFADFASDF","this is a test");
        String data = readJSONAssets(getApplicationContext(), "sample.json");
        Log.e("ASDADSASDADSASD", data);

        createJSON(getApplicationContext(), "sample.json", data);
        String data2 = readJSON(getApplicationContext(), "sample.json");
        Log.e("THISISATESTPOGPOGPOG", data2);

        **/



        assetFiles = new String[0];

        try {
            assetFiles = getAssets().list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
         for (int i = 1; i < assetFiles.length-1; i++) {
            Log.e("POGPOGPOGPOG", assetFiles[i]);
            createJSON(getApplicationContext(), assetFiles[i], readJSONAssets(getApplicationContext(), assetFiles[i]));

         }


        /**
         localFiles = getFilesDir().list();
         JSONString = new String[localFiles.length];
          JSONS = new JSONObject[localFiles.length];
          names = new String[localFiles.length];
          expDates = new String[localFiles.length];
          imgs = new String[localFiles.length];
          scnDate = new Double[localFiles.length];
          barcodes = new String[localFiles.length];

         for (int i = 0; i < localFiles.length; i++) {
             Log.e("KYSKYSKYSKYS", localFiles[i]);
             JSONString[i] = readJSON(getApplicationContext(), localFiles[i]);
         }

        for (int i = 0; i < localFiles.length; i++) {
            try {
                JSONS[i] = new JSONObject(JSONString[i]);
            }catch (JSONException err){
                Log.d("Error", err.toString());
            }
        }

        for (int i = 0; i < localFiles.length; i++) {
            try {

                JSONObject temp = (JSONObject)JSONS[i].getJSONArray("products").get(0);


                imgs[i] = temp.getString("images");
                names[i] = temp.getString("product_name");
                scnDate[i] = JSONS[i].getDouble("scan_date");
                barcodes[i] = temp.getString("barcode_number");


                imgs[i] = imgs[i].replace("\\", "");
                imgs[i] = imgs[i].replace("[", "");
                imgs[i] = imgs[i].replace("]", "");


                Log.d("IMAGES", imgs[i]);
                Log.d("NAMES", names[i]);
                Log.d("BARCODES", barcodes[i]);
                Log.d("DATES", scnDate[i].toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        **/

        getApplicationContext().deleteFile("sample.json");
        updateView(getApplicationContext());




    }

    private static void updateView(Context context) {

        localFiles = context.getFilesDir().list();
        JSONString = new String[localFiles.length];
        JSONS = new JSONObject[localFiles.length];
        names = new String[localFiles.length];
        expDates = new String[localFiles.length];
        imgs = new String[localFiles.length];
        scnDate = new Double[localFiles.length];
        barcodes = new String[localFiles.length];

        for (int i = 0; i < localFiles.length; i++) {
            Log.e("KYSKYSKYSKYS", localFiles[i]);
            JSONString[i] = readJSON(context, localFiles[i]);
        }

        for (int i = 0; i < localFiles.length; i++) {
            try {
                JSONS[i] = new JSONObject(JSONString[i]);
            }catch (JSONException err){
                Log.d("Error", err.toString());
            }
        }

        for (int i = 0; i < localFiles.length; i++) {
            try {

                JSONObject temp = (JSONObject)JSONS[i].getJSONArray("products").get(0);


                imgs[i] = temp.getString("images");
                names[i] = temp.getString("product_name");
                scnDate[i] = JSONS[i].getDouble("scan_date");
                barcodes[i] = temp.getString("barcode_number");


                imgs[i] = imgs[i].replace("\\", "");
                imgs[i] = imgs[i].replace("[", "");
                imgs[i] = imgs[i].replace("]", "");


                Log.d("IMAGES", imgs[i]);
                Log.d("NAMES", names[i]);
                Log.d("BARCODES", barcodes[i]);
                Log.d("DATES", scnDate[i].toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] getFiles() {
        return localFiles;
    }

    public static String[] getImgs() {
        return imgs;
    }

    public static String[] getNames() {
        return names;
    }

    public static String[] getBarcodes() {
        return barcodes;
    }

    public static Double[] getScnDate() { return scnDate; }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
