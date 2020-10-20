package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {
    Laboratorio.rolAsignado = Limpiador
    val empleadoBiclope = Biclope(0)
    val empleadoCiclope = Ciclople(50)


    it("empleado come uva ") {
      empleadoCiclope.comerFruta(Uva)
      empleadoBiclope.comerFruta(Uva)

      empleadoCiclope.estamina.shouldBe(51)
      empleadoBiclope.estamina.shouldBe(1)
    }
    it("empleado come manzana") {
      empleadoCiclope.comerFruta(Manzana)
      empleadoBiclope.comerFruta(Manzana)

      empleadoCiclope.estamina.shouldBe(55)
      empleadoBiclope.estamina.shouldBe(5)
    }
    it("empleado como bananas") {
      empleadoCiclope.comerFruta(Banana)
      empleadoBiclope.comerFruta(Banana)

      empleadoCiclope.estamina.shouldBe(150)
      empleadoBiclope.estamina.shouldBe(10)
    }
    describe("experiencia que tiene los empleados por realizar tareas"){

      val repararMaquina = ArreglarMaquina(mutableListOf(),10)

      repeat(2){
        Laboratorio.enviarTarea(empleadoCiclope,repararMaquina)
      }
      it("total de experiencia adquirida por el empleadoBiclope"){
        empleadoBiclope.experiencia().shouldBe(0)

      }
      it("experiencia del ciclope, no realizo ninguna tarea"){
        empleadoCiclope.experiencia().shouldBe(40)

        empleadoBiclope.rol.shouldBe(Limpiador)
      }
    }
  }


  describe("ejemplos- no del parcial"){
    Laboratorio.rolAsignado = Soldado
    val sector1 = Sector(true,true,4)

    it("un Bíclope Soldado con 10 de estamina y 4 de daño extra"){
      val mimBiclople = Biclope(10)
      mimBiclople.defender(sector1)
      mimBiclople.defender(sector1)
      mimBiclople.fuerza().shouldBe(11)
    }
    // debe redondear para abajo y el int aparentemente lo hace
    it("Cíclope Soldado estamina 10, daño extra 4") {

      val minCiclo = Ciclople(10)
      minCiclo.defender(sector1)
      minCiclo.defender(sector1)
      //minCiclo.fuerza().shouldBe(5)
    }


  }


})
