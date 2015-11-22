package com.triestpa.magiclights;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HttpUtil {
    final String TAG = HttpUtil.class.getSimpleName();

    private static HttpUtil instance = null;

    protected static HttpUtil getInstance() {
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    private Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

    protected void changeColor(String newColor) {
        try {
            JSONObject colorJson = new JSONObject();
            colorJson.put("color", newColor);
            new ChangeColorTask().execute(colorJson.toString());
        }
        catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ChangeColorTask extends AsyncTask<String, Void, Integer> {
        final String TAG = ChangeColorTask.class.getSimpleName();
        final String SERVER_ADDRESS = "http://107.170.90.220:3000/";
        final String ENDPOINT = "changeColor";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Integer doInBackground(String[] colorJson) {
            String url = SERVER_ADDRESS + ENDPOINT;
            try {
                Response response = post(url, colorJson[0]);
                return response.code();
            }
            catch (IOException e) {
                Log.e(TAG, "Network Error: " + e.getMessage());
                return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer o) {
            super.onPostExecute(o);
        }

    }

}
