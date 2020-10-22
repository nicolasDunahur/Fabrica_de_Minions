package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.describeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {

    val laboratorio = Laboratorio()


    val empleadoBiclope = Biclope(Obrero, 0)
    val empleadoCiclope = Ciclople(Obrero, 50)


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
    describe("experiencia que tiene los empleados por realizar tareas") {

      val maquinaQuimica = ArreglarMaquina(mutableListOf("destornillador"), 5)

      repeat(2) {
        laboratorio.enviarTarea(empleadoCiclope, maquinaQuimica)
      }

      it("total de experiencia adquirida por el empleadoBiclope") {
        empleadoBiclope.experiencia().shouldBe(0)

      }
      it("experiencia del ciclope, realizo 2 tareas de reparacion") {
        empleadoCiclope.experiencia().shouldBe(40)

      }
    }
    describe(" fuerza de los empleados"){

      val ciclopeObrero = Ciclople(Obrero,10)

      it("fuerza de los empleados con rol obrero"){

        empleadoBiclope.fuerza().shouldBe(2)
        ciclopeObrero.fuerza().shouldBe(3)
      }
      it("fuerza de los empleados con rol Soldado"){
        laboratorio.asignarRol(ciclopeObrero,Soldado)
        Soldado.danioExtra = 4

        ciclopeObrero.fuerza().shouldBe(5)


      }
    }

    describe("si lo empleados pueden realizar las tareas asignadas") {
      val pc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)

      it("empleado ciclope no puede realizar tarea de reparacion, no tiene la herramientas necesarias ") {
        empleadoCiclope.puedeRealizarTarea(pc).shouldBeFalse()
      }

      val maquina = ArreglarMaquina(mutableListOf(), 25)

      it("empleadoCiclope puede realizar tarea de raparacion ") {

        empleadoCiclope.puedeRealizarTarea(maquina).shouldBeTrue()
      }

      describe("si el empleado puede defender el sector") {
        val sectorA = Sector(true,false,20)
        val defensa = DefenderSector(sectorA)
        val obreroBiclope = Biclope(Obrero, 10)
        val obreroCicople = Ciclople(Obrero,100)

        describe(" el empleado no puede realizar la tarea ,el empleado ahora tiene rol limpiador"){

          laboratorio.asignarRol(empleadoBiclope,Limpiador)

          it("no puede defender"){
            empleadoBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
        }
        describe("empleados con rol de Obreros"){

          it("pueden defender el sector"){


            defensa.puedeSerRealizada(obreroBiclope).shouldBeFalse()

            defensa.puedeSerRealizada(obreroCicople).shouldBeTrue()
          }
        }
        describe("empleados con rol soldado"){
          laboratorio.asignarRol(empleadoBiclope,Soldado)
          laboratorio.asignarRol(empleadoCiclope,Soldado)

          it( "el empleado no pude realizar la tarea de defender el sector"){
            obreroBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
          it("el empleado si puede realizar la tarea de defenser el sector "){

            obreroCicople.puedeRealizarTarea(defensa).shouldBeTrue()
          }
        }
      }

    }

  }

  /*describe("ejemplos- no del parcial"){


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


  }*/


})
