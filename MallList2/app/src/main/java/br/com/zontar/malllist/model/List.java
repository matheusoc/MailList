package br.com.zontar.malllist.model;

/**
 * Created by MatheusdeOliveiraCam on 29/08/2017.
 */

public class List {

    private int idList;
    private String nameList;

    public List() {
    }

    public List(int idList, String nameList) {
        this.idList = idList;
        this.nameList = nameList;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
