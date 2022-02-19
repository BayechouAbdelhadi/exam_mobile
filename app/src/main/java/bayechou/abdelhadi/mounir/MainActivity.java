package bayechou.abdelhadi.mounir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bayechou.abdelhadi.mounir.pojo.Student;
import bayechou.abdelhadi.mounir.pojo.StudentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Student> students = new ArrayList<>();
    RecyclerView rvStudents;
    StudentService studentService;

    ImageButton android, android_2, searchButton, goBack;
    LinearLayout linearLayout_1, linearLayout_2;
    EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentService = new Retrofit.Builder()
                .baseUrl(studentService.apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentService.class);

        rvStudents = (RecyclerView) findViewById(R.id.rvStudents);

        studentService.listStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                students = response.body();

                StudentAdapter adapter = new StudentAdapter(students);
                rvStudents.setAdapter(adapter);
                rvStudents.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("failure", t.getMessage());
            }
        });

        android = (ImageButton) findViewById(R.id.android);
        android_2 = (ImageButton) findViewById(R.id.android_2);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        goBack = (ImageButton) findViewById(R.id.goBack);
        searchInput = (EditText) findViewById(R.id.searchInput);
        linearLayout_1 = (LinearLayout) findViewById(R.id.linearLayout_1);
        linearLayout_2 = (LinearLayout) findViewById(R.id.linearLayout_2);

        linearLayout_2.setVisibility(View.INVISIBLE);


        displayDialog(android);
        displayDialog(android_2);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout_1.setVisibility(View.INVISIBLE);
                linearLayout_2.setVisibility(View.VISIBLE);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout_2.setVisibility(View.INVISIBLE);
                linearLayout_1.setVisibility(View.VISIBLE);

                StudentAdapter adapter = new StudentAdapter(students);
                rvStudents.setAdapter(adapter);
                rvStudents.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });


        searchInput.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                List<Student> filteredStudents = students.stream().filter(student -> (student.getNom().toLowerCase()+ " "+ student.getPrenom().toLowerCase()).contains(s.toString().toLowerCase()))
                                                .collect(Collectors.toList());

                StudentAdapter adapter = new StudentAdapter(filteredStudents);
                rvStudents.setAdapter(adapter);
                rvStudents.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }
        });



    }

    public void displayDialog(ImageButton android_button) {
        android_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MainActivity.this);
                dlgAlert.setMessage("Ceci est un message d'information");
                dlgAlert.setTitle("Titre de la fenetre");
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });
    }
}