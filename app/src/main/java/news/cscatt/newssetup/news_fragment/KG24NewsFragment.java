package news.cscatt.newssetup.news_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;


import news.cscatt.newssetup.DataBase;
import news.cscatt.newssetup.News;
import news.cscatt.newssetup.R;
import news.cscatt.newssetup.ReadNewsActivity;
import news.cscatt.newssetup.imageloader.LazyImageLoadNewsAdapter;

/**
 * Created by tillo on 29.6.2016.
 */
public class KG24NewsFragment extends ListFragment {
    LazyImageLoadNewsAdapter adapter;
    ListView list;

    public KG24NewsFragment() {
    }


    final String LOG_TAG = "KG24NewsFragment";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = (ListView) getActivity().findViewById(R.id.listView);
        adapter = new LazyImageLoadNewsAdapter(getActivity(), getData(),
                getActivity().getApplicationContext());// mStrings
        setListAdapter(adapter);
//        getData();
//        Log.d(LOG_TAG, "getData");

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        Intent i = new Intent(getActivity(), ReadNewsActivity.class);
        i.putExtra("mPosition", position);
        startActivity(i);

    }

    private ArrayList<News> getData() {
        DataBase db = new DataBase(getActivity()
                .getApplicationContext());
        final ArrayList<News> stringItems = new ArrayList<News>();

        ArrayList<News> pr = (ArrayList<News>) db.getAllNews();

        for (News p : pr) {
            stringItems.add(p);
        }
        Log.d(LOG_TAG, stringItems.toString());
        return stringItems;

    }
}