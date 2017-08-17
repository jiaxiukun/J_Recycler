package com.xzz.draw.j_recycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xzz.draw.j_recycler.adapter.CommonAdapter;
import com.xzz.draw.j_recycler.adapter.ViewHolder;
import com.xzz.draw.j_recycler.lift_list.LiftModel;
import com.xzz.draw.j_recycler.lift_list.LiftRequest;
import com.xzz.draw.j_recycler.right_list.RightModel;
import com.xzz.draw.j_recycler.right_list.RightRequest;
import com.xzz.draw.j_recycler.socket.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mLiftRecy,mRigrtRecy;
    private LiftRequest mLiftRequest;
    private RightRequest mRightRequest;
    private CommonAdapter<LiftModel.OptInfosBean> mLiftAdapter;
    private CommonAdapter<RightModel.OptInfosBean> mRightAdapter;
    private List<LiftModel.OptInfosBean> mliftList = new ArrayList<>();
    private List<RightModel.OptInfosBean> mRightList = new ArrayList<>();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        bindView();
        httpRequest();
    }

    private void bindView() {
        mLiftRecy.setLayoutManager(new LinearLayoutManager(this));
        mLiftRecy.setAdapter(mLiftAdapter);

        mRigrtRecy.setLayoutManager(new LinearLayoutManager(this));
        mRigrtRecy.setAdapter(mRightAdapter);
    }

    private void httpRequest() {
        // 发起请求获取左侧数据
        mLiftRequest.request(new HttpRequest<LiftModel>() {
            @Override
            public void onSuccedd(LiftModel liftModel) {
                mliftList.addAll(liftModel.getOpt_infos());
                mLiftAdapter.setData(liftModel.getOpt_infos());
            }
        });
        // 发起请求获取右侧数据
        mRightRequest.request(new HttpRequest<RightModel>() {
            @Override
            public void onSuccedd(RightModel rightModel) {
                mRightAdapter.setData(rightModel.getOpt_infos());
            }
        });
    }

    private void initData() {
        // 左侧网络请求对象
        mLiftRequest = new LiftRequest();
        // 左侧适配器
        mLiftAdapter = new CommonAdapter<LiftModel.OptInfosBean>(this,R.layout.item_lift_layout,mliftList) {
            @Override
            public void conver(ViewHolder viewHodler, LiftModel.OptInfosBean o) {
                viewHodler.setText(R.id.item_lift_text,o.getOpt_name())
                .setOnItemClickLisenter(new ViewHolder.OnItemClickLisenter() {
                    @Override
                    public void onItemClickLisenter(View view, int position) {
                        if (!mliftList.isEmpty()){
                            mRightRequest.setPdduid(mliftList.get(position).getPriority()+"")
                                    .request(new HttpRequest<RightModel>() {
                                        @Override
                                        public void onSuccedd(RightModel rightModel) {
                                            Toast.makeText(MainActivity.this,rightModel.getOpt_infos().size()+"___",Toast.LENGTH_SHORT).show();
                                            mRightAdapter.setData(rightModel.getOpt_infos());
                                        }
                                    });
                        }
                    }
                });
            }
        };
        // 右侧网络请求对象 + 适配器
        mRightRequest = new RightRequest();
        mRightAdapter = new CommonAdapter<RightModel.OptInfosBean>(this,R.layout.item_right_layout,mRightList){
            @Override
            public void conver(ViewHolder viewHodler, RightModel.OptInfosBean o) {
                viewHodler.setText(R.id.right_text,o.getOpt_name())
                        .setimageResouse(R.id.right_image,R.mipmap.ic_launcher)
                        .setOnItemClickLisenter(new ViewHolder.OnItemClickLisenter() {
                            @Override
                            public void onItemClickLisenter(View view, int position) {
                                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        };
    }

    private void initView() {
        mLiftRecy = (RecyclerView) findViewById(R.id.left_recycler);
        mLiftRecy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRigrtRecy = (RecyclerView) findViewById(R.id.right_recycler);
    }
}
