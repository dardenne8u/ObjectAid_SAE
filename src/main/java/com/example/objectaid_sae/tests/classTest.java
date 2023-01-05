package com.example.objectaid_sae.tests;

public class classTest extends AbstractTest implements InterfaceTest{

    private String attributDeclare;

    protected classTest(int num) {
        super(num);
    }

    @Override
    protected void methodeHeriteeAb() {

    }

    public int AtributPublique;


    public String methodeDeclare(){
        return attributDeclare;
    }
}
