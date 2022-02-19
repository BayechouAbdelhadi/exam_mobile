package bayechou.abdelhadi.mounir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bayechou.abdelhadi.mounir.pojo.Student;

public class Details extends AppCompatActivity {

    ImageView avatar_detail;
    TextView fullName_detail, adresse_detail_value, adresse_detail_label, telephone_detail_value, telephone_detail_label;
    ImageButton linkedIn, goBack_from_details;
    Button map,appeler;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        avatar_detail = (ImageView) findViewById(R.id.avatar_detail);

        fullName_detail = (TextView) findViewById(R.id.fullName_detail);
        adresse_detail_value = (TextView) findViewById(R.id.adresse_detail_value);
        telephone_detail_value = (TextView) findViewById(R.id.telephone_detail_value);
        map = (Button) findViewById(R.id.map);
        appeler = (Button) findViewById(R.id.appeler);
        linkedIn = (ImageButton) findViewById(R.id.linkedIn);
        goBack_from_details = (ImageButton) findViewById(R.id.goBack_from_details);

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        Picasso.get().load(student.getPhoto()).into(avatar_detail);
        //Picasso.get().load(student.getPhoto()).into(linkedIn);

       adresse_detail_value.setText("174 rue michel ange 62100 calais");
       telephone_detail_value.setText(student.getPhone());
        fullName_detail.setText(student.getNom()+ " " + student.getPrenom());

        appeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + student.getPhone()));
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=174 rue michel ange 62100 calais"));
                startActivity(intent);
            }
        });

        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(student.getPhoto()));
                startActivity(intent);
            }
        });

        goBack_from_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}