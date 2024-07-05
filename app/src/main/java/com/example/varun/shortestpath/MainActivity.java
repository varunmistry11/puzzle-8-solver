package com.example.sharnam.shortestpath;

import android.os.Bundle;
import android.os.Handler;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by SHARNAM on 01-04-2016.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String solutionString = "";
    private Graph graph;
    private String solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_button).setOnClickListener(this);
        findViewById(R.id.edges_text_view).setOnClickListener(this);
        graph = new Graph();
        solution=graph.dijkstraAlgorithm();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_button) {
            startGame();
        } else if (view.getId() == R.id.text2 || view.getId() == R.id.text3 || view.getId() == R.id.text4 || view.getId() == R.id.text5 ||
                view.getId() == R.id.text6 || view.getId() == R.id.text7 || view.getId() == R.id.text8 || view.getId() == R.id.text9) {
            solutionString = solutionString + "-" + ((TextView) view).getText();
            ((TextView) findViewById(R.id.solution_text_view)).setText(solutionString);
            findViewById(view.getId()).setBackgroundResource(R.drawable.selected);

        } else if (R.id.clear_button == view.getId()) {
            resetButtons();
        } else if (R.id.check_button == view.getId()) {
            if (solutionString.equals(graph.dijkstraAlgorithm())) {
                Toast.makeText(MainActivity.this, "Well Done", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
            resetButtons();
            
        } else if (R.id.show_solution_button == view.getId()) {
            resetButtons();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                String[] split = solution.split("-");
                int index = 0;

                @Override
                public void run() {
                    if (index < split.length) {
                        findViewById(getResources().getIdentifier("text" + split[index], "id", MainActivity.this.getPackageName()))
                                .setBackgroundResource(R.drawable.selected);
                        TextView tv=(TextView) findViewById(R.id.solution_text_view);
                        if(index==0){
                            tv.setText("Solution is:1");
                        }else{
                            tv.setText(tv.getText() +"-"+ split[index]);
                        }
                        index++;
                        handler.postDelayed(this, 1000);
                    } else {
                        handler.removeCallbacks(this);
                    }
                }
            }, 1000);
        } else if (R.id.reset_button == view.getId()) {
            resetButtons();
            startGame();
        }
    }

    private void resetButtons(){
        int index = 1;
        while (index <=9) {
            findViewById(getResources().getIdentifier("text" + index, "id", MainActivity.this.getPackageName()))
                    .setBackgroundResource(R.drawable.round_text);
            index++;
        }
        findViewById(R.id.text1).setBackgroundResource(R.drawable.selected);
        solutionString = "1";
        ((TextView) findViewById(R.id.solution_text_view)).setText(solutionString);
    }

    private void startGame() {
        findViewById(R.id.linear_layout_4).setVisibility(View.GONE);
        findViewById(R.id.start_button).setVisibility(View.GONE);
        findViewById(R.id.edges_text_view).setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        final HashMap<Integer, ArrayList<NodeLinks>> nodeMap = graph.getAllNodeLinks();
        final ArrayList<NodeLinks> item=new ArrayList<NodeLinks>();
        for(int i=0;i<9;i++){
            ArrayList<NodeLinks> items=nodeMap.get(i);
            if(items!=null)
            for(NodeLinks nodeLinks:items){
                item.add(nodeLinks);
            }
        }
        handler.postDelayed(new Runnable() {
            int listItemCount=0;

            public void run() {
                if (listItemCount < item.size()) {
                    ((TextView) findViewById(R.id.edges_text_view)).setText("Weight(" + (item.get(listItemCount).getSource())
                          + "," + (item.get(listItemCount).getDestination()) + ")=" +
                          item.get(listItemCount).getCost());
                    listItemCount++;
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                    resetButtons();
                    findViewById(R.id.linear_layout_4).setVisibility(View.VISIBLE);
                    findViewById(R.id.edges_text_view).setVisibility(View.GONE);
                    int[] ids = { R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9,
                          R.id.clear_button, R.id.check_button, R.id.show_solution_button, R.id.reset_button};
                    for (int id : ids) {
                        findViewById(id).setOnClickListener(MainActivity.this);
                    }
                }
            }
        }, 1000);

    }
}