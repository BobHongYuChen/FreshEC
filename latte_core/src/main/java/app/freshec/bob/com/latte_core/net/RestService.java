package app.freshec.bob.com.latte_core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 具体请求
 * */
public interface RestService {

    //请求的返回值,通用的封装
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    //POST请求
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    //PUT请求
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    //DELETE请求
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    //DownLoad请求,返回请求体，但是当文件过大的时候下载会出现内存溢出，采用Streaming注解（边下载边写入）
    //需要异步处理
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    //UPLOAD请求
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
