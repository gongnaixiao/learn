package api;

import com.alibaba.dubbo.common.URL;

import java.util.List;

/**
 * Created by xg on 2018/6/1.
 */
public interface RegistryService {
    void register(URL url);

    void unregister(URL url);

    void subscribe(URL url, NotifyListener listener);

    void unsubscribe(URL url, NotifyListener listener);

    List<URL> lookup(URL url);
}
