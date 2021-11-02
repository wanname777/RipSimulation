package com.zhang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

public class Main {
    /**
     * 路由器连接表
     * 本路由器 相邻的路由器（跳过网络） 0结束符
     * A B D E 0
     * B A C 0
     * C B F 0
     * D A E F 0
     * E A D F 0
     * F C D E 0
     *
     *
     */
    /**
     * 路由器个数，路由器名称
     * 目的网络 距离 下一跳路由器

     *
     */
    public static void main(String[] args) {
	// write your code here
        RipTool ripTool = new RipTool();
        ripTool.initRips("C:\\Users\\zhang\\IdeaProjects\\RipSimulation\\src\\com" +
                "\\zhang\\data\\initRips.txt");
        // System.out.println(ripTool.getRipHashMap());
        ripTool.initRouters("C:\\Users\\zhang\\IdeaProjects\\RipSimulation\\src" +
                "\\com\\zhang\\data\\nets.txt");

        ripTool.switchRips();
        // ripTool.switchRips();
        // ripTool.switchRips();
        // ripTool.switchRips();
        ripTool.showAll();
    }




}
