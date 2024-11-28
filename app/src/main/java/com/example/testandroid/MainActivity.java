package com.example.testandroid;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import cn.iwgang.licenseplatediscern.LicensePlateDiscernCore;
import cn.iwgang.licenseplatediscern.LicensePlateInfo;
import cn.iwgang.licenseplatediscern.view.LicensePlateDiscernView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class MainActivity extends AppCompatActivity {
    private LicensePlateDiscernView cvLicensePlateDiscernView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LicensePlateDiscernCore.Companion.init(this);
        // 通过 ID 获取 LicensePlateDiscernView
        cvLicensePlateDiscernView = findViewById(R.id.cv_licensePlateDiscernView);
        // 设置车牌识别回调监听器
        cvLicensePlateDiscernView.setOnDiscernListener(new Function1<LicensePlateInfo, Unit>() {
            @Override
            public Unit invoke(LicensePlateInfo result) {
                // 处理回调逻辑
                String scanResult = "识别结果：" + result.getLicensePlate() + "（可信度：" + result.getConfidence() + "）";
                System.out.println(scanResult);

                // 重新识别
                cvLicensePlateDiscernView.reDiscern();

                return Unit.INSTANCE; // 必须返回 Unit.INSTANCE
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

