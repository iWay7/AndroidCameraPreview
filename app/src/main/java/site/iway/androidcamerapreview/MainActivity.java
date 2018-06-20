package site.iway.androidcamerapreview;

import android.Manifest.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import site.iway.androidhelpers.CameraPreview;
import site.iway.androidhelpers.CameraPreview.OnPreviewErrorListener;
import site.iway.androidhelpers.CameraPreview.OnQRCodeDetectedListener;

public class MainActivity extends Activity {

    private CameraPreview mCameraPreview;

    private void setViews() {
        setContentView(R.layout.activity_main);
        mCameraPreview = findViewById(R.id.cameraPreview);
        mCameraPreview.setOnQRCodeDetectedListener(new OnQRCodeDetectedListener() {
            @Override
            public void onQRCodeDetected(String qrCode) {
                Toast.makeText(MainActivity.this, qrCode, Toast.LENGTH_SHORT).show();
            }
        });
        mCameraPreview.setOnPreviewErrorListener(new OnPreviewErrorListener() {
            @Override
            public void onPreviewError(Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.camera0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraPreview.setCameraId(0);
            }
        });
        findViewById(R.id.camera1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraPreview.setCameraId(1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            if (checkSelfPermission(permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                setViews();
            } else {
                String[] permissions = new String[1];
                permissions[0] = permission.CAMERA;
                requestPermissions(permissions, 0);
            }
        } else {
            setViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setViews();
        }
    }

}