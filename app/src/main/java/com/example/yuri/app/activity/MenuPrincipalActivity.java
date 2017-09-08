package com.example.yuri.app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yuri.app.R;
import com.example.yuri.app.dao.UsuarioDao;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.Search;
import com.example.yuri.app.model.Usuario;
import com.example.yuri.app.tab.MyAdapter;
import com.example.yuri.app.tab.SlidingTabLayout;
import com.example.yuri.app.util.Mensagem;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mItemSelected;
    private String[] listItems;
    boolean[] checkedItems;
    private ArrayList<Integer> mUserItems = new ArrayList<>();
    private MaterialSearchView searchView;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    Database db = new Database(MenuPrincipalActivity.this);
    UsuarioDao usuarioDao = new UsuarioDao(MenuPrincipalActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogAdicionar();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mItemSelected = (TextView) findViewById(R.id.ActivityMain_tvItemSelected);
        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];

        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);

    }

    private ArrayList<Search> initData() {
        ArrayList<Search> items = new ArrayList<>();
        items.add(new Search("Wesley"));
        items.add(new Search("Rodrigues"));
        items.add(new Search("Ramos"));
        items.add(new Search("Wesley Rodrigues"));
        items.add(new Search("Wesley Ramos"));
        items.add(new Search("Wesley Rodrigues Ramos"));

        return items;
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
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            listarUsuarios();
            return true;
        } else if (id == R.id.action_search) {
            abrirSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    private void chamarBanco() {

        Usuario usuario2 = new Usuario("Wesley", "email@email.com", 55, 44, 996222010);
        Log.i("142: ","Insert 1");
        Usuario usuario3 = new Usuario("Ramos", "email@email.com", 55, 44, 996222010);
        Log.i("142: ","Insert 2");
        usuarioDao.adicionarUsuario(usuario2);
        Log.i("142: ","Insert 1 - A");
        usuarioDao.adicionarUsuario(usuario3);
        Log.i("142: ","Insert 2 - A");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            abrirSearch();
        } else if (id == R.id.nav_gallery) {
            listarUsuarios();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listarUsuarios(){
        chamarBanco();
        List<Usuario> usuarios = usuarioDao.listaTodosUsuarios();
        for (Usuario u : usuarios){
            Log.d("Lista:", "\nID: " + u.getId() + " NOME: " + u.getNome());
        }

    }

    public void abrirDialogAdicionar(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuPrincipalActivity.this);
        mBuilder.setTitle(R.string.dialog_title);
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked) {
                    mUserItems.add(position);
                } else {
                    mUserItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + listItems[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }
                }
                mItemSelected.setText(item);
            }
        });

        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mUserItems.clear();
                    mItemSelected.setText("");
                }
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void abrirSearch(){
        new SimpleSearchDialogCompat(MenuPrincipalActivity.this,
                "Pesquisar",
                "Digite aqui o que está procurando...",
                null,
                initData(),
                new SearchResultListener<Searchable>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, Searchable searchable, int i) {
                        Mensagem.toastShort(MenuPrincipalActivity.this, ""+searchable.getTitle());
                        baseSearchDialogCompat.dismiss();
                    }
                }).show();
       }
}
