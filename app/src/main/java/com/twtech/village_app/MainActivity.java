
package com.twtech.village_app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView stickyView;
    private ListView listView;
    private View heroImageView;
    private int MAX_ROWS = 20;
    String[] values;
    ArrayList<String> arrayList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        heroImageView = findViewById(R.id.img);
        /* Inflate list header layout */
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listHeader = inflater.inflate(R.layout.listheader, null);
        /* Add list view header */
        listView.addHeaderView(listHeader);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /* Check if the first item is already reached to top.*/
                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChild = listView.getChildAt(0);
                    int topY = 1;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }
                    /* Set the image to scroll half of the amount that of ListView */
                    heroImageView.setY(topY * 1.0f);
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                values = new String[]{"Contacts",
                        "Daily news",
                        "Voting details",
                        "Famous for",
                        "Temple",
                        "Suggestions"


                });

        arrayList.add("null");
        arrayList.add("file:///android_asset/Contact.html");
        arrayList.add("http://www.lokmat.com/latestnews/");
        arrayList.add("file:///android_asset/A0880002.pdf");
        arrayList.add("file:///android_asset/activity_lifecycle.html");
        arrayList.add("file:///android_asset/temple.html");



        arrayList.add("file:///android_asset/suggestion.html");






        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                webView.loadUrl(arrayList.get(position));
//                webView.setVisibility(View.VISIBLE);
                //String image= (String) parent.getItemAtPosition(position);
                //int s1= Integer.parseInt(arrayList.get(position));

                //int image=(Integer) parent.getItemAtPosition(position);

                if (position>0)
                {
                    String s=arrayList.get(position);
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("img1", s);
                    startActivity(intent);
                }
//                else
//                {
//
//                }


            }
        });






    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuass,menu);
        return true;



    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.ques:
                Intent i1=new Intent(MainActivity.this,Main2Activity.class);
                i1.putExtra("str11","https://docs.google.com/forms/d/1wZoLGR14FsU1ITg4r14Lfq7tqUi4Ql2WxsiGhKbfDlA/edit");
                startActivity(i1);
//                WebView webView = (WebView) findViewById(R.id.web1);
//
//
//                WebSettings webSettings = webView.getSettings();
//                webSettings.setJavaScriptEnabled(true);
//                webView.loadUrl("file:///android_asset/Interview_qestions.html");
                break;

            case R.id.share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                startActivity(Intent.createChooser(myIntent,"Sharing Options..."));

                break;

            case R.id.about:

                aboutus fragmentDemo=new aboutus();
                FragmentManager fm=getSupportFragmentManager();
                fm
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                        .add(R.id.activity_main,fragmentDemo)
                        .commit();



                break;


        }
        return  super.onOptionsItemSelected(item);
    }



}







