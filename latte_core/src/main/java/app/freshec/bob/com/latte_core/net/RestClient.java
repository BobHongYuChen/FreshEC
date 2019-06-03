package app.freshec.bob.com.latte_core.net;

import java.util.Map;
import java.util.WeakHashMap;

import app.freshec.bob.com.latte_core.net.callback.IError;
import app.freshec.bob.com.latte_core.net.callback.IFailure;
import app.freshec.bob.com.latte_core.net.callback.IRequest;
import app.freshec.bob.com.latte_core.net.callback.ISuccess;
import app.freshec.bob.com.latte_core.net.callback.RequestCallbacks;
import okhttp3.Call;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * 请求的具体实现类
 * 设计模式：建造者模式
 * */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    //创建建造者
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        //从creator中获取service,请求开始的时候做的
        final RestService service = RestCreator.getRestService();
        retrofit2.Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            //不影响UI线程进行启动
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }
    public final void post() {
        request(HttpMethod.POST);
    }
    public final void put() {
        request(HttpMethod.PUT);
    }
    public final void delete() {
        request(HttpMethod.DELETE);
    }






}
