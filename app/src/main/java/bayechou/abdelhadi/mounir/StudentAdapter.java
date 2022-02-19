package bayechou.abdelhadi.mounir;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bayechou.abdelhadi.mounir.pojo.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private final List<Student> students;

    public StudentAdapter(List<Student> students){
        this.students = students;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView = inflater.inflate(R.layout.student_item, parent, false);

        return new ViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = students.get(position);
        ImageView imageView = holder.avatar;
        TextView fullName = holder.fullName;
        TextView email = holder.email;
        TextView telephone = holder.telephone;
        ImageButton details = holder.details;

        Picasso.get().load(student.getPhoto()).into(imageView);

        fullName.setText(student.getNom() + " " + student.getPrenom());
        email.setText(student.getEmail());
        telephone.setText(student.getPhone());

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), student.getEmail(), Toast.LENGTH_SHORT).show();
                Log.d("success toast", "success toast displayed");
                Intent intent = new Intent(v.getContext(), Details.class);
                intent.putExtra("student", student);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView avatar;
        public TextView fullName;
        public TextView email;
        public TextView telephone;
        public ImageButton details;

        public ViewHolder(View itemView){
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            fullName = (TextView) itemView.findViewById(R.id.fullName);
            email = (TextView) itemView.findViewById(R.id.email);
            telephone = (TextView) itemView.findViewById(R.id.telephone);
            details = (ImageButton) itemView.findViewById(R.id.details);
        }
    }
}