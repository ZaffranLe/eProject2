/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vukho
 */
@Entity
@Table(name = "nguoidung", catalog = "TaskManagement", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nguoidung.findAll", query = "SELECT n FROM Nguoidung n")
    , @NamedQuery(name = "Nguoidung.findById", query = "SELECT n FROM Nguoidung n WHERE n.id = :id")
    , @NamedQuery(name = "Nguoidung.findByEmail", query = "SELECT n FROM Nguoidung n WHERE n.email = :email")
    , @NamedQuery(name = "Nguoidung.findByMatKhau", query = "SELECT n FROM Nguoidung n WHERE n.matKhau = :matKhau")
    , @NamedQuery(name = "Nguoidung.findByHoTen", query = "SELECT n FROM Nguoidung n WHERE n.hoTen = :hoTen")
    , @NamedQuery(name = "Nguoidung.findBySdt", query = "SELECT n FROM Nguoidung n WHERE n.sdt = :sdt")
    , @NamedQuery(name = "Nguoidung.findByDiaChi", query = "SELECT n FROM Nguoidung n WHERE n.diaChi = :diaChi")})
public class Nguoidung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Email", nullable = false, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "MatKhau", nullable = false, length = 100)
    private String matKhau;
    @Basic(optional = false)
    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;
    @Basic(optional = false)
    @Column(name = "SDT", nullable = false, length = 10)
    private String sdt;
    @Basic(optional = false)
    @Column(name = "DiaChi", nullable = false, length = 1000)
    private String diaChi;
    @ManyToMany(mappedBy = "nguoidungCollection")
    private Collection<Noidung> noidungCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nguoidung")
    private Collection<NguoidungDuan> nguoidungDuanCollection;

    public Nguoidung() {
    }

    public Nguoidung(Integer id) {
        this.id = id;
    }

    public Nguoidung(Integer id, String email, String matKhau, String hoTen, String sdt, String diaChi) {
        this.id = id;
        this.email = email;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @XmlTransient
    public Collection<Noidung> getNoidungCollection() {
        return noidungCollection;
    }

    public void setNoidungCollection(Collection<Noidung> noidungCollection) {
        this.noidungCollection = noidungCollection;
    }

    @XmlTransient
    public Collection<NguoidungDuan> getNguoidungDuanCollection() {
        return nguoidungDuanCollection;
    }

    public void setNguoidungDuanCollection(Collection<NguoidungDuan> nguoidungDuanCollection) {
        this.nguoidungDuanCollection = nguoidungDuanCollection;
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
        if (!(object instanceof Nguoidung)) {
            return false;
        }
        Nguoidung other = (Nguoidung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Backend.Model.Nguoidung[ id=" + id + " ]";
    }
    
}
