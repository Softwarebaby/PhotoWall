package software.baby.photowall.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.io.File;

import software.baby.photowall.R;
import software.baby.photowall.image.Images;

/**
 * Created by Du Senmiao on 2020/01/20
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    /**
     * 在给定的位置进行Item的初始化
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String imagePath = getImagePath(Images.imageUrls[position]);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(container.getResources(), R.drawable.empty_photo);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_zoom_image_view, null);
        ZoomImageView zoomImageView = view.findViewById(R.id.zoom_image_view);
        zoomImageView.setImageBitmap(bitmap);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return Images.imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    /**
     * 获取图片的本地存储路径。
     *
     * @param imageUrl
     *            图片的URL地址。
     * @return 图片的本地存储路径。
     */
    private String getImagePath(String imageUrl) {
        int lastSlashIndex = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(lastSlashIndex + 1);
        String imageDir = Environment.getExternalStorageDirectory().getPath()
                + "/PhotoWall/";
        File file = new File(imageDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String imagePath = imageDir + imageName;
        return imagePath;
    }

}
