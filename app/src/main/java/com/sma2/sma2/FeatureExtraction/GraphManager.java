package com.sma2.sma2.FeatureExtraction;

import android.content.Context;
import android.graphics.Color;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sma2.sma2.DataAccess.FeatureDA;
import com.sma2.sma2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GraphManager {

    Context CONTEXT;

    public GraphManager(Context context){

        CONTEXT=context;


    }

    public void BarGraph(GraphView graph, ArrayList<Integer> x, ArrayList<Float> y, double maxY, double maxX, String Title, String Xlabel, String Ylabel){


        BarGraphSeries<DataPoint> series= new BarGraphSeries<>();

            for (int i = 0; i < x.size(); i++) {
                series.appendData(new DataPoint(x.get(i), y.get(i)), true, 5);
            }

        graph.addSeries(series);

        series.setColor(Color.rgb(255, 140, 0));
        series.setSpacing(5);
        graph.getViewport().setMinY(0.0);
        if (maxY>0){
            graph.getViewport().setMaxY(maxY);
        }
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(maxX);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        series.setTitle(Title);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle(Xlabel);
        gridLabel.setVerticalAxisTitle(Ylabel);

    }


    public void LineGraph(GraphView graph, ArrayList<Integer> x, ArrayList<Float> y, double maxY, double maxX, String Title, String Xlabel, String Ylabel){


        LineGraphSeries<DataPoint> series= new LineGraphSeries<>();

        for (int i = 0; i < x.size(); i++) {
            series.appendData(new DataPoint(x.get(i), y.get(i)), true, x.size());
        }

        graph.addSeries(series);

        series.setColor(Color.rgb(255, 140, 0));
        graph.getViewport().setMinY(0.0);
        if (maxY>0){
            graph.getViewport().setMaxY(maxY);
        }
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(maxX);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        series.setTitle(Title);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle(Xlabel);
        gridLabel.setVerticalAxisTitle(Ylabel);
    }

    public void LineGraph(GraphView graph, ArrayList<Float> x, ArrayList<Float> y, double maxY, double minY, double maxX, String Title, String Xlabel, String Ylabel){

        LineGraphSeries<DataPoint> series= new LineGraphSeries<>();

        for (int i = 0; i < x.size(); i++) {
            series.appendData(new DataPoint(x.get(i), y.get(i)), true, x.size());
        }

        graph.addSeries(series);

        series.setColor(Color.rgb(255, 140, 0));
        graph.getViewport().setMinY(0.0);
        if (maxY > 0){
            graph.getViewport().setMaxY(maxY);
        }

        if (minY < 0){
            graph.getViewport().setMinY(minY);
        }
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(maxX);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        series.setTitle(Title);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle(Xlabel);
        gridLabel.setVerticalAxisTitle(Ylabel);
    }




    public void LineGraph(GraphView graph, List<FeatureDA> features, String Xlabel, String Ylabel){


        LineGraphSeries<DataPoint> series= new LineGraphSeries<>();

        float y=0;
        long datef=0;
        FeatureDA feature;
        Date date;
        final ArrayList<Long> dates=new ArrayList<>();
        dates.clear();

        int j=0;
        for (int i = features.size()-1; i >=0; i--) {

            feature=features.get(i);
            y=feature.getFeature_value();
            date=feature.getFeature_date();
            datef=date.getTime();
            dates.add(datef);
            series.appendData(new DataPoint(j, y), true, features.size());
            j+=1;
        }


        graph.addSeries(series);
        series.setColor(Color.rgb(255, 140, 0));


        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle(Xlabel);
        gridLabel.setVerticalAxisTitle(Ylabel);
        gridLabel.setHorizontalLabelsAngle(135);
        gridLabel.setLabelFormatter(new DefaultLabelFormatter() {
            SimpleDateFormat dateFormat =  new SimpleDateFormat("dd/MM");
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {

                    long datef=dates.get((int)value);
                    Date d = new Date(datef);
                    return (dateFormat.format(d));
                }
                return "" + (int) value;
            }
        });
        graph.getViewport().setMaxY(102);
        graph.getViewport().setMinY(0);
        graph.getViewport().setYAxisBoundsManual(true);

    }




    public void LineGraph2lines(GraphView graph, float[] x1, float[] y1, float[] x2, float[] y2, double maxY, double maxX, String Title, String Xlabel, String Ylabel, String legend1, String legend2){


        LineGraphSeries<DataPoint> series= new LineGraphSeries<>();
        for (int i = 0; i < x1.length; i++) {
            series.appendData(new DataPoint(x1[i], y1[i]), true, x1.length);
        }

        LineGraphSeries<DataPoint> series2= new LineGraphSeries<>();
        for (int i = 0; i < x2.length; i++) {
            series2.appendData(new DataPoint(x2[i], y2[i]), true, x2.length);
        }



        graph.addSeries(series);
        graph.addSeries(series2);

        series.setColor(Color.rgb(255, 140, 0));
        series2.setColor(Color.rgb(0, 0, 0));
        series.setTitle(legend1);
        series2.setTitle(legend2);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setMinY(0.0);

        graph.getViewport().setMinX(1.0);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle(Xlabel);
        gridLabel.setVerticalAxisTitle(Ylabel);
    }







}
