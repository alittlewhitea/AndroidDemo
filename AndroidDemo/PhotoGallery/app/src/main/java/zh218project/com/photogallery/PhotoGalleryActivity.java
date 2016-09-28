package zh218project.com.photogallery;

import android.app.Fragment;

/**
 * 沿用之前的设计框架，继承SingleFragmentActivity,并且实现createFragment方法
 * createFragment方法将返回一个PhotoGalleryFragment类实例
 */

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
