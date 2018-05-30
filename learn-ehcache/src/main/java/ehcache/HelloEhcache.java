package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class HelloEhcache {

    /*
    public static void main(String[] args) {

        //1. Create a cache manager
        CacheManager cm = CacheManager.getInstance();

        //2. Create a cache called "cache1"
        cm.addCache("cache1");

        //3. Get a cache called "cache1"
        Cache cache = cm.getCache("cache1");

        //4. Put few elements in cache
        cache.put(new Element("1", "Jan"));
        cache.put(new Element("2", "Feb"));
        cache.put(new Element("3", "Mar"));

        //5. Get element from cache
        Element ele = cache.get("1");

        //6. Print out the element
        String output = (ele == null ? null : ele.getObjectValue().toString());
        System.out.println(output);

        //7. Is key in cache?
        System.out.println(cache.isKeyInCache("1"));
        System.out.println(cache.isKeyInCache("5"));

        //8. shut down the cache manager
        cm.shutdown();

    }
    */
    public static void main(String[] args) {

        //1. Create a cache manager
        CacheManager cm = CacheManager.newInstance();

        //cm.addCache("cache1");

        //2. Get a cache called "cache1", declared in ehcache.xml
        Cache cache = cm.getCache("cache1");

        //3. Put few elements in cache
        cache.put(new Element("1","Jan"));
        cache.put(new Element("2","Feb"));
        cache.put(new Element("3","Mar"));

        //4. Get element from cache
        Element ele = cache.get("2");

        //5. Print out the element
        String output = (ele == null ? null : ele.getObjectValue().toString());
        System.out.println(output);

        //6. Is key in cache?
        System.out.println(cache.isKeyInCache("3"));
        System.out.println(cache.isKeyInCache("10"));

        //7. shut down the cache manager
        cm.shutdown();

    }


}
