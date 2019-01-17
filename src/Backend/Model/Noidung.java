/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "noidung", catalog = "TaskManagement", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noidung.findAll", query = "SELECT n FROM Noidung n")
    , @NamedQuery(name = "Noidung.findByIDNoiDung", query = "SELECT n FROM Noidung n WHERE n.iDNoiDung = :iDNoiDung")
    , @NamedQuery(name = "Noidung.findByTieuDe", query = "SELECT n FROM Noidung n WHERE n.tieuDe = :tieuDe")
    , @NamedQuery(name = "Noidung.findByNoiDung", query = "SELECT n FROM Noidung n WHERE n.noiDung = :noiDung")
    , @NamedQuery(name = "Noidung.findByTrangThai", query = "SELECT n FROM Noidung n WHERE n.trangThai = :trangThai")
    , @NamedQuery(name = "Noidung.findByNgayBatDau", query = "SELECT n FROM Noidung n WHERE n.ngayBatDau = :ngayBatDau")
    , @NamedQuery(name = "Noidung.findByNgayKetThuc", query = "SELECT n FROM Noidung n WHERE n.ngayKetThuc = :ngayKetThuc")})
public class Noidung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDNoiDung")
    private String iDNoiDung;
    @Basic(optional = false)
    @Column(name = "TieuDe")
    private String tieuDe;
    @Basic(optional = false)
    @Column(name = "NoiDung")
    private String noiDung;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private String trangThai;
    @Basic(optional = false)
    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;
    @Basic(optional = false)
    @Column(name = "NgayKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;
    @JoinTable(name = "nguoidung_noidung", joinColumns = {
        @JoinColumn(name = "IDNoiDung", referencedColumnName = "IDNoiDung")}, inverseJoinColumns = {
        @JoinColumn(name = "IDNguoiDung", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Nguoidung> nguoidungCollection;
    @JoinColumn(name = "IDDuAn", referencedColumnName = "IDDuAn")
    @ManyToOne(optional = false)
    private Duan iDDuAn;


    public String getiDNoiDung() {
        return iDNoiDung;
    }

    public void setiDNoiDung(String iDNoiDung) {
        this.iDNoiDung = iDNoiDung;
    }

    public Duan getiDDuAn() {
        return iDDuAn;
    }

    public void setiDDuAn(Duan iDDuAn) {
        this.iDDuAn = iDDuAn;
    }

    public Noidung() {
    }

    public Noidung(String iDNoiDung) {
        this.iDNoiDung = iDNoiDung;
    }

    public Noidung(String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc) {
        this.iDNoiDung = iDNoiDung;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getIDNoiDung() {
        return iDNoiDung;
    }

    public void setIDNoiDung(String iDNoiDung) {
        this.iDNoiDung = iDNoiDung;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    @XmlTransient
    public Collection<Nguoidung> getNguoidungCollection() {
        return nguoidungCollection;
    }

    public void setNguoidungCollection(Collection<Nguoidung> nguoidungCollection) {
        this.nguoidungCollection = nguoidungCollection;
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
        hash += (iDNoiDung != null ? iDNoiDung.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noidung)) {
            return false;
        }
        Noidung other = (Noidung) object;
        if ((this.iDNoiDung == null && other.iDNoiDung != null) || (this.iDNoiDung != null && !this.iDNoiDung.equals(other.iDNoiDung))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Backend.Model.Noidung[ iDNoiDung=" + iDNoiDung + " ]";
    }
    

    
}
