package ru.job4j.ood.lsp;

public class Square extends Rectangle {

    @Override
    public void setWeight(int weight) {
     this.weight = weight;
       this.hight = weight;
    }

    @Override
    public void setHight(int hight) {
       this.hight = hight;
       this.weight = hight;
    }
}
