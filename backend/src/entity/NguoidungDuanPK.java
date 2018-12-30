/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Quang
 */
@Embeddable
public class NguoidungDuanPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDNguoiDung")
    private int iDNguoiDung;
    @Basic(optional = false)
    @Column(name = "IDDuAn")
    private String iDDuAn;

    public NguoidungDuanPK() {
    }

    public NguoidungDuanPK(int iDNguoiDung, String iDDuAn) {
        this.iDNguoiDung = iDNguoiDung;
        this.iDDuAn = iDDuAn;
    }

    public int getIDNguoiDung() {
        return iDNguoiDung;
    }

    public void setIDNguoiDung(int iDNguoiDung) {
        this.iDNguoiDung = iDNguoiDung;
    }

    public String getIDDuAn() {
        return iDDuAn;
    }

    public void setIDDuAn(String iDDuAn) {
        this.iDDuAn = iDDuAn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDNguoiDung;
        hash += (iDDuAn != null ? iDDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoidungDuanPK)) {
            return false;
        }
        NguoidungDuanPK other = (NguoidungDuanPK) object;
        if (this.iDNguoiDung != other.iDNguoiDung) {
            return false;
        }
        if ((this.iDDuAn == null && other.iDDuAn != null) || (this.iDDuAn != null && !this.iDDuAn.equals(other.iDDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NguoidungDuanPK[ iDNguoiDung=" + iDNguoiDung + ", iDDuAn=" + iDDuAn + " ]";
    }
    
}
