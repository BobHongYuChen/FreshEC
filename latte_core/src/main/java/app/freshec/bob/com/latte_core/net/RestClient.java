package app.freshec.bob.com.latte_core.net;

import java.util.Map;
import java.util.WeakHashMap;

import app.freshec.bob.com.latte_core.net.callback.IError;
import app.freshec.bob.com.latte_core.net.callback.IFailure;
import app.freshec.bob.com.latte_core.net.callback.IRequest;
import app.freshec.bob.com.latte_core.net.callback.ISuccess;
import okhttp3.RequestBody;

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

    //从creator中获取service
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
    }



}
