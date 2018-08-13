package com.feby.asyst.ordercoklat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.feby.asyst.ordercoklat.utility.Constant;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    RadioGroup rgCoklat;
    RadioButton rbOriginal, rbMatcha, rbMilk;
    CheckBox cbChocochips, cbOreo;
    Button btnOrder;
    String selectedMenu = "Choco Original (Rp. 10000)", selectedTopping, nama;
    ArrayList<String> listTopping = new ArrayList<>();
    String topping;
    int harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        etNama = findViewById(R.id.nama_edittext);
        rgCoklat = findViewById(R.id.coklat_radiogroup);
        rbOriginal = findViewById(R.id.original_radiobutton);
        rbMatcha = findViewById(R.id.matcha_radiobutton);
        rbMilk = findViewById(R.id.milk_radiobutton);
        cbChocochips = findViewById(R.id.chocochips_checkbox);
        cbOreo = findViewById(R.id.oreo_checkbox);
        btnOrder = findViewById(R.id.order_button);

        rgCoklat.setOnCheckedChangeListener(this);

        cbChocochips.setOnCheckedChangeListener(this);
        cbOreo.setOnCheckedChangeListener(this);

        btnOrder.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_button:
                nama = etNama.getText().toString();
                countPrice();

                if (!TextUtils.isEmpty(etNama.getText().toString())) {

                    topping = "";
                    for (int i = 0; i < listTopping.size(); i++) {
                        topping = topping + " " + listTopping.get(i);

                    }

                    Log.d("topping", topping);

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Are You Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                            String result = ("Nama : " + nama + "\n" + "Menu : " + selectedMenu + "\n" + "Topping : " + topping + "\n" + "Harga : " );
                            int total = harga;
//                            intent.putExtra(Constant.KEY_RESULT, result);
//                            startActivity(intent);
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra(Constant.KEY_RESULT, result);
                            returnIntent.putExtra(Constant.KEY_TOTAL, total);
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    }).setNegativeButton("No", null).show();


                } else {
                    Toast.makeText(getApplicationContext(), "HARAP ISI NAMA", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.chocochips_checkbox:
                if (isChecked) {
                    listTopping.add("Chocochips (Rp. 2000)\n");
                } else {
                    listTopping.remove("Chocochips (Rp. 2000)\n");
                }
                break;
            case R.id.oreo_checkbox:
                if (isChecked) {
                    listTopping.add("Oreo (Rp. 3000)");
                } else {
                    listTopping.remove("Oreo (Rp. 3000)");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.original_radiobutton:
                selectedMenu = "Choco Original (Rp. 10000)";
                break;
            case R.id.matcha_radiobutton:
                selectedMenu = "Choco Matcha (Rp. 12000)";
                break;
            case R.id.milk_radiobutton:
                selectedMenu = "Choco Milk (Rp. 16000)";
                break;
        }
    }

    public void countPrice() {
        if (rbOriginal.isChecked()) {
            harga += 10000;
            if (cbChocochips.isChecked()){
                harga += 2000;
            }
            if (cbOreo.isChecked()){
                harga += 3000;
            }
        }
        if (rbMatcha.isChecked()) {
            harga += 12000;
            if (cbChocochips.isChecked()){
                harga += 2000;
            }
            if (cbOreo.isChecked()){
                harga += 3000;
            }
        }
        if (rbMilk.isChecked()) {
            harga += 16000;
            if (cbChocochips.isChecked()){
                harga += 2000;
            }
            if (cbOreo.isChecked()){
                harga += 3000;
            }
        }
    }
}
