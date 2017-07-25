package org.tsys.sbb.model;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class Station {
    private Integer stationId;
    private String stationName;
    private Integer xCrd;
    private Integer yCrd;

    @Id
    @Column(name = "station_id", nullable = false)
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "station_name", nullable = false, length = 45)
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "x_crd", nullable = false)
    public Integer getxCrd() {
        return xCrd;
    }

    public void setxCrd(Integer xCrd) {
        this.xCrd = xCrd;
    }

    @Basic
    @Column(name = "y_crd", nullable = false)
    public Integer getyCrd() {
        return yCrd;
    }

    public void setyCrd(Integer yCrd) {
        this.yCrd = yCrd;
    }
}
