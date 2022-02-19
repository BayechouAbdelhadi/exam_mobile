package bayechou.abdelhadi.mounir.pojo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentService {
    public static final String apiURL = "http://192.168.43.61:8080";

    @GET("/students")
    Call<List<Student>> listStudents();

}
