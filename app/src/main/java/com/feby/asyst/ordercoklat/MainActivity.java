package com.feby.asyst.ordercoklat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feby.asyst.ordercoklat.utility.Constant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    ArrayList<String> listOrder = new ArrayList<>();
    Button btnAdd, btnExit;
    ArrayAdapter arrayAdapter;
    TextView tvTotal, tvPpn, tvJumlah;
    int total;
    int jumlahHarga, ppn, totalHarga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        btnAdd = findViewById(R.id.add_button);
        btnExit = findViewById(R.id.exit_button);
        tvTotal = findViewById(R.id.total_textview);
        tvPpn = findViewById(R.id.ppn_textview);
        tvJumlah = findViewById(R.id.jumlah_harga_textview);

        btnAdd.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listOrder);
        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                String result=bundle.getString(Constant.KEY_RESULT,"");
                total=bundle.getInt(Constant.KEY_TOTAL,0);
                listOrder.add(result+total);
                arrayAdapter.notifyDataSetChanged();
                jumlahHarga += total;
                ppn = ((10*jumlahHarga)/100);
                totalHarga = jumlahHarga + ppn;
                tvJumlah.setText("Jumlah Harga : "+ jumlahHarga);
                tvPpn.setText("PPN : Rp. "+ppn);
                tvTotal.setText("TOTAL HARGA : Rp. "+ totalHarga);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
//                Intent intent = new Intent(MainActivity.this,OrderActivity.class);
////                startActivity(intent);
                Intent i = new Intent(this, OrderActivity.class);
                startActivityForResult(i, 1);
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }
}
