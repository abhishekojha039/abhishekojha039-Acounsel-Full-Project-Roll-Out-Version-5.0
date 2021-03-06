package com.edge.examfinder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class A_list extends AppCompatActivity {
    ListView lstvw;
    String nm[]={"Government Sector","Central Government"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_list);
        lstvw=findViewById(R.id.lstvw);
        ArrayAdapter adapter=new ArrayAdapter(A_list.this,R.layout.listview,nm);
        lstvw.setAdapter(adapter);
        lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=""+nm[position];
                if(str.equals("Government Sector"))
                {
                    Intent intent=new Intent(A_list.this,A_Central.class);
                    startActivity(intent);
                }
                else if(str.equals("Central Government"))
                {
                    Intent intent=new Intent(A_list.this,A_Govt.class);
                    startActivity(intent);
                }
            }
        });
    }
}
