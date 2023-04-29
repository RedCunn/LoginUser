class Empleado:
    LIMITE_IMPUESTO = 1000
    TASA_IMPUESTO_MAYOR = 0.15
    TASA_IMPUESTO_MENOR = 0.1

    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario

    def calcular_salario_neto(self):
        impuestos = self.calcular_impuestos()
        salario_neto = self.salario - impuestos
        return salario_neto

    def calcular_impuestos(self):
        if self.salario > self.LIMITE_IMPUESTO:
            return self.salario * self.TASA_IMPUESTO_MAYOR
        else:
            return self.salario * self.TASA_IMPUESTO_MENOR

    def aplicar_aumento_salario(self, porcentaje):
        self.salario = self.salario + self.salario * (porcentaje / 100)
