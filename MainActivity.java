package lav.pjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

   /* String url = "{\n" +
            "   \"sys\":\n" +
            "   {\n" +
            "      \"country\":\"GB\",\n" +
            "      \"sunrise\":1381107633,\n" +
            "      \"sunset\":1381149604\n" +
            "   },\n" +
            "   \"weather\":[\n" +
            "      {\n" +
            "         \"id\":711,\n" +
            "         \"main\":\"Smoke\",\n" +
            "         \"description\":\"smoke\",\n" +
            "         \"icon\":\"50n\"\n" +
            "      }\n" +
            "   ],\n" +
            "\t\n" +
            "  \"main\":\n" +
            "   {\n" +
            "      \"temp\":304.15,\n" +
            "      \"pressure\":1009,\n" +
            "   }\n" +
            "}";*/

   String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("RES", response.toString());
            }
        },  new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOGIN ERROR", String.valueOf(error));

                }
        }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Content-Type", "application/json");

                    return map;
                }
            };
        obj.setRetryPolicy(new DefaultRetryPolicy(
                40000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(obj, "tag");
        }
}
