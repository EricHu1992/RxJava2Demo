package com.eric.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RxFlowableDemo.flowableDemo2();
    }

    @OnClick(R.id.demo1)
    public void onClickDemo1Btn() {
        RxDemo.demo1();
    }

    @OnClick(R.id.demo2)
    public void onClickDemo2Btn() {
        RxDemo.demo2();
    }

    @OnClick(R.id.demoMap)
    public void onClickDemoMapBtn() {
        RxDemo.mapDemo();
    }

    @OnClick(R.id.demoFlatMap)
    public void onClickDemoFlatMapBtn() {
        RxDemo.flatMapDemo();
    }

    @OnClick(R.id.demoConcatMap)
    public void onClickDemoConcatMapBtn() {
        RxDemo.concatMap();
    }

    @OnClick(R.id.demoZip)
    public void onClickDemoZipBtn() {
        RxDemo.zipDemo();
    }

    @OnClick(R.id.demoFlowable)
    public void onClickDemoFlowableBtn() {
        RxFlowableDemo.request(1);
    }

}
