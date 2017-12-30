package com.gearglen.lock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class InvisibleActivity extends Activity {

    private DevicePolicyManager dpm;
    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取得系统服务
        dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(InvisibleActivity.this, LockReceiver.class);

        //添加一个隐式意图，完成设备权限的添加
        //这个Intent (DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)跳转到 权限提醒页面
        //并传递了两个参数EXTRA_DEVICE_ADMIN 、 EXTRA_ADD_EXPLANATION
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

        //权限列表
        //EXTRA_DEVICE_ADMIN参数中说明了用到哪些权限，
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);

        //描述(additional explanation)
        //EXTRA_ADD_EXPLANATION参数为附加的说明
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "锁屏");
        startActivityForResult(intent, 0);

        dpm.lockNow(); // 锁屏

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
