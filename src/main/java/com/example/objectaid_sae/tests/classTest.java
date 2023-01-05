package com.example.objectaid_sae.tests;

public class classTest extends AbstractTest implements InterfaceTest{

    private String attributDeclare;

    protected classTest(int num) {
        super(num);
    }

    public int AtributPublique;

    @Override
    public void methodeHeritee() {

    }

    public String methodeDeclare(){
        return attributDeclare;
    }
}
