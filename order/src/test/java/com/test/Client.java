package com.test;

import com.order.pojo.TokenAuthention;
import com.order.util.JsonUtil;

public class Client {
    public static void main(String[] args) {
       Node root =new Node(0);
       root.append(1);
       root.append(2);
       root.append(3);
       root.print();

       root.remove(1);
    }
}
