package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private DatabaseReference doc1, doc2, doc3, doc4, doc5, doc7, do8, doc9, doc10;

    private String[][] doctor_details1 =
            {
                    {"Brian", "A1", "5 years", "1234567890", "$80"},
                    {"Peter", "A2", "8 years", "9876543210", "$76"},
                    {"John", "A3", "10 years", "5555555555", "$90"},
                    {"Sarah", "A4", "3 years", "3333333333", "$65"}
            };

    private String[][] doctor_details2 =
            {
                    {"Erick", "B1", "7 years", "1111111111", "$20"},
                    {"Emanuel", "B2", "6 years", "2222222222", "$46"},
                    {"Michael", "B3", "9 years", "4444444444", "$75"},
                    {"Olivia", "B4", "4 years", "6666666666", "$55"}
            };

    private String[][] doctor_details3 =
            {
                    {"Allan", "C1", "12 years", "7777777777", "$95"},
                    {"Karl", "C2", "15 years", "8888888888", "$80"},
                    {"Jennifer", "C3", "18 years", "9999999999", "$100"},
                    {"David", "C4", "20 years", "1231231234", "$105"}
            };

    private String[][] doctor_details4 =
            {
                    {"Purity", "D1", "1 year", "2222333344", "$10"},
                    {"Angel", "D2", "2 years", "1111222233", "$14"},
                    {"Patricia", "D3", "3 years", "3333222211", "$19"},
                    {"Daniel", "D4", "4 years", "4444111122", "$25"}
            };

    private String[][] doctor_details5 =
            {
                    {"Davis", "E1", "6 years", "7654321098", "$60"},
                    {"Voxy", "E2", "5 years", "1234509876", "$46"},
                    {"Susan", "E3", "7 years", "8765432109", "$55"},
                    {"Richard", "E4", "8 years", "9876543210", "$70"}
            };


    TextView tv;
    Button btnBACK;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btnBACK = findViewById(R.id.buttonLTBack);



        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
        if (title.compareTo("Cardiologists")==0)
            doctor_details = doctor_details5;
        else


        btnBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1","Doctor Name:"+doctor_details[i][0]);
            item.put("line2","Hospital Address:"+doctor_details[i][1]);
            item.put("line3","Exp :"+doctor_details[i][2]);
            item.put("line4","Mobile No:"+doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst = findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        btnBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}