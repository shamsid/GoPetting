package com.shamsid.gopetting.network;

import com.shamsid.gopetting.models.Pojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Contructing Url by using Retrofit

public interface Api {
 @GET("{guide}")
 Call<Pojo> getPlayer (@Path("guide") String guide);
}
