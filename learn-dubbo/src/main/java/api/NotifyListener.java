package api;

import com.alibaba.dubbo.common.URL;

import java.util.List;

/**
 * Created by xg on 2018/6/1.
 */
public interface NotifyListener {
    void notify(List<URL> urls);
}
