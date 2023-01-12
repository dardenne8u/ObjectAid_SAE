package com.example.objectaid_sae.DiffClassTest;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Fleche;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class classTest extends AbstractTest implements InterfaceTest{

    private String attributDeclare;

    private int[] tableauInteger;
    private Fleche[] tableauFleche;
    private List<Integer> list;
    private Map<Integer, AbstractTest> map;

    private Set<Analyseur> set;

    public classTest(int num) {
        super(num);
    }

    @Override
    protected void methodeHeriteeAb() {

    }

    public int AtributPublique;


    public String methodeDeclare(){
        return attributDeclare;
    }
    public static void nothing(){}
}
