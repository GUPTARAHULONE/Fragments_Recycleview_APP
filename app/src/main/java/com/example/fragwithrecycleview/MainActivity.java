package com.example.fragwithrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked{

    TextView tvName,tvTel;
    EditText etname,etTel;
    Button btnAdd;
    ListFrag listFrag;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName=findViewById(R.id.tvName);
        tvTel=findViewById(R.id.tvTel);
        etname=findViewById(R.id.etName);
        etTel=findViewById(R.id.etTel);
        btnAdd=findViewById(R.id.btnAdd);

        fragmentManager=this.getSupportFragmentManager();
        listFrag=(ListFrag) fragmentManager.findFragmentById(R.id.listFrag);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(etname.getText().toString().isEmpty()||etTel.getText().toString().isEmpty())
                   {
                       Toast.makeText(MainActivity.this, "plz fill", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       ApplicationClass.people.add(new Person(etname.getText().toString().trim(),etTel.getText().toString().trim()));
                       Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT).show();
                       etname.setText(null);
                        etTel.setText(null);
                       listFrag.notifyDataChanged();
                   }
            }
        });
        onItemClicked(0);
    }

    @Override
    public void onItemClicked(int index) {
        tvName.setText(ApplicationClass.people.get(index).getName());
        tvTel.setText(ApplicationClass.people.get(index).getTelNr());
    }
}