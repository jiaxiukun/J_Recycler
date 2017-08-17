package com.xzz.draw.j_recycler.lift_list;

import com.xzz.draw.j_recycler.socket.Api;
import com.xzz.draw.j_recycler.socket.HttpSocket;
import com.xzz.draw.j_recycler.socket.Params;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class LiftRequest extends HttpSocket<LiftModel,LiftRequest.LiftRequeqtInterface> {


    @Override
    public void setParams(Params params) {
        params.put("opt_type","1");
        params.put("offset","0");
        params.put("sort_type","DEFAULT");
        params.put("size","50");
        params.put("pdduid","");
    }

    @Override
    public Class<LiftRequeqtInterface> getRequestClass() {
        return LiftRequeqtInterface.class;
    }

    @Override
    public Observable<LiftModel> getObservable(LiftRequeqtInterface liftRequeqtInterface, Params params) {
        return liftRequeqtInterface.getRequestLiftList(params);
    }

    public interface LiftRequeqtInterface {
        @GET(Api.LIFT_LIST)
        Observable<LiftModel> getRequestLiftList(@QueryMap Params params);
    }
}
