package zzy.unionfind;

/**
 * 并查集接口
 */
public interface UF {
    int size();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
