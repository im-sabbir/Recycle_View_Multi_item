package com.example.recyclepractice2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_View;

    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recycler_View = findViewById(R.id.recycler_View);


        arrayList = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("list_item", "book");
        hashMap.put("book_name", "How to talk to any one");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("list_item", "song");
        hashMap.put("song_name", "Amar vitor o bahir");
        arrayList.add(hashMap);



        adapter adapter = new adapter();
        recycler_View.setAdapter(adapter);

        recycler_View.setLayoutManager(new LinearLayoutManager(MainActivity.this));




    }

private class adapter extends RecyclerView.Adapter{

        int BOOK = 0;
        int SONG = 1;

        private class bookViewHolder extends RecyclerView.ViewHolder{


            TextView book_text_view;
            public bookViewHolder(@NonNull View itemView) {
                super(itemView);

                book_text_view = itemView.findViewById(R.id.book_text_view);

            }
        }

    private class songViewHolder extends RecyclerView.ViewHolder{

            TextView song_text_view;
        public songViewHolder(@NonNull View itemView) {
            super(itemView);

            song_text_view = itemView.findViewById(R.id.song_text_view);
        }
    }



    @Override
    public int getItemViewType(int position) {

        hashMap = arrayList.get(position);
        String item_type= hashMap.get("list_item");

        if (item_type.contains("book")){
            return BOOK;
        }else {
            return SONG;
        }

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = getLayoutInflater();


        if (viewType==BOOK){
            View myview = layoutInflater.inflate(R.layout.book_list, parent, false);
            return new bookViewHolder(myview);

        }else {
            View myview = layoutInflater.inflate(R.layout.song_list,parent, false);
            return new songViewHolder(myview);
        }

    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (getItemViewType(position)==BOOK){
                bookViewHolder bookHolder = (bookViewHolder) holder;
                hashMap = arrayList.get(position);
                String book_name = hashMap.get("book_name");

                bookHolder.book_text_view.setText(book_name);
                bookHolder.book_text_view.setOnClickListener(v -> {
                    Toast.makeText(MainActivity.this, "Book", Toast.LENGTH_SHORT).show();
                });


            }else {

                songViewHolder songHolder = (songViewHolder) holder;
                hashMap = arrayList.get(position);
                String book_name = hashMap.get("song_name");

                songHolder.song_text_view.setText(book_name);
                songHolder.song_text_view.setOnClickListener(v -> {
                    Toast.makeText(MainActivity.this, "Song", Toast.LENGTH_SHORT).show();
                });

            }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




}



}