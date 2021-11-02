package com.zhang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

public class RipTool {
    /**
     * 存的节点和rip表
     */
    public HashMap<String, Vector<Rip>> ripHashMap=new HashMap<>();
    /**
     * 存的节点和临界路由器
     */
    public HashMap<String,Vector<String>> nextRouter=new HashMap<>();
    public HashMap<String, Vector<Rip>> getRipHashMap() {
        return ripHashMap;
    }

    public void setRipHashMap(HashMap<String, Vector<Rip>> ripHashMap) {
        this.ripHashMap = ripHashMap;
    }

    public HashMap<String, Vector<String>> getNextRouter() {
        return nextRouter;
    }

    public void setNextRouter(HashMap<String, Vector<String>> nextRouter) {
        this.nextRouter = nextRouter;
    }

    /**
     * 根据路由表初始化
     * @param r 文件路径
     */
    void initRips(String r){
        try {
            BufferedReader in = new BufferedReader(new FileReader(r));
            String str;

            str=in.readLine();
            int cnt=Integer.parseInt(str);
            // 个数循环
            for (int i = 0; i < cnt; i++) {
                String name=in.readLine();

                String[] split = in.readLine()
                                   .split(" ");
                Vector<Rip> ripVector = new Vector<>();
                // 遍历路由表所有
                while (!Objects.equals(split[0], "0")){

                        ripVector.add(new Rip(Integer.parseInt(split[0]),Integer.parseInt(split[1]),split[2]));

                    split = in.readLine()
                              .split(" ");
                }
                this.ripHashMap.put(name,ripVector);

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 生成路由器的邻接表
     * @param r 文件路径
     */
    void initRouters(String r) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(r));
            String str;

            str = in.readLine();
            while (str != null) {
                String[] split = str.split(" ");
                Vector<String> stringVector = new Vector<>();
                // 除了第一个之外都放入vector
                for (int i = 1; i < split.length; i++) {
                    stringVector.add(split[i]);
                }
                //放入map
                this.nextRouter.put(split[0], stringVector);
                str = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void switchRips(){
        this.nextRouter.forEach((name,vec)->{
            for (int i = 0; i < vec.size(); i++) {
                //根据一个更新另一个路由表
                mergeRouters(name, vec.get(i));
            }
        });
    }
    void mergeRouters(String name,String nextName){
        Vector<Rip> oldVectorRip = this.ripHashMap.get(name);
        Vector<Rip> newVectorRip= this.ripHashMap.get(nextName);
        int i=0,j=0;
        Rip oldRip = null;
        Rip newRip=null;
        // 有种merge的思想在里面
        while (i<oldVectorRip.size()&&j< newVectorRip.size()){
            oldRip=oldVectorRip.get(i);
            newRip=newVectorRip.get(j);
            if(oldRip.getNet()<newRip.getNet()){
                //如果小，则直接放入
                newVectorRip.add(j,new Rip(oldRip.getNet(),oldRip.getDistance()+1,name));
                i++;
            }else if(oldRip.getNet()==newRip.getNet()){
                //如果目的相同，且下一跳相同，则无论如何都替换
                if (Objects.equals(name, newRip.getRouter())){
                    newRip.setDistance(oldRip.getDistance()+1);
                }else{
                    //否则选短的路
                    if (oldRip.getDistance()+1<=newRip.getDistance()){
                        newRip.setDistance(oldRip.getDistance()+1);
                        newRip.setRouter(name);
                    }
                }
                i++;
            }
            j++;
        }
        // 最大的部分最后加进去
        while (i < oldVectorRip.size()) {
            oldRip=oldVectorRip.get(i);
            newVectorRip.add(new Rip(oldRip.getNet(),oldRip.getDistance()+1,name));
            i++;
        }
    }
    void showAll(){
        this.ripHashMap.forEach((name,vec)->{
            System.out.println(name);
            vec.forEach(System.out::println);
        });
    }
}
