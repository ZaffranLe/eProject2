/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vukho
 */
@Entity
@Table(name = "nguoidung_duan", catalog = "TaskManagement", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NguoidungDuan.findAll", query = "SELECT n FROM NguoidungDuan n")
    , @NamedQuery(name = "NguoidungDuan.findByIDNguoiDung", query = "SELECT n FROM NguoidungDuan n WHERE n.nguoidungDuanPK.iDNguoiDung = :iDNguoiDung")
    , @NamedQuery(name = "NguoidungDuan.findByIDDuAn", query = "SELECT n FROM NguoidungDuan n WHERE n.nguoidungDuanPK.iDDuAn = :iDDuAn")
    , @NamedQuery(name = "NguoidungDuan.findByViTri", query = "SELECT n FROM NguoidungDuan n WHERE n.viTri = :viTri")})
public class NguoidungDuan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NguoidungDuanPK nguoidungDuanPK;
    @Basic(optional = false)
    @Column(name = "ViTri")
    private String viTri;
    @JoinColumn(name = "IDNguoiDung", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Nguoidung nguoidung;
    @JoinColumn(name = "IDDuAn", referencedColumnName = "IDDuAn", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Duan duan;

    public NguoidungDuan() {
    }

    public NguoidungDuan(NguoidungDuanPK nguoidungDuanPK) {
        this.nguoidungDuanPK = nguoidungDuanPK;
    }

    public NguoidungDuan(NguoidungDuanPK nguoidungDuanPK, String viTri) {
        this.nguoidungDuanPK = nguoidungDuanPK;
        this.viTri = viTri;
    }

    public NguoidungDuan(int iDNguoiDung, String iDDuAn) {
        this.nguoidungDuanPK = new NguoidungDuanPK(iDNguoiDung, iDDuAn);
    }

    public NguoidungDuanPK getNguoidungDuanPK() {
        return nguoidungDuanPK;
    }

    public void setNguoidungDuanPK(NguoidungDuanPK nguoidungDuanPK) {
        this.nguoidungDuanPK = nguoidungDuanPK;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Nguoidung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(Nguoidung nguoidung) {
        this.nguoidung = nguoidung;
    }

    public Duan getDuan() {
        return duan;
    }

    public void setDuan(Duan duan) {
        this.duan = duan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nguoidungDuanPK != null ? nguoidungDuanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoidungDuan)) {
            return false;
        }
        NguoidungDuan other = (NguoidungDuan) object;
        if ((this.nguoidungDuanPK == null && other.nguoidungDuanPK != null) || (this.nguoidungDuanPK != null && !this.nguoidungDuanPK.equals(other.nguoidungDuanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Backend.Model.NguoidungDuan[ nguoidungDuanPK=" + nguoidungDuanPK + " ]";
    }
    
}
