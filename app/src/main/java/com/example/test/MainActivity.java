package com.example.test;
// 확인용 수정2
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    static int find(String[] arr, String s) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].indexOf(s) >= 0)
                return 1;
        }
        return -1;
    }

    private EditText et_token;
    private Button btn_insert;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btn_creat;
    private Button btn_count;
    private String str;    // 입력된 토큰을 받는 변수입니다, 변수 명을 바꿔주면 좋을듯...
    String[] token = {"1111","2222","3333","4444","5555"};   //이걸 데이터베이스에서 가져와야 합니다 (수정필요!!!!!!)
    int where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_token = findViewById(R.id.et_token);

        btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을때 실행되는거!!
                str = et_token.getText().toString();  // 넣은 토큰을 string형태로 가져와서 str(빈 변수)에 저장한다
                where = find(token,str);
                if(where==1) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("str", str);  // putExtra로 str(토큰 들어있음)데이터를 담는다
                    startActivity(intent);  // 실제로 액티비티 이동하는 구문, 담은 데이터를 서브 액티비티로 쏜다!!  >>  서브 액티비티로 가서 코드를 만들어주자
                }else{
                    Toast.makeText(getApplicationContext(),"저장되어있지 않은 토큰입니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_creat = findViewById(R.id.btn_creat);
        btn_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatActivity.class);
                startActivity(intent);
            }
        });

        btn_count = findViewById(R.id.btn_count);
        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}


