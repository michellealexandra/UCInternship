package com.example.ucinternship.network;

import com.example.ucinternship.model.response.AcceptResponse;
import com.example.ucinternship.model.response.PendingResponse;
import com.example.ucinternship.model.response.ProgressResponse;
import com.example.ucinternship.model.response.ProjectUserResponse;
import com.example.ucinternship.model.response.StudentProgressResponse;
import com.example.ucinternship.model.response.ProjectResponse;
import com.example.ucinternship.model.response.StudentResponse;
import com.example.ucinternship.model.response.SupervisorProgressResponse;
import com.example.ucinternship.model.response.SupervisorResponse;
import com.example.ucinternship.model.response.TaskResponse;
import com.example.ucinternship.model.response.TokenResponse;
import com.example.ucinternship.utils.Constants;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final Endpoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    //fungsi retrofit service itu nyambungin apps dengan DB (request)

    private RetrofitService(String token) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")) {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            });
        } else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(Endpoints.class);
    }

    public static com.example.ucinternship.network.RetrofitService getInstance(String token) {
        if (service == null) {
            service = new com.example.ucinternship.network.RetrofitService(token);
        } else if (!token.equals("")) {
            service = new com.example.ucinternship.network.RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String email, String password) {
        return api.login(email, password);
    }

    public Call<ProjectResponse> getProjects() {
        return api.getProjects();
    }

    public Call<ProjectResponse> getProjectOffers() {
        return api.getProjectOffers();
    }

    public Call<ProjectResponse> getEventOffer() {
        return api.getEventOffer();
    }

    public Call<ProjectResponse> getEducationOffer() {
        return api.getEducationOffer();
    }

    public Call<ProjectResponse> getOtherOffer() {
        return api.getOtherOffer();
    }

    public Call<StudentResponse> getStudentDetails(int id) {
        return api.getStudentDetails(id);
    }

    public Call<SupervisorResponse> getSupervisorDetails(int id) {
        return api.getSupervisorDetails(id);
    }

    public Call<TaskResponse> getTasks() {
        return api.getTasks();
    }
    public Call<TaskResponse> getTaskLists(int project_id) {
        return api.getTaskLists( project_id);
    }

    public Call<StudentProgressResponse> getProgresses() {
        return api.getProgresses();
    }
    public Call<StudentProgressResponse> getProgressLists(int task_id) {
        return api.getProgressLists(task_id);
    }

    public Call<SupervisorProgressResponse> getSpvProgresses() {
        return api.getSpvProgresses();
    }

    public Call<PendingResponse> getPending() {
        return api.getPending();
    }

    public Call<AcceptResponse> getAccept() {
        return api.getAccept();
    }

    public Call<ProjectResponse> getEvent() {
        return api.getEvent();
    }

    public Call<ProjectResponse> getEducation() {
        return api.getEducation();
    }

    public Call<ProjectResponse> getOther() {
        return api.getOther();
    }

    public Call<StudentResponse> updateStudent(int id, String phone, String line_account) {
        return api.updateStudent(id, phone, line_account);
    }
    public Call<SupervisorResponse> updateSupervisor(int id, String phone, String line_account) {
        return api.updateSupervisor(id, phone, line_account);
    }
    public Call<ProjectUserResponse> acceptStudent(int user_id, int project_id) {
        return api.acceptStudent(user_id, project_id);
    }
    public Call<ProjectUserResponse> declineStudent(int user_id, int project_id) {
        return api.declineStudent(user_id, project_id);
    }
    public Call<ProgressResponse> approveProgress(int progress_id, String comment) {
        return api.approveProgress(progress_id, comment);
    }
    public Call<ProgressResponse> declineProgress(int progress_id, String comment) {
        return api.declineProgress(progress_id, comment);
    }
    public Call<ProjectUserResponse> applyToAProject(int user_id, int project_id) {
        return api.applyToAProject(user_id, project_id);
    }


    public Call<JsonObject> logout() {
        return api.logout();
    }
}
