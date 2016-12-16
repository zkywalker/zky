package org.zky.zky;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.zky.zky.utils.GetText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_widget) {
            // Handle the camera action
        } else if (id == R.id.nav_view) {

        } else if (id == R.id.nav_activity) {

        } else if (id == R.id.nav_sdk) {

        } else if (id == R.id.nav_share) {
            //            intent.setType("image/*");
            /*
             * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
             * File(Environment.getExternalStorageDirectory()+"/name.png");
             *
             * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM,
             * uri); 　
             */
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain"); //纯文本
            intent.putExtra(Intent.EXTRA_SUBJECT, GetText.getString(R.string.share));
            intent.putExtra(Intent.EXTRA_TEXT, GetText.getString(R.string.share_content));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent,  GetText.getString(R.string.share)));

        } else if (id == R.id.nav_send) {

            try {

//                Intent.ACTION_SENDTO 无附件的发送
//                Intent.ACTION_SEND 带附件的发送
//                Intent.ACTION_SEND_MULTIPLE 带有多附件的发送
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+GetText.getString(R.string.email)));
                intent.putExtra(Intent.EXTRA_SUBJECT, GetText.getString(R.string.email_subject));
                startActivity(intent);

            }catch (ActivityNotFoundException e){

                Snackbar.make(findViewById(R.id.content_main),GetText.getString(R.string.no_email_app),Snackbar.LENGTH_LONG).setAction(GetText.getString(R.string.i_know), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
