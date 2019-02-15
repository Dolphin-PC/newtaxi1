package com.example.pcy.sharingeconomy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.Nullable;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PurchaseActivity extends AppCompatActivity {
    private Button btnPurchase;
    private PopupWindow pwindow;
    final Point p = new Point();
    private int mWidthPixels, mHeightPixels;
    private TextView TextViewTitle,TextViewContent;
    private EditText editTextPrice, editTextExpirationDate;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        final Intent intent = getIntent();
        index = intent.getExtras().getInt("Index");

        TextViewTitle = (TextView)findViewById(R.id.textViewSelect);
        TextViewContent = (TextView)findViewById(R.id.textViewBody);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextExpirationDate = (EditText) findViewById(R.id.editTextExpirationDate);

        btnPurchase = (Button) findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (p != null) {
                    showpopupwindows(PurchaseActivity.this, p);
                }
            }
        });
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();
        Query query = reference.child("post").orderByChild("index").equalTo(index);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
                Data_Post dataPost = nodeDataSnapshot.getValue(Data_Post.class);
                TextViewTitle.setText(dataPost.getTitle());
                TextViewContent.setText(dataPost.getContent());
                editTextPrice.setText(String.valueOf(dataPost.getPoint()));
                editTextExpirationDate.setText(String.valueOf(dataPost.getTime()));
            }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    public void onWindowsFocusChanged(boolean hasFocus)
    {
        int location[] = new int [2];
        btnPurchase.getLocationOnScreen(location);

        p.x = location[0];
        p.y = location[1];
    }

    private void showpopupwindows(final Activity context, Point p)
    {
        int popupwidth = 1100;
        int popupheight = 400;

        LinearLayout viewgroup = (LinearLayout)context.findViewById(R.id.purchase_popup_window);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.purchase_popup_window, viewgroup);

        final PopupWindow window = new PopupWindow(context);
        window.setContentView(layout);
        window.setWidth(popupwidth);
        window.setHeight(popupheight);
        window.setFocusable(true);

        int offset_x = 150;
        int offset_y = 1000;

        window.showAtLocation(layout, Gravity.NO_GRAVITY, p.x+offset_x, p.y+offset_y);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(getApplicationContext(), client.class);
                startActivity(intentBack);
            }
        });


        Button btnAccept = (Button) layout.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPurchase.setVisibility(View.GONE);
                window.dismiss();
                ImageView imageView = (ImageView) findViewById(R.id.imageViewBarcode);
                imageView.setVisibility(View.VISIBLE);
            }
        });

        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"결제가 취소되었습니다",Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });

    }


}