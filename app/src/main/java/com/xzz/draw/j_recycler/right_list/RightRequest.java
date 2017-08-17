package com.xzz.draw.j_recycler.right_list;

import com.xzz.draw.j_recycler.socket.Api;
import com.xzz.draw.j_recycler.socket.HttpSocket;
import com.xzz.draw.j_recycler.socket.Params;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class RightRequest extends HttpSocket<RightModel,RightRequest.RightListReuestInterface> {

    private String pdduid = "";

    @Override
    public void setParams(Params params) {
        params.put("opt_type","2");
        params.put("offset","0");
        params.put("size","50");
        params.put("pdduid",pdduid);
    }

    @Override
    public Class<RightListReuestInterface> getRequestClass() {
        return RightListReuestInterface.class;
    }

    @Override
    public Observable<RightModel> getObservable(RightListReuestInterface rightListReuestInterface, Params params) {
        return rightListReuestInterface.getRequestRightList(params);
    }

    public interface RightListReuestInterface {
        @GET(Api.RIGHT_LIST)
        Observable<RightModel> getRequestRightList(@QueryMap Params params);
    }

    public RightRequest setPdduid(String pdduid){
        this.pdduid = pdduid;
        return this;
    }
}
