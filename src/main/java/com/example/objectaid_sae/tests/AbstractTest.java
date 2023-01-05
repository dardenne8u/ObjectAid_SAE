package com.example.objectaid_sae.tests;

public abstract class AbstractTest {

    protected int attributHerite;

    public void methodeHeritee(){}

    protected void methodeProtectedHeritee(){}

    protected AbstractTest(int num){
        attributHerite = num;
    }

    protected abstract void methodeHeriteeAb();
}
