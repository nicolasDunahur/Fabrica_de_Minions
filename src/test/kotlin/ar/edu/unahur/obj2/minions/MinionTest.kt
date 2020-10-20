package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {

    val empleadoBiclope = Biclope(Limpiador,0)
    val empleadoCiclope = Ciclople(Limpiador, 0)

    it("empleado come uva "){
      empleadoCiclope.comerFruta(Uva)
      empleadoBiclope.comerFruta(Uva)

      empleadoCiclope.estamina.shouldBe(1)
      empleadoBiclope.estamina.shouldBe(1)
    }
    it("empleado come manzana"){
      empleadoCiclope.comerFruta(Manzana)
      empleadoBiclope.comerFruta(Manzana)

      empleadoCiclope.estamina.shouldBe(5)
      empleadoBiclope.estamina.shouldBe(5)
    }
    it("empleado como bananas"){
      empleadoCiclope.comerFruta(Banana)
      empleadoBiclope.comerFruta(Banana)

      empleadoCiclope.estamina.shouldBe(100)
      empleadoBiclope.estamina.shouldBe(10)
    }


  }

  describe("ejemplos- no del parcial"){
    val sector1 = Sector(true,true,4)

    it("un Bíclope Soldado con 10 de estamina y 4 de daño extra"){
      val mimBiclople = Biclope(Soldado,10)
      mimBiclople.defender(sector1)
      mimBiclople.defender(sector1)
      mimBiclople.fuerza().shouldBe(11)
    }
    // debe redondear para abajo y el int aparentemente lo hace
    it("Cíclope Soldado estamina 10, daño extra 4") {
      val minCiclo = Ciclople(Soldado,10)
      minCiclo.defender(sector1)
      minCiclo.defender(sector1)
      minCiclo.fuerza().shouldBe(5)
    }


  }


})
