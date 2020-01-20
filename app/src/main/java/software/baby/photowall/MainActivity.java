package software.baby.photowall;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import software.baby.photowall.util.PermissionsChecker;
import software.baby.photowall.view.MyScrollView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;

    private static final String[] PERMISSIONS = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private PermissionsChecker permissionsChecker;
    private MyScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myScrollView = findViewById(R.id.my_scroll_view);

        permissionsChecker = new PermissionsChecker(this);
        checkExternalStoragePermission();
    }

    private void checkExternalStoragePermission() {
        //Android6.0运行时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!permissionsChecker.checkPermissions(PERMISSIONS)) {
                requestPermissions(PERMISSIONS, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission Denied
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            } else {
                myScrollView.reloadImages();
            }
        }
    }
}
