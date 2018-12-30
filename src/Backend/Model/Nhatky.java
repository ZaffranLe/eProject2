/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vukho
 */
@Entity
@Table(name = "nhatky", catalog = "TaskManagement", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nhatky.findAll", query = "SELECT n FROM Nhatky n")
    , @NamedQuery(name = "Nhatky.findById", query = "SELECT n FROM Nhatky n WHERE n.id = :id")
    , @NamedQuery(name = "Nhatky.findByNoiDung", query = "SELECT n FROM Nhatky n WHERE n.noiDung = :noiDung")
    , @NamedQuery(name = "Nhatky.findByThoiGian", query = "SELECT n FROM Nhatky n WHERE n.thoiGian = :thoiGian")})
public class Nhatky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NoiDung", nullable = false, length = 100)
    private String noiDung;
    @Basic(optional = false)
    @Column(name = "ThoiGian", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGian;
    @JoinColumn(name = "IDDuAn", referencedColumnName = "IDDuAn", nullable = false)
    @ManyToOne(optional = false)
    private Duan iDDuAn;

    public Nhatky() {
    }

    public Nhatky(Integer id) {
        this.id = id;
    }

    public Nhatky(Integer id, String noiDung, Date thoiGian) {
        this.id = id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Duan getIDDuAn() {
        return iDDuAn;
    }

    public void setIDDuAn(Duan iDDuAn) {
        this.iDDuAn = iDDuAn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhatky)) {
            return false;
        }
        Nhatky other = (Nhatky) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Backend.Model.Nhatky[ id=" + id + " ]";
    }
    
}
