package com.teamx.farmapp.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.teamx.farmapp.CustomAdapter;
import com.teamx.farmapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SlideshowFragment extends Fragment {

    EditText xdata, ydata;
    Button insertbtn;
    LineChart linechart;
    LineData lineData;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    ArrayList<Entry> dataVals;
    BarChart barchart;
    BarDataSet barDataSet;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
    RecyclerView recyclerView;
    CustomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        barchart = (BarChart) root.findViewById(R.id.barchart);
        xdata = (EditText) root.findViewById(R.id.x_data);
        ydata = (EditText) root.findViewById(R.id.y_data);
        linechart = root.findViewById(R.id.lineChart);
        insertbtn = root.findViewById(R.id.insertdb);
        //createRandomBarGraph("2016/05/05", "2016/04/01");

        String[] items = {"Total Income","Savings", "Most Sold Crop", "Total Number Of Crops Sold", "Current Sale Price"};
        int flags[] = {R.drawable.money, R.drawable.money, R.drawable.money, R.drawable.money, R.drawable.money};

        adapter = new CustomAdapter(getContext(), flags, items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);
        barEntries = new ArrayList<BarEntry>();
        dataVals = new ArrayList<Entry>();
        lineDataSet.setLineWidth(5);
        insertData();
        return root;
    }

    public void insertData() {
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBar();
                float size = 20;
                int x = Integer.parseInt(xdata.getText().toString());
                int y = Integer.parseInt(ydata.getText().toString());

                dataVals.add(new Entry(x, y));
                lineDataSet.setValues(dataVals);
                lineDataSet.setCircleColor(R.color.text_color);
                lineDataSet.setLabel("DataSet 1");
                lineDataSet.setValueTextSize(size);
                iLineDataSets.clear();
                iLineDataSets.add(lineDataSet);
                lineData = new LineData(iLineDataSets);
                linechart.clear();
                linechart.setData(lineData);
                linechart.invalidate();

            }
        });
    }
    public void getBar(){
        int x = Integer.parseInt(xdata.getText().toString());
        int y = Integer.parseInt(ydata.getText().toString());

        barEntries.add(new BarEntry(x, y));
        barDataSet = new BarDataSet(barEntries, "Bar dataset");
        barDataSet.setColor(R.color.text_color);
        barDataSet.setBarBorderColor(R.color.colorAccent);
        //barDataSet.setValues(barEntries);
       // barDataSet.setLabel();
        iBarDataSets.clear();
        iBarDataSets.add(barDataSet);

        BarData barData = new BarData(iBarDataSets);
        barchart.clear();
        barchart.setData(barData);
        barchart.invalidate();

    }

    public void createRandomBarGraph(String Date1, String Date2){

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date date1 = simpleDateFormat.parse(Date1);
            Date date2 = simpleDateFormat.parse(Date2);

            Calendar mDate1 = Calendar.getInstance();
            Calendar mDate2 = Calendar.getInstance();
            mDate1.clear();
            mDate2.clear();

            mDate1.setTime(date1);
            mDate2.setTime(date2);

            dates = new ArrayList<>();
            dates = getList(mDate1, mDate2);

            random = new Random();
            barEntries = new ArrayList<BarEntry>();
            float max = 0f;
            float value = 0f;
            for(int j = 0; j< dates.size(); j++){
                max = 100f;
                value = random.nextFloat()*max;
                barEntries.add(new BarEntry(value, j));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
        BarData barData = new BarData((IBarDataSet) dates, barDataSet);
        barchart.setData(barData);
    }
    public ArrayList<String> getList(Calendar startDate, Calendar endDate){
        ArrayList<String> list = new ArrayList<>();
        while (startDate.compareTo(endDate) <=0){
            list.add(getDate(startDate));
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }
    public String getDate(Calendar cls){
        String curDate = cls.get(Calendar.YEAR) + "/" + (cls.get(Calendar.MONTH) + 1) + "/"
                + cls.get(Calendar.DAY_OF_MONTH);
        try{
            Date date = new SimpleDateFormat("yyyy/mm/dd").parse(curDate);
            curDate = new SimpleDateFormat("yyyy/mm/dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDate;
    }
}