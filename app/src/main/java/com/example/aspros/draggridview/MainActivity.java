package com.example.aspros.draggridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aspros.draggridview.view.DragGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


    private DragGridView dragGridView;
    private List<Integer> datas=new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dragGridView= (DragGridView) findViewById(R.id.gridview);
        init();
    }

    private void init()
    {
        for(int i=0;i<46;i++)
        {
            datas.add(i);
        }
        adapter=new MyAdapter(this);
        adapter.setDatas(datas);
        dragGridView.setAdapter(adapter);

    }
}
