package com.violet.algorithm.hash;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/8 下午3:56
 * @since 1.0.0
 */
public class ConsistentHashTest {

    @Test
    @SuppressWarnings("all")
    public void consistentHash() throws Exception {
        List<HashNode> nodes = new ArrayList<>();
        nodes.add(new Node("10.25.6.103"));
        nodes.add(new Node("10.25.6.62"));
        nodes.add(new Node("10.25.6.49"));

        ConsistentHash<HashNode> consistentHash = new ConsistentHash<>(nodes);
        consistentHash.add(new Node("10.25.6.214"));

        System.out.println(consistentHash.size());

        Field circleField = consistentHash.getClass().getDeclaredField("circle");
        circleField.setAccessible(true);
        SortedMap<Long,HashNode> circle = (SortedMap<Long,HashNode>)circleField.get(consistentHash);
        long last = 0;
        for (Map.Entry<Long, HashNode> longHashNodeEntry : circle.entrySet()) {
            System.out.println(longHashNodeEntry.getKey()+" = "+longHashNodeEntry.getValue().getNodeName()+" : "+(longHashNodeEntry.getKey()-last));
            last = longHashNodeEntry.getKey();
        }


        System.out.println(consistentHash.get("USER:AGE:NUMBER").getNodeName());
        System.out.println(consistentHash.get("ROLE:GRADE:INDEX").getNodeName());
        System.out.println(consistentHash.get("PERSON:NAME:INDEX").getNodeName());


        consistentHash.remove("10.25.6.62");
        System.out.println(consistentHash.get("USER:AGE:NUMBER").getNodeName());
        System.out.println(consistentHash.get("ROLE:GRADE:INDEX").getNodeName());
        System.out.println(consistentHash.get("PERSON:NAME:INDEX").getNodeName());
    }


    public static class Node implements HashNode {

        private String nodeName;

        public Node(String nodeName) {
            this.nodeName = nodeName;
        }

        @Override
        public String getNodeName() {
            return nodeName;
        }
    }
}
