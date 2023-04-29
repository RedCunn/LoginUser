class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario

    def calcular_salario_neto(self):
        # Calcular impuestos
        impuestos = 0
        if self.salario > 1000:
            impuestos = self.salario * 0.15
        else:
            impuestos = self.salario * 0.1
        salario_neto = self.salario - impuestos
        return salario_neto

    def obtener_aumento(self, porcentaje):
        self.salario = self.salario + self.salario * (porcentaje / 100)
