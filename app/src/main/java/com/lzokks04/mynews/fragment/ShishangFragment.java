package com.lzokks04.mynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lzokks04.mynews.R;
import com.lzokks04.mynews.activity.WebActivity;
import com.lzokks04.mynews.adapter.MyRecyclerViewAdapter;
import com.lzokks04.mynews.bean.NewsBean;
import com.lzokks04.mynews.util.Api;
import com.lzokks04.mynews.util.NewsService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Liu on 2016/9/5.
 */
public class ShishangFragment extends Fragment{
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    private MyRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getNews();
    }

    private void getNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        newsService.getNews("shishang", Api.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mjj", e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        initRecyclerView(newsBean);
                    }
                });
    }

    private void initRecyclerView(final NewsBean newsBean) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(adapter = new MyRecyclerViewAdapter(getContext(), newsBean));
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",newsBean.getResult().getData().get(postion).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
