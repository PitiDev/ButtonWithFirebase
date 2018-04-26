package com.phanthasombath.piti.firebaseiot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference myRef;
    private TextView mfirebaseTextView;

    private TextView mTextViewFanOut;
    private TextView mTextViewPumNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfirebaseTextView = (TextView) findViewById(R.id.firebaseTextView);
        mTextViewFanOut = (TextView) findViewById(R.id.TextViewFanOut);
        mTextViewPumNum = (TextView) findViewById(R.id.TextViewPumNum);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map =(Map)dataSnapshot.getValue();
                String value = String.valueOf(map.get("FanIn"));
                mfirebaseTextView.setText(value);
                value = String.valueOf(map.get("FanOut"));
                mTextViewFanOut.setText(value);
                value = String.valueOf(map.get("PumNum"));
                mTextViewPumNum.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //----------------------------- Button ---------------------------------------------------------

    public void onClickFaninOn(View view) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("FanIn","ON");
        myRef.updateChildren(value);
    }
    public void OnClickFaninOf(View view){
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("FanIn","OFF");
        myRef.updateChildren(value);
    }

    public void onClickFanOutOn(View view) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("FanOut","ON");
        myRef.updateChildren(value);
    }
    public void OnClickFanOutOf(View view){
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("FanOut","OFF");
        myRef.updateChildren(value);
    }

    public void OnClickPumNamOn(View view) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("PumNum","ON");
        myRef.updateChildren(value);
    }
    public void OnClickPumNamOf(View view){
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("PumNum","OFF");
        myRef.updateChildren(value);
    }
//----------------------------End Button -----------------------------------------------------------

}
