package com.jyoti.demo;

/*class A {
    public int i;
    public int j;
}

class B extends A {
    void display() {
        super.j = super.i + 1;
        System.out.println(super.i + ":" + super.j);
        System.out.println(this.i + ":" + this.j);
    }
}

public class demo {
    public static void main(String[] args) {
        B b = new B();
        b.i = 2;
        b.j = 3;
        b.display();
    }
}*/

abstract class demoA {
    public int a;
    String s = "                        ";
    demoA() {
        a = 10;
    }
    abstract public void set();
    abstract  public void get();
}

class demoB extends demoA {

    @Override
    public void set() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public final void get() {
        // TODO Auto-generated method stub
        
    }
    
}
class A {
    int add (int i, int j) {
        return i+j;
    }
}

public class demo extends A {
    public static void main(String[] args) {
        short s = 9;
        //System.out.println(add(s, 6));
    }
    
}

interface intA {
    public static final String NAME = "XXXX";
}
