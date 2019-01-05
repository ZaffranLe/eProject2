/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vukho
 */
@Entity
@Table(name = "duan", catalog = "TaskManagement", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Duan.findAll", query = "SELECT d FROM Duan d")
    , @NamedQuery(name = "Duan.findByIDDuAn", query = "SELECT d FROM Duan d WHERE d.iDDuAn = :iDDuAn")
    , @NamedQuery(name = "Duan.findByTenDuAn", query = "SELECT d FROM Duan d WHERE d.tenDuAn = :tenDuAn")
    , @NamedQuery(name = "Duan.findByNgayBatDau", query = "SELECT d FROM Duan d WHERE d.ngayBatDau = :ngayBatDau")
    , @NamedQuery(name = "Duan.findByNgayKetThuc", query = "SELECT d FROM Duan d WHERE d.ngayKetThuc = :ngayKetThuc")
    , @NamedQuery(name = "Duan.findByTrangThai", query = "SELECT d FROM Duan d WHERE d.trangThai = :trangThai")})
public class Duan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDDuAn")
    private String iDDuAn;
    @Basic(optional = false)
    @Column(name = "TenDuAn")
    private String tenDuAn;
    @Basic(optional = false)
    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;
    @Column(name = "NgayKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private String trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duan")
    private Collection<NguoidungDuan> nguoidungDuanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDDuAn")
    private Collection<Nhatky> nhatkyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDDuAn")
    private Collection<Noidung> noidungCollection;

    public Duan() {
    }

    public Duan(String iDDuAn) {
        this.iDDuAn = iDDuAn;
    }

    public Duan(String iDDuAn, String tenDuAn, Date ngayBatDau, String trangThai) {
        this.iDDuAn = iDDuAn;
        this.tenDuAn = tenDuAn;
        this.ngayBatDau = ngayBatDau;
        this.trangThai = trangThai;
    }

    public String getIDDuAn() {
        return iDDuAn;
    }

    public void setIDDuAn(String iDDuAn) {
        this.iDDuAn = iDDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Collection<NguoidungDuan> getNguoidungDuanCollection() {
        return nguoidungDuanCollection;
    }

    public void setNguoidungDuanCollection(Collection<NguoidungDuan> nguoidungDuanCollection) {
        this.nguoidungDuanCollection = nguoidungDuanCollection;
    }

    @XmlTransient
    public Collection<Nhatky> getNhatkyCollection() {
        return nhatkyCollection;
    }

    public void setNhatkyCollection(Collection<Nhatky> nhatkyCollection) {
        this.nhatkyCollection = nhatkyCollection;
    }

    @XmlTransient
    public Collection<Noidung> getNoidungCollection() {
        return noidungCollection;
    }

    public void setNoidungCollection(Collection<Noidung> noidungCollection) {
        this.noidungCollection = noidungCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDuAn != null ? iDDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Duan)) {
            return false;
        }
        Duan other = (Duan) object;
        if ((this.iDDuAn == null && other.iDDuAn != null) || (this.iDDuAn != null && !this.iDDuAn.equals(other.iDDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Backend.Model.Duan[ iDDuAn=" + iDDuAn + " ]";
    }
    
}
