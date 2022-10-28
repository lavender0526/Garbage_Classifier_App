package com.example.sa.Iterator;

public class ConcreteAggregate implements Aggregate{
    private String[]list;
    private int index=0;
    public ConcreteAggregate(){
        this.list=new String[100];
    }
    public void add(String item){
        System.out.println("add susceed");
        this.list[index] = item;
        index++;
    }

    public Iterator createIterator(){
        return new ConcreteIterator();
    }
    private class ConcreteIterator implements Iterator{
        public int pos=0;
        public String First(){
            return list[pos];
        }
        public boolean hasNext(){
            if(pos> list.length||list[pos]==null){
                return false;
            }
            return true;
        }
        public String next(){
            if(this.hasNext()){
                return list[pos++];
            }
            return null;
        }
    }

}
