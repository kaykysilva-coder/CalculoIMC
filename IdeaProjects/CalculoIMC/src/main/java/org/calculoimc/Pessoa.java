package org.calculoimc;

public class Pessoa {
    private int id;
    private String nome;
    private double altura;
    private double peso;
    private double imc;

    public Pessoa(int id, String nome, double altura, double peso) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.imc = calcularIMC();
    }

    public Pessoa(int id, String nome, double altura, double peso, double imc) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.imc = imc;
    }

    public double calcularIMC() {
        if (altura <= 0) return 0;
        return peso / (altura * altura);
    }

    public String getClassificacao() {
        if (imc < 18.5) return "Abaixo do Peso";
        if (imc < 25.0) return "Peso Normal";
        if (imc < 30.0) return "Sobrepeso";
        if (imc < 35.0) return "Obesidade Grau 1";
        if (imc < 40.0) return "Obesidade Grau 2";
        return "Obesidade Grau 3";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }
}