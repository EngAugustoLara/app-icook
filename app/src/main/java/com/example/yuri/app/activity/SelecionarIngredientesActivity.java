package com.example.yuri.app.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuri.app.R;

import java.util.ArrayList;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.yuri.app.R;
import com.example.yuri.app.database.myDbAdapter;
import com.example.yuri.app.util.Mensagem;

import java.util.ArrayList;

public class SelecionarIngredientesActivity extends AppCompatActivity {


    EditText Name, Pass, updateold, updatenew, delete;
    private Button btAdcReceita;
    private ImageView GerarNovoIngrediente;
    myDbAdapter helper;


    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        updateold = (EditText) findViewById(R.id.editText3);
        updatenew = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        mItemSelected = (TextView) findViewById(R.id.ActivityMain_tvItemSelected);
        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];

        helper = new myDbAdapter(this);

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SelecionarIngredientesActivity.this);
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
        });
    }




    public void addReceita() {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();

        if(t1.isEmpty() || t2.isEmpty())
        {
            Mensagem.toastShort(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            long id = helper.insertData(t1,t2);
            if(id<=0)
            {
                Mensagem.toastShort(getApplicationContext(),"Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            } else
            {
                Mensagem.toastShort(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Mensagem.toastShort(this,data);
    }

    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Mensagem.toastShort(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
                Mensagem.toastShort(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Mensagem.toastShort(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }
    }
    public void delete( View view)
    {
        String uname = delete.getText().toString();

        if(uname.isEmpty())
        {
            Mensagem.toastShort(getApplicationContext(),"Enter Data");
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Mensagem.toastShort(getApplicationContext(),"Unsuccessful");
                delete.setText("");
            }
            else
            {
                Mensagem.toastShort(this, "DELETED");
                delete.setText("");
            }
        }
    }

}
