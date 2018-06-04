import com.alibaba.dubbo.common.URL;
import com.gongnaixiao.learn.dubbo.api.NotifyListener;
import com.gongnaixiao.learn.dubbo.api.Registry;

import java.io.File;
import java.util.List;

/**
 * Created by xg on 2018/6/1.
 */
public class AbstractRegistry implements Registry {
    private URL registryUrl;
    // Local disk cache file
    private File file;

    public AbstractRegistry(URL url) {
        setUrl(url);
    }

    @Override
    public URL getUrl() {
        return null;
    }
    protected void setUrl(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("registry url == null");
        }
        this.registryUrl = url;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void register(URL url) {

    }

    @Override
    public void unregister(URL url) {

    }

    @Override
    public void subscribe(URL url, NotifyListener listener) {

    }

    @Override
    public void unsubscribe(URL url, NotifyListener listener) {

    }

    @Override
    public List<URL> lookup(URL url) {
        return null;
    }
}
