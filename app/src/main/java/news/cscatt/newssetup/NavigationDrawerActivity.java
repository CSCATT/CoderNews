package news.cscatt.newssetup;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import news.cscatt.newssetup.news_fragment.KG24NewsFragment;
import news.cscatt.newssetup.news_fragment.VBNewsFragment;
import news.cscatt.newssetup.news_fragment.VestiNewsFragment;
import news.cscatt.newssetup.news_fragment.ZanozaNewsFragment;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = (ListView) findViewById(R.id.listView);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        new AlertDialog.Builder(this)
                .setTitle("Выйти из приложения?")  //оценка приложения
                .setMessage("Вы действительно хотите выйти?")
                .setNegativeButton("Отмена", null)
                .setNeutralButton("Оценить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Uri uri = Uri
                                .parse("http://google.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0,
                                                int arg1) {

                                NavigationDrawerActivity.super.onBackPressed();
                            }
                        }).create().show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DataBase db = new DataBase(NavigationDrawerActivity.this);
            db.deleteAll();
            db.close();
             return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();


        if (id == R.id.vecherka) {
            ftrans.replace(R.id.container, new VBNewsFragment());

            // Handle the camera action
        } else if (id == R.id.vesty) {
            ftrans.replace(R.id.container, new VestiNewsFragment());
        } else if (id == R.id.zanoza) {
            ftrans.replace(R.id.container, new ZanozaNewsFragment());
        } else if (id == R.id.kg24n) {
            ftrans.replace(R.id.container, new KG24NewsFragment());

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }ftrans.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
