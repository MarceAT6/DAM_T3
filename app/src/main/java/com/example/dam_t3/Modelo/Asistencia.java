package com.example.dam_t3.Modelo;

import java.util.Date;

public class Asistencia {
    private int idAsistencia;
    private int idSede;
    private int idAlumno;
    private String fecha;
    private String horaEnt;
    private String horaSal;

    public Asistencia(int idAsistencia, int idSede, int idAlumno, String fecha, String horaEnt, String horaSal) {
        this.idAsistencia = idAsistencia;
        this.idSede = idSede;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.horaEnt = horaEnt;
        this.horaSal = horaSal;
    }

    public Asistencia(int idSede, int idAlumno, String fecha, String horaEnt, String horaSal) {
        this.idSede = idSede;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.horaEnt = horaEnt;
        this.horaSal = horaSal;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraEnt() {
        return horaEnt;
    }

    public void setHoraEnt(String horaEnt) {
        this.horaEnt = horaEnt;
    }

    public String getHoraSal() {
        return horaSal;
    }

    public void setHoraSal(String horaSal) {
        this.horaSal = horaSal;
    }
}
