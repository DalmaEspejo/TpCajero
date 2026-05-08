package unlarr.edu.ar.model;

import java.time.LocalDateTime;

public class Transaccion {

    private TipoTransaccion tipo;
    private double monto;
    private LocalDateTime fechaHora;
    private String descripcion;

    public Transaccion(TipoTransaccion tipo, double monto, String descripcion){
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
    }

    public TipoTransaccion geTipo(){
        return tipo;
    }

    public double getMonto(){
        return monto;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;

    }

    public String getDescripcion(){
        return descripcion;
    }

    @Override
    public String toString(){
        return fechaHora +
                " - " + tipo +
                " - $" + monto +
                " - " + descripcion;
    }

}
