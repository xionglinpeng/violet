package com.violet.algorithm.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>一致性哈希</p>
 *
 * @author xlp
 * @date 2020/5/8 下午2:35
 * @since 1.0.0
 */
public class ConsistentHash<T extends HashNode> {

    /**
     * 默认虚拟节点数量
     */
    private static final int DEFAULT_VIRTUAL_NODE = 1 << 5;

    /**
     * 虚拟机节点数量
     */
    private int virtualNode = DEFAULT_VIRTUAL_NODE;

    /**
     * 虚拟机节点名称索引分隔符
     */
    private static final String VIRTUAL_NODE_SEPARATOR = "#";

    /**
     * hash环
     */
    private SortedMap<Long,T> circle = new TreeMap<>();


    public ConsistentHash() {
    }

    public ConsistentHash(Collection<T> nodes) {
        this(nodes,DEFAULT_VIRTUAL_NODE);
    }

    public ConsistentHash(int virtualNode) {
        this(null,virtualNode);
    }

    /**
     * 设置初始化节点和虚拟节点数量。
     * @param nodes 初始化节点。
     * @param virtualNode 虚拟节点数量。
     */
    public ConsistentHash(Collection<T> nodes,int virtualNode) {
        initNode(nodes);
        if (virtualNode >= DEFAULT_VIRTUAL_NODE) {
            this.virtualNode = virtualNode;
        }
    }

    /**
     * 初始化hash环节点。
     * @param nodes 初始化节点。
     */
    public void initNode(Collection<T> nodes){
        if (!circle.isEmpty()) {
            throw new IllegalStateException("hash环节点已初始化");
        }
        if (nodes != null && !nodes.isEmpty()) {
            nodes.forEach(this::add);
        }
    }

    /**
     * 添加节点，每个节点将生成{@link #virtualNode}个虚拟节点。
     * @param node 节点。
     */
    public void add(T node) {
        for (int i = 0; i < virtualNode; i++){
            circle.put(hash(node.getNodeName()+ VIRTUAL_NODE_SEPARATOR + i),node);
        }
    }

    /**
     * 删除节点。
     * @param node 节点。
     */
    public void remove(T node){
        remove(node.getNodeName());
    }

    /**
     * 删除节点。
     * @param name 节点名称。
     */
    public void remove(String name){
        for (int i = 0; i < virtualNode; i++){
            circle.remove(hash(name+ VIRTUAL_NODE_SEPARATOR + i));
        }
    }

    /**
     * 获取对应key临近节点。
     * @param key key。
     * @return key临近节点。
     */
    public T get(String key) {
        if (key == null) {
            return null;
        }
        long hash = hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty()?circle.firstKey():tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public int size(){
        return circle.size();
    }

    private long hash(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("The MD5 algorithm was not found",e);
        }
        md5.reset();
        md5.update(key.getBytes());
        byte[] digest = md5.digest();
        return (((long)(digest[3] & 0xFF) << 24)
               |((long)(digest[2] & 0xFF) << 16)
               |((long)(digest[1] & 0xFF) << 8)
               | (long)(digest[0] & 0xFF))
               & 0xffffffffL;
    }
}
