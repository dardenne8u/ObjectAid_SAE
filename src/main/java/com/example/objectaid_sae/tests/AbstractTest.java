package com.example.objectaid_sae.tests;

public abstract class AbstractTest {

    protected int attributHerite;

    abstract void methodeHeritee();

    protected AbstractTest(int num){
        attributHerite = num;
    }
}
