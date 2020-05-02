package es.sarascript.catasaserviceapp;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface CatService {

    @GET("images/search?size=full")
    Call<Cat[]> getCats();

}
