package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurButtonNewClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VueNewClasse extends GridPane {
    private CheckBox abstr, impl, exte, pub, pro, pri;
    private TextField impleName, nameField,extName;


    public VueNewClasse(ControleurButtonNewClass cont) {
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(8);
        setHgap(10);


        // Création des éléments de la vue
        Label name = new Label("Nom de la nouvelle classe :");
        nameField = new TextField();
        abstr = new CheckBox("classe abstraite");
        impl = new CheckBox("classe implémente");
        exte = new CheckBox("classe hérite");
        impleName = new TextField();
        impleName.setDisable(true);
        extName = new TextField();
        extName.setDisable(true);
        Button annuler = new Button("Annuler");
        annuler.setOnAction(cont);
        Button valider = new Button("Valider");
        valider.setOnAction(cont);


        valider.setAlignment(Pos.BASELINE_RIGHT);
        impl.setOnAction(event -> impleName.setDisable(!impl.isSelected()));
        exte.setOnAction(event -> extName.setDisable(!exte.isSelected()));





        pub = new CheckBox("Public");
        pri = new CheckBox("Private");
        pro = new CheckBox("Protected");

        pub.setOnAction(event -> {
            if (pub.isSelected()) {
                pri.setSelected(false);
                pro.setSelected(false);
            }
        });
        pri.setOnAction(event -> {
            if (pri.isSelected()) {
                pub.setSelected(false);
                pro.setSelected(false);
            }
        });
        pro.setOnAction(event -> {
            if (pro.isSelected()) {
                pub.setSelected(false);
                pri.setSelected(false);
            }
        });


        // Ajout des éléments au GridPane
        add(name, 0, 0);
        add(nameField, 1, 0);
        add(abstr, 0, 2);
        add(impl, 0, 3);
        add(impleName, 1, 3);
        add(exte, 0, 4);
        add(extName, 1, 4);

        add(pub,0,5);
        add(pri,0,6);
        add(pro,0,7);

        add(annuler, 0, 8);
        add(valider, 1, 8);

    }

    public String getTypeClass() {
        String res = "public";
        if(pub.isSelected()) res = "public";
        else if(pri.isSelected()) res = "private";
        else if(pro.isSelected()) res = "protected";

        if(abstr.isSelected()) res += " abstract";
        if(nameField.getText().matches("^[A-Z][A-z]+"))
            res += " " + nameField.getText();
        return res;
    }
}
