package com.example.testandroid;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

import com.shouzhong.scanner.Callback;
import com.shouzhong.scanner.IViewFinder;
import com.shouzhong.scanner.Result;
import com.shouzhong.scanner.ScannerView;

import cn.iwgang.licenseplatediscern.view.LicensePlateDiscernView;


public class MainActivity extends AppCompatActivity {
    private LicensePlateDiscernView cvLicensePlateDiscernView;
    private ScannerView scannerView;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LicensePlateDiscernCore.Companion.init(this);
//        // 通过 ID 获取 LicensePlateDiscernView
//        cvLicensePlateDiscernView = findViewById(R.id.cv_licensePlateDiscernView);
//        // 设置车牌识别回调监听器
//        cvLicensePlateDiscernView.setOnDiscernListener(new Function1<LicensePlateInfo, Unit>() {
//            @Override
//            public Unit invoke(LicensePlateInfo result) {
//                // 处理回调逻辑
//                String scanResult = "识别结果：" + result.getLicensePlate() + "（可信度：" + result.getConfidence() + "）";
//                System.out.println(scanResult);
//
//                // 重新识别
//                cvLicensePlateDiscernView.reDiscern();
//
//                return Unit.INSTANCE; // 必须返回 Unit.INSTANCE
//            }
//        });
        scannerView = findViewById(R.id.sv);
        scannerView.setShouldAdjustFocusArea(true);
        scannerView.setViewFinder(new ViewFinder2());
        scannerView.setRotateDegree90Recognition(true);
        scannerView.setEnableLicensePlate(true);
        scannerView.setCallback(new Callback() {
            @Override
            public void result(Result result) {
                startVibrator();
                System.out.println("扫描成功");
                String scanResult = "识别结果：" + result.data ;
                System.out.println(scanResult);
                scannerView.restartPreviewAfterDelay(2000);
            }
        });
    }

    private void startVibrator() {
        if (vibrator == null)
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(300);
    }


    class ViewFinder2 implements IViewFinder {
        @Override
        public Rect getFramingRect() {
            return new Rect(240, 240, 840, 840);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.onPause();
    }
}

