package com.example.test;
//이 페이지는 맨 첫 페이지에서 + 버튼을 눌렀을 때 투표를 생성하는 페이지 입니다.
//우선 선거 명부를 작성해야합니다 - 파일로 입력받을 수 있으면 좋겠습니다.

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class CreatActivity extends AppCompatActivity {

    private EditText et_studentID;
    private TextView tv_display;

    static final String mFILENAME = "myContacts.txt";
    private Button btn_next;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
        database = FirebaseDatabase.getInstance();

        et_studentID = (EditText) findViewById(R.id.et_studentID);
        tv_display = (TextView) findViewById(R.id.tv_display);

        displayContacts();

        btn_next = (Button) findViewById(R.id.btn_next);
        // next 버튼 누르면 생기는 일
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //next 버튼을 눌렀을 때를 말하는듯

                if (list1.size() > 0) {
                    Intent intent = new Intent(CreatActivity.this, CreatActivity2.class);
                    intent.putExtra("list1", list1);
                    startActivity(intent);


                } else {
                    Toast.makeText(getApplicationContext(), "후보 이름이 작성되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }



    public void mOnInsertClick(View view) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);

            String studentID = et_studentID.getText().toString();

            dos.writeUTF(studentID);

            dos.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (dos != null) dos.close();
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        displayContacts();

        et_studentID.setText("");

    }

    public void mOnDeleteClick(View view) {
        //지정된 파일 자체를 삭제할 수 있게하는 코드
        if (deleteFile(mFILENAME)) {
            tv_display.setText("delete success");
            list1.clear();
        }else
            tv_display.setText("delete failed");
    }


    ArrayList list1 = new ArrayList();
    private void displayContacts() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = openFileInput(mFILENAME);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);


            String str1 = "";
            while (dis.available() > 0) {
                String str = "";
                String studentID = dis.readUTF();

                str1 += studentID + "\n " ;
                str = studentID;
                tv_display.setText(str1 + "\n");
                list1.add(str);
                System.out.println(list1);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) dis.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


