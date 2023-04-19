package Investimentos;

public record Investimentos(double valorInvestido, double jurosMensais, int mesesInvestimento) {

    public double calcularLucro() {
        double jurosAcumulados = Math.pow(1 + jurosMensais, mesesInvestimento) - 1;
        return valorInvestido * jurosAcumulados;
    }

    public double calcularValorTotal() {
        return valorInvestido + calcularLucro();
    }

    public void imprimirInvestimento() {
        System.out.println("Investimento:");
        System.out.printf("Valor investido: R$ %.2f\n", valorInvestido);
        System.out.printf("Juros mensais: %.2f%%\n", jurosMensais * 100);
        System.out.printf("Meses de investimento: %d\n", mesesInvestimento);
        System.out.printf("Valor total: R$ %.2f\n", calcularValorTotal());
    }

    public static void main(String[] args) {
        Investimentos investimento1 = new Investimentos(1000, 0.15, 12);
        investimento1.imprimirInvestimento();
    }
}