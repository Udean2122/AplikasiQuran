package com.rifqinaufal.aplikasiquran.Fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rifqinaufal.aplikasiquran.Adapter.AdapterHome;
import com.rifqinaufal.aplikasiquran.Preference;
import com.rifqinaufal.aplikasiquran.R;
import com.rifqinaufal.aplikasiquran.modal.ModalAyatHome;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import static com.rifqinaufal.aplikasiquran.base.Constant.ROOT_AYAT;

public class FragmentHome extends Fragment {
    View view;
    RecyclerView recyclerView;
    AdapterHome adaperHome;
    ArrayList<ModalAyatHome> modalAyatHomes;
    GridLayoutManager glm;
    Context context;
    RequestQueue queue;
    String url = "https://al-quran-8d642.firebaseio.com/data.json?print=pretty";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.recycler_view);
        modalAyatHomes = new ArrayList<>();
        glm = new GridLayoutManager(getContext(), 2);
        adaperHome = new AdapterHome(getActivity(),modalAyatHomes);
        queue = Volley.newRequestQueue(getActivity());
        recyclerView.setLayoutManager(glm);

        makanBang();

        return view;

    }

    private void makanBang(){
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, ROOT_AYAT, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject obj = response.getJSONObject(i);
                        String nama = obj.getString("nama");
                        String nomor = obj.getString("nomor");
                        String ayat = obj.getString("ayat");
                        String asma = obj.getString("asma");

                        ModalAyatHome modalAyatHome = new ModalAyatHome(nama, nomor, ayat, asma);
                        modalAyatHomes.add(modalAyatHome);
                        recyclerView.setAdapter(adaperHome);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(arrayRequest);

    }


}