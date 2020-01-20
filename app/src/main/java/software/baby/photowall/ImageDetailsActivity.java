package software.baby.photowall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import software.baby.photowall.image.Images;
import software.baby.photowall.view.ViewPagerAdapter;

public class ImageDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private TextView pageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();  //去掉TitleBar
        }

        int imagePosition = getIntent().getIntExtra("image_position", 0);
        pageText = findViewById(R.id.page_text);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imagePosition);
        viewPager.setOnPageChangeListener(this);
        // 设定当前的页数和总页数
        pageText.setText((imagePosition + 1) + "/" + Images.imageUrls.length);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        pageText.setText((position + 1) + "/" + Images.imageUrls.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
