package com.mcpherson.multithreading;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<AdapterItems>listCounter = new ArrayList<AdapterItems>();
    MyCustomAdapter myAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list =(ListView)findViewById(R.id.listView);
    }

    public void createFile(View view) {
        String filename = "numbers.txt";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE); // opens a file in internal storage
            String string;
            // writes numbers 1-10 to internal storage
           for (int i = 1; i < 11; i++) {
               string = i + "\n";
               outputStream.write(string.getBytes());
               Thread.sleep(250);
           }
               outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadFile(View view) {

        Context context = this;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("numbers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                Thread.sleep(250);
                listCounter.add(new AdapterItems(line));
                myAdapter=new MyCustomAdapter(listCounter);
                list.setAdapter(myAdapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clearAdapter(View view) {

    }

    public class AdapterItems
    {
        public   String count;

        //for news details
        AdapterItems(String count)
        {
            this.count = count;
        }
    }

    //display news list
    private class MyCustomAdapter extends BaseAdapter {
        public  ArrayList<AdapterItems>  listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_ticket, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView txtCount=(TextView)myView.findViewById(R.id.count);
            txtCount.setText(s.count);

            return myView;
        }

    }

}
