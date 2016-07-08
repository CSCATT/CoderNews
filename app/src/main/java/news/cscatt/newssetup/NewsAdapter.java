package news.cscatt.newssetup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tillo on 29.6.2016.
 */
public class NewsAdapter extends BaseAdapter implements View.OnClickListener {

    final int DIALOG_ASK = 2;
    String data[] = { "Редактировать", "Удалить", "Удалить все"};
    //---------------------------------------------------
    String LOG_TAG = "TEXT";
    Activity activity;
    //---------------------------------------------------
    List<News> rData = new ArrayList<News>(); // принимает
    //---------------------------------------------------
    static LayoutInflater inflater = null;
    Context mContext;

    //---------------------------------------------------
    public NewsAdapter(Activity a, ArrayList<News> rD,
                              Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return rData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
    }


    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView text;
        public ImageView image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.creativiti_of_news_list, null);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textView2);
            holder.image = (ImageView) vi.findViewById(R.id.imageView3);

            // /Set holder with LayoutInflater/
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        News item = rData.get(position);

        holder.text.setText(item.getNews_title());
        holder.image.setImageResource(R.mipmap.ic_launcher);




        //Set Item Click Listner for LayoutInflater for each row
        vi.setOnClickListener(new OnItemClickListener(position, item));
        //vi.setOnLongClickListener(new OnLongClickListener(position, item));

        return vi;
    }


    /*********
     * Called when Item click in ListView
     ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private News p;

        OnItemClickListener(int position, News news) {
            mPosition = position;
            p = news;
        }

        @Override
        public void onClick(View v) {
            int flag = 1;
            Intent i = new Intent(mContext, ReadNewsActivity.class);
            i.putExtra("news_text", p.getNews_title());
            i.putExtra("news_image", String.valueOf(flag));

            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);

        }
    }
}