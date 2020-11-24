package com.example.agendaasier;

public class ObjetoTarea {
    private String nomTarea;
    private String DescTarea;
    private String FechaTarea;
    private int CosteTarea;
    private  String SpinnerPrio;

    public ObjetoTarea(String nomTarea, String descTarea, String fechaTarea, int costeTarea, String spinnerPrio) {
        this.nomTarea = nomTarea;
        this.DescTarea = descTarea;
        this.FechaTarea = fechaTarea;
        this.CosteTarea = costeTarea;
        this.SpinnerPrio = spinnerPrio;
    }

    @Override
    public String toString() {
        return "ObjetoTarea{" +
                "nomTarea='" + nomTarea + '\'' +
                ", DescTarea='" + DescTarea + '\'' +
                ", FechaTarea='" + FechaTarea + '\'' +
                ", CosteTarea=" + CosteTarea +
                ", SpinnerPrio='" + SpinnerPrio + '\'' +
                '}';
    }
}
