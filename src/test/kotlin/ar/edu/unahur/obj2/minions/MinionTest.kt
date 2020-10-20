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
})
