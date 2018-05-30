package ehcache;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Dog {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private short year;

    public Dog(long id, String name, short year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

}