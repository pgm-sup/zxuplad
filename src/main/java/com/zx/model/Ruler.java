package com.zx.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author pgm_sup
 */
@Entity
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "col")
    private String col;

    @Column(name = "val")
    private String val;

    public Ruler() {
    }


    public Ruler(String col, String val) {
        this.col = col;
        this.val = val;
    }



    //    //可选属性optional=false,表示Knowledge不能为空。删除Ruler，不影响Knowledge
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
//    @JoinColumn(name="knowledge_id")
//    private Knowledge knowledge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ruler{" +
                "id=" + id +
                ", col='" + col + '\'' +
                ", val='" + val + '\'' +
                '}';
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruler ruler = (Ruler) o;
        return Objects.equals(col, ruler.col) &&
                Objects.equals(val, ruler.val);
    }

    @Override
    public int hashCode() {

        return Objects.hash(col, val);
    }
}
