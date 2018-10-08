/**
 * Created by Matthieu J.B Capuano on 10/20/2017.
 */
public class mapElem<K, V> {
    private boolean removed;
    private K key;
    private V value;

    public mapElem(K key, V value) {
        this.key = key;
        this.value = value;

    }
}
