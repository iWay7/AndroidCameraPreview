# AndroidCameraPreview
Android 相机预览，含二维码识别。

### 本应用的示例

![image](https://github.com/iWay7/AndroidCameraPreview/blob/master/sample.gif)   

### 本示例基于 AndroidHelpers 库，访问 https://github.com/iWay7/AndroidHelpers 添加依赖。

#### 开始使用：
##### 在布局文件中声明一个 AndroidCameraPreview 视图：
```
<site.iway.androidhelpers.CameraPreview
    android:id="@+id/cameraPreview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:cameraId="0"
    app:detectQRCode="true"
    app:focusByClick="true" />
```

##### 其中的属性如下：
```
app:cameraId // Camera Id
app:detectQRCode // 是否识别二维码
app:focusByClick // 是否进行点击对焦
```

##### 通过代码设置 Camera Id
```
mCameraPreview.setCameraId(...); // 通常 0/1 代表 后置摄像头/前置摄像头。
```

##### 设置二维码识别和错误监听器
```
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
```

##### 如果遇到错误（例如权限问题）需要重新初始化相机，调用 reInitialize 即可。
```
mCameraPreview.reInitialize();
```

##### 权限问题的处理，可以使用示例中的代码。